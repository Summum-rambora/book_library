package com.book_library.book_library.mappers;

import com.book_library.book_library.dto.UserEntityDto;
import com.book_library.book_library.models.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEntityMapper {

    UserEntityMapper Instance = Mappers.getMapper(UserEntityMapper.class);

    UserEntityDto toDto(UserEntity userEntity);
    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserEntityDto userEntityDto);
}
