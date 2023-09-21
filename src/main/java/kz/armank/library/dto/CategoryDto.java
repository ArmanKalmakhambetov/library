package kz.armank.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для статистики по категориям книг")
public class CategoryDto {
    @Schema(description = "Имя категории")
    private String categoryName;
    @Schema(description = "Количество книг в категории")
    private Long bookCount;

}
