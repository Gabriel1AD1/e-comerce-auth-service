package org.cerroteberes.authservice.domain.client;

import org.cerroteberes.authservice.domain.dto.request.RequestRegisterUserDTO;
import org.cerroteberes.authservice.domain.entity.enums.TypeUserSignup;
import org.cerroteberes.authservice.domain.model.UserPrincipal;

public interface ClientUserService {
    UserPrincipal getUserPrincipalForName(String email);
    void registerUser(RequestRegisterUserDTO dto, TypeUserSignup typeUser);
    UserPrincipal getUserPrincipalForUserId(Long userId);
}
