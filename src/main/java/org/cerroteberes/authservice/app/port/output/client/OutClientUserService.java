package org.cerroteberes.authservice.app.port.output.client;

import org.cerroteberes.authservice.domain.model.UserPrincipal;

public interface OutClientUserService {
    UserPrincipal getUserPrincipalForName(String email,String password);
}
