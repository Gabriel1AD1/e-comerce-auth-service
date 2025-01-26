package org.cerroteberes.authservice.app.port.output.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Service
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@AppTransactional
public @interface AppUseCase {
}
