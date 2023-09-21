package kz.armank.library.service.imp;

import kz.armank.library.dao.abstracts.BookDtoDao;
import kz.armank.library.dto.BookDto;
import kz.armank.library.service.abstracts.BookDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookDtoServiceImp implements BookDtoService {

    private final BookDtoDao bookDtoDao;

    public Optional<BookDto> getById() {
        return bookDtoDao.getBookDto();
    }
}
