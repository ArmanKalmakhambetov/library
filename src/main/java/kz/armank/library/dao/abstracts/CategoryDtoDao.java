package kz.armank.library.dao.abstracts;

import kz.armank.library.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryDtoDao {

    Optional<List<CategoryDto>> getCategoryDto();
}
