package org.cerroteberes.authservice.app.utils;


import java.util.Optional;
import java.util.function.Function;

public class OptionalMapper {

    // Método genérico para mapear un Optional<T> a otro valor
    public static <T, R> R mapOptional(Optional<T> optional, Function<T, R> mapper, R defaultValue) {
        return optional.map(mapper).orElse(defaultValue);
    }

    // Método para mapear y lanzar excepción si el valor está vacío
    public static <T, R> R mapOptionalOrThrow(Optional<T> optional, Function<T, R> mapper, RuntimeException exception) {
        return optional.map(mapper).orElseThrow(() -> exception);
    }

    public static <T> Optional<T> wrapInOptional(T object) {
        return Optional.ofNullable(object);
    }

    // Método genérico para mapear un objeto a Optional<R>
    public static <T, R> Optional<R> mapToOptional(T object, Function<T, R> mapper) {
        return Optional.ofNullable(object)
                .map(mapper);
    }

}
