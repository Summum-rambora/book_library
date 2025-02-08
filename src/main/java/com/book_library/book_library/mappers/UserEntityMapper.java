package com.book_library.book_library.mappers;

import com.book_library.book_library.dto.UserEntityDto;
import com.book_library.book_library.models.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface UserEntityMapper {
    UserEntityDto toDto(UserEntity userEntity);
    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserEntityDto userEntityDto);
}
