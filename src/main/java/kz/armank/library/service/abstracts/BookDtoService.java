package kz.armank.library.service.abstracts;

import kz.armank.library.dto.BookDto;

import java.util.Optional;

public interface BookDtoService {

    Optional<BookDto> getById();
}
