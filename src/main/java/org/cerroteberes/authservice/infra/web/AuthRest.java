package org.cerroteberes.authservice.infra.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.cerroteberes.authservice.app.port.input.use_case.InLoginUser;
import org.cerroteberes.authservice.app.port.input.use_case.InRefreshToken;
import org.cerroteberes.authservice.app.port.input.use_case.InRegisterUser;
import org.cerroteberes.authservice.domain.dto.request.RequestLoginJWTDTO;
import org.cerroteberes.authservice.domain.dto.request.RequestRegisterUserDTO;
import org.cerroteberes.authservice.domain.dto.response.ResponseJWT;
import org.cerroteberes.authservice.domain.entity.enums.TypeUserSignup;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthRest {
    private final InRegisterUser inRegisterUser;
    private final InRefreshToken inRefreshToken;
    private final InLoginUser inLoginUser;
    @PostMapping("/sing-up/{type-user}")
    public ResponseEntity<Void> registerNewUser(
            @Valid @RequestBody RequestRegisterUserDTO dto,
            @PathVariable("type-user") TypeUserSignup typeUser){
        inRegisterUser.executeRegisterUser(dto,typeUser);
        return ResponseEntity.created(URI.create("")).build();
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseJWT> getTokenJwt(@Valid @RequestBody RequestLoginJWTDTO dto){
        return ResponseEntity.ok(inLoginUser.executeLoginUser(dto));
    }
    @GetMapping("/refresh-token")
    public ResponseEntity<ResponseJWT> getRefreshToken(@RequestParam("refresh-token")String tokenRefresh){
        return ResponseEntity.ok(inRefreshToken.executeRefreshment(tokenRefresh));
    }

}
