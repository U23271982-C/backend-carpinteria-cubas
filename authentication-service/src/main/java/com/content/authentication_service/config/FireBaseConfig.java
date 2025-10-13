package com.content.authentication_service.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FireBaseConfig {

    /**
     * Este método se ejecuta una vez que el bean ha sido creado e inicializado.
     * Es el lugar perfecto para inicializar FirebaseApp.
     */
    @PostConstruct
    public void initialize() {
        try {
            // Usamos ClassPathResource para cargar el archivo desde el classpath
            ClassPathResource resource = new ClassPathResource("carpinteriacubas-6bd84-firebase-adminsdk-fbsvc-0a8ab16790.json");

            // Obtenemos el InputStream del archivo
            InputStream serviceAccount = resource.getInputStream();

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            // Solo inicializamos la app si no existe una ya inicializada
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase application has been initialized");
            }

        } catch (IOException e) {
            // Manejar la excepción de forma adecuada para tu aplicación
            e.printStackTrace();
        }
    }

    /**
     * Creamos un Bean de FirebaseApp para que pueda ser inyectado en otras partes
     * de la aplicación si es necesario.
     * @return FirebaseApp
     */
    @Bean
    public FirebaseApp firebaseApp() {
        return FirebaseApp.getInstance();
    }

    /**
     * Creamos un Bean de FirebaseAuth para facilitar su inyección en servicios
     * y controladores.
     * @param firebaseApp La instancia de FirebaseApp creada en el bean anterior.
     * @return FirebaseAuth
     */
    @Bean
    public FirebaseAuth firebaseAuth(FirebaseApp firebaseApp) {
        return FirebaseAuth.getInstance(firebaseApp);
    }
}