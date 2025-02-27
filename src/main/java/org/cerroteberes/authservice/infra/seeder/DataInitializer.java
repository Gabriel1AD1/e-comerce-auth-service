package org.cerroteberes.authservice.infra.seeder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    @Value("${secret.token}")
    private String token;
    @Override
    public void run(String... args) throws Exception {
        log.debug("token cargado correctamente {}",token);
    }
}
