package org.cerroteberes.authservice.infra.client.adapter;

import lombok.AllArgsConstructor;
import org.cerroteberes.authservice.app.port.output.client.OutClientUserService;
import org.cerroteberes.authservice.domain.dto.request.RequestRegisterUserDTO;
import org.cerroteberes.authservice.domain.entity.enums.TypeUserSignup;
import org.cerroteberes.authservice.domain.model.UserPrincipal;
import org.cerroteberes.authservice.infra.client.feing.UserClient;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdapterOutClientUserService implements OutClientUserService {
    private final UserClient userClient;
    @Override
    public UserPrincipal getUserPrincipalForName(String email) {
        return userClient.getUserByEmail(email);
    }

    @Override
    public void registerUser(RequestRegisterUserDTO dto, TypeUserSignup typeUser) {
        userClient.registerUser(dto,typeUser);
    }

    @Override
    public UserPrincipal getUserPrincipalForUserId(Long userId) {
        return userClient.getUserById(userId);
    }
}
