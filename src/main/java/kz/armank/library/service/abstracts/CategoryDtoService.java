package kz.armank.library.service.abstracts;

import kz.armank.library.dto.BookDto;
import kz.armank.library.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryDtoService {

    Optional<List<CategoryDto>> getCategoryDto();
}
