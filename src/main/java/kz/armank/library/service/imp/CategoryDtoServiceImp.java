package kz.armank.library.service.imp;

import kz.armank.library.dao.abstracts.CategoryDtoDao;
import kz.armank.library.dto.CategoryDto;
import kz.armank.library.service.abstracts.CategoryDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryDtoServiceImp implements CategoryDtoService {

    private final CategoryDtoDao categoryDtoDao;

    @Override
    public Optional<List<CategoryDto>> getCategoryDto() {
        return categoryDtoDao.getCategoryDto();
    }
}
