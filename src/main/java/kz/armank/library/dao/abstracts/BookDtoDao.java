package kz.armank.library.dao.abstracts;

import kz.armank.library.dto.BookDto;

import java.util.Optional;

public interface BookDtoDao {

    Optional<BookDto> getBookDto();
}
