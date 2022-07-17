package by.hrachyshkin.watcher.dto;

import org.springframework.data.domain.Page;

public interface DtoMapper<M, D> {

    D modelToDto(M m);

    M dtoToModel(D d);

    Page<D> modelsToDto(Page<M> m);
}
