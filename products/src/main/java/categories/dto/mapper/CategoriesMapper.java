package categories.dto.mapper;

import categories.dto.CategorieResponseDto;
import es.ecoshop.api.genereted.jooq.tables.records.CategoriesRecord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoriesMapper {
    public CategoriesMapper INSTANCE = Mappers.getMapper(CategoriesMapper.class);

    CategorieResponseDto categorieToDto(CategoriesRecord reg);
}
