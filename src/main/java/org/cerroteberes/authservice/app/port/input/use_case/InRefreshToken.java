package org.cerroteberes.authservice.app.port.input.use_case;

import org.cerroteberes.authservice.domain.dto.response.ResponseJWT;

public interface InRefreshToken {
    ResponseJWT executeRefreshment(String refreshToken);
}
