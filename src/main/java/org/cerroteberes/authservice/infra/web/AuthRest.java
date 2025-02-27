package org.cerroteberes.authservice.infra.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.cerroteberes.authservice.app.port.input.use_case.InLoginUser;
import org.cerroteberes.authservice.app.port.input.use_case.InRefreshToken;
import org.cerroteberes.authservice.app.port.input.use_case.InRegisterUser;
import org.cerroteberes.authservice.domain.dto.request.RequestLoginJWTDTO;
import org.cerroteberes.authservice.domain.dto.request.RequestRegisterUserDTO;
import org.cerroteberes.authservice.domain.dto.request.RequestTokenRefreshDTO;
import org.cerroteberes.authservice.domain.dto.response.ResponseJWT;
import org.cerroteberes.authservice.domain.dto.response.ResponseTokenRefreshJWT;
import org.cerroteberes.authservice.domain.entity.enums.TypeUserSignup;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.net.URI;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
@Tag(name = "Auth API", description = "API para gestionar la autenticación y registro de usuarios")
public class AuthRest {
    private final InRegisterUser inRegisterUser;
    private final InRefreshToken inRefreshToken;
    private final InLoginUser inLoginUser;

    @Operation(
            summary = "Registrar nuevo usuario",
            description = "Permite registrar un nuevo usuario especificando el tipo de usuario (ejemplo: ADMIN o CLIENT)."
    )
    @PostMapping("/sing-up/{type-user}")
    public ResponseEntity<Void> registerNewUser(
            @Valid @RequestBody RequestRegisterUserDTO dto,
            @PathVariable("type-user") TypeUserSignup typeUser) {
        inRegisterUser.executeRegisterUser(dto, typeUser);
        return ResponseEntity.created(URI.create("")).build();
    }

    @Operation(
            summary = "Obtener token JWT",
            description = "Genera un token JWT válido para el usuario autenticado."
    )
    @PostMapping("/login")
    public ResponseEntity<ResponseJWT> getTokenJwt(@Valid @RequestBody RequestLoginJWTDTO dto) {
        return ResponseEntity.ok(inLoginUser.executeLoginUser(dto));
    }

    @Operation(
            summary = "Refrescar token JWT",
            description = "Permite obtener un nuevo token JWT a partir de uno caducado."
    )
    @PostMapping("/refresh-token")
    public ResponseEntity<ResponseTokenRefreshJWT> getRefreshToken(@Valid @RequestBody RequestTokenRefreshDTO tokenRefresh) {
        return ResponseEntity.ok(inRefreshToken.executeRefreshment(tokenRefresh));
    }
}
