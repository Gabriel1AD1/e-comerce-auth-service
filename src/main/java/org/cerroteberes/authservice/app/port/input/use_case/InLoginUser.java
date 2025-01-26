package org.cerroteberes.authservice.app.port.input.use_case;

import org.cerroteberes.authservice.domain.dto.request.RequestLoginJWTDTO;
import org.cerroteberes.authservice.domain.dto.response.ResponseJWT;

public interface InLoginUser {
    ResponseJWT executeLoginUser(RequestLoginJWTDTO dto);
}
