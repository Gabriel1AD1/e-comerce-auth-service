package org.cerroteberes.authservice.domain.comons;

public interface WriteRepository<M, I> {
    M save(M entity);

    void delete(I id);
}
