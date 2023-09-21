package kz.armank.library.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.armank.library.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для книги")
public class BookDto {

    @Schema(description = "Общее количество страниц книг")
    private Long totalPageVolume;
    @Schema(description = "Общее количество книг в базе")
    private Long totalBooks;

}
