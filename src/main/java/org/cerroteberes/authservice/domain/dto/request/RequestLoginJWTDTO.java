package org.cerroteberes.authservice.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestLoginJWTDTO {
    @JsonProperty("email")
    @Email
    @NotNull
    String email;

    @JsonProperty("password")
    @NotNull(message = "La contraseña no puede ser nula")
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(regexp = ".*[A-Z].*", message = "La contraseña debe contener al menos una letra mayúscula")
    @Pattern(regexp = ".*[a-z].*", message = "La contraseña debe contener al menos una letra minúscula")
    @Pattern(regexp = ".*[0-9].*", message = "La contraseña debe contener al menos un número")
    @Pattern(regexp = ".*[!@#\\$%\\^&\\*].*", message = "La contraseña debe contener al menos un carácter especial (!@#$%^&*)")
    String password;
}
