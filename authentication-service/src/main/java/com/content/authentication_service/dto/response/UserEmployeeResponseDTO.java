package com.content.authentication_service.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UserEmployeeResponseDTO {

    private UUID uuid;
    private String full_name;
    private String user_name;
    private String position;
    private String phone;
    private String state;
    @JsonIgnore
    private Instant lastPasswordChange;

    // 2. Este es un método "getter virtual".
    // @JsonProperty("lastPasswordChange") le dice a Jackson que
    // llame a este método y ponga el resultado en el JSON
    // bajo la clave "lastPasswordChange".
    @JsonProperty("lastPasswordChange")
    public String getLastPasswordChangeAsString() {
        if (this.lastPasswordChange == null) {
            return "No se realizó cambio de contraseña";
        }
        // Si no es null, lo conviertes a String
        return this.lastPasswordChange.toString();
    }
}
