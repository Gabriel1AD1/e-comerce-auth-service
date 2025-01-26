package org.cerroteberes.authservice.infra.client.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FeignAuthInterceptor implements RequestInterceptor {

    private final GenerateMicroserviceJWT authToken;

    @Override
    public void apply(RequestTemplate template) {
        // Aquí estamos agregando el token Bearer en el encabezado de autorización
        template.header("Authorization", "Bearer " + authToken.tokenGenerate());
    }
}
