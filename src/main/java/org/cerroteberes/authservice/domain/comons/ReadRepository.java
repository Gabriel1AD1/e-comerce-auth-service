package org.cerroteberes.authservice.domain.comons;

import java.util.List;
import java.util.Optional;

public interface ReadRepository<M, I> {
    Optional<M> findByIdRead(I id);

    List<M> findALlRead();
}
