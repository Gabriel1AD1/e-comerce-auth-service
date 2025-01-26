package org.cerroteberes.authservice.app.implementation.usecase;

import lombok.AllArgsConstructor;
import org.cerroteberes.authservice.app.exception.LoginFailedException;
import org.cerroteberes.authservice.app.exception.TokenExpiredException;
import org.cerroteberes.authservice.app.implementation.service.refresh_token.RefreshTokenService;
import org.cerroteberes.authservice.app.port.input.use_case.InLoginUser;
import org.cerroteberes.authservice.app.port.input.use_case.InRefreshToken;
import org.cerroteberes.authservice.app.port.input.use_case.InRegisterUser;
import org.cerroteberes.authservice.app.port.output.annotation.AppUseCase;
import org.cerroteberes.authservice.app.port.output.utils.OutGenerateToken;
import org.cerroteberes.authservice.domain.dto.response.ResponseJWT;
import org.cerroteberes.authservice.domain.entity.RefreshToken;
import org.cerroteberes.authservice.domain.client.ClientUserService;
import org.cerroteberes.authservice.domain.dto.request.RequestLoginJWTDTO;
import org.cerroteberes.authservice.domain.dto.request.RequestRegisterUserDTO;
import org.cerroteberes.authservice.domain.entity.enums.TypeUserSignup;
import org.cerroteberes.authservice.domain.model.UserPrincipal;

import java.util.List;

import static org.cerroteberes.authservice.app.utils.PasswordUtils.hashPassword;
import static org.cerroteberes.authservice.app.utils.PasswordUtils.verifyPassword;

@AppUseCase
@AllArgsConstructor
public class RegisterUserUseCase implements InRegisterUser, InLoginUser, InRefreshToken {
    private final ClientUserService clientUserService;
    private final OutGenerateToken outGenerateToken;
    private final RefreshTokenService refreshTokenService;
    @Override
    public void executeRegisterUser(RequestRegisterUserDTO dto, TypeUserSignup typeUser) {
        String passwordHash = hashPassword(dto.getPassword());
        dto.setPassword(passwordHash);
        clientUserService.registerUser(dto,typeUser);
    }

    @Override
    public ResponseJWT executeLoginUser(RequestLoginJWTDTO dto) {
        UserPrincipal userPrincipal = clientUserService.getUserPrincipalForName(dto.getEmail());

        if (!verifyPassword(dto.getPassword(),userPrincipal.getPasswordEncoded()))throw new LoginFailedException("La contraseña o el correo estan mal por favor revisarlo");

        List<String> roles = userPrincipal.getRolesAsString();
        String generateTokenJWT = outGenerateToken.generateToken(userPrincipal.getUSERID(),roles);
        Long expirationTime = outGenerateToken.expirationTimeInSeconds();


        RefreshToken refreshToken = refreshTokenService.createOrDelete(userPrincipal.getUSERID());

        String refreshTokenJwt = refreshToken.getRefreshToken();
        return ResponseJWT.builder()
                .token(generateTokenJWT)
                .milliseconds(expirationTime)
                .refreshToken(refreshTokenJwt)
                .build();

    }

    @Override
    public ResponseJWT executeRefreshment(String refreshToken) {
        Long userId = outGenerateToken.getIdUserForRefreshToken(refreshToken);
        UserPrincipal userPrincipal = clientUserService.getUserPrincipalForUserId(userId);
        List<String> roles = userPrincipal.getRolesAsString();
        String generateTokenJWT = outGenerateToken.generateToken(userPrincipal.getUSERID(),roles);
        Long expirationTime = outGenerateToken.expirationTimeInSeconds();
        RefreshToken refreshTokenFindByUserId = refreshTokenService.findByUserId(userId);
        if (refreshTokenService.verifyTokenNotExpired(refreshTokenFindByUserId)){
            String refreshTokenJwt =refreshTokenFindByUserId.getRefreshToken();
            return ResponseJWT.builder()
                    .token(generateTokenJWT)
                    .milliseconds(expirationTime)
                    .refreshToken(refreshTokenJwt)
                    .build();
        }
        throw new TokenExpiredException("El token ha expirado por favor volver hacer login");
    }
}
