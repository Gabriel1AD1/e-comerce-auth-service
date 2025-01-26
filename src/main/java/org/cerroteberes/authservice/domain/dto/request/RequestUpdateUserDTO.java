package org.cerroteberes.authservice.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

/**
 * DTO for {@link org.cerroteberes.authservice.domain.entity.User}
 */
@Value
public class RequestUpdateUserDTO {
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    String name;

    @NotNull(message = "La contraseña no puede ser nula")
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(regexp = ".*[A-Z].*", message = "La contraseña debe contener al menos una letra mayúscula")
    @Pattern(regexp = ".*[a-z].*", message = "La contraseña debe contener al menos una letra minúscula")
    @Pattern(regexp = ".*[0-9].*", message = "La contraseña debe contener al menos un número")
    @Pattern(regexp = ".*[!@#\\$%\\^&\\*].*", message = "La contraseña debe contener al menos un carácter especial (!@#$%^&*)")
    String password;
}
