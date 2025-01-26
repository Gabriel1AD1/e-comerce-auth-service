package org.cerroteberes.authservice.app.port.input.use_case;

import org.cerroteberes.authservice.domain.dto.request.RequestRegisterUserDTO;
import org.cerroteberes.authservice.domain.entity.enums.TypeUserSignup;

public interface InRegisterUser {
    void executeRegisterUser(RequestRegisterUserDTO dto, TypeUserSignup typeUser);
}
