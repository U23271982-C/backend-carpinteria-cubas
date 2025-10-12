package com.content.authentication_service.service;
import com.content.authentication_service.service.abstractservice.FireBaseService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;

@Service
public class FireBaseServiceImpl implements FireBaseService {

    @Override
    public UserRecord createUserInFirebase(String email, String password, String displayName) {
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password)
                    .setDisplayName(displayName)
                    .setEmailVerified(false) // Opcional
                    .setDisabled(false);

            return FirebaseAuth.getInstance().createUser(request);
        } catch (FirebaseAuthException e) {
            // Es crucial manejar esta excepción. Lanza una excepción personalizada.
            throw new RuntimeException("Error creating user in Firebase: " + e.getMessage(), e);
        }
    }

    @Override
    public UserRecord updateUserInFirebase(String uid, String email, String password, String displayName) {
        try {
            UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(uid)
                    .setEmail(email)
                    .setDisplayName(displayName);

            if (password != null && !password.isEmpty()) {
                request.setPassword(password);
            }

            return FirebaseAuth.getInstance().updateUser(request);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Error updating user in Firebase: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteUserInFirebase(String uid) {
        try {
            FirebaseAuth.getInstance().deleteUser(uid);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Error deleting user in Firebase: " + e.getMessage(), e);
        }
    }

    @Override
    public UserRecord getUserByUid(String uid) {
        try {
            return FirebaseAuth.getInstance().getUser(uid);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Error fetching user from Firebase: " + e.getMessage(), e);
        }
    }
}