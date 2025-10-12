package com.content.authentication_service.service.abstractservice;
import com.google.firebase.auth.UserRecord;

public interface FireBaseService{
    UserRecord createUserInFirebase(String email, String password, String displayName);
    UserRecord updateUserInFirebase(String uid, String email, String password, String displayName);
    void deleteUserInFirebase(String uid);
    UserRecord getUserByUid(String uid);
}