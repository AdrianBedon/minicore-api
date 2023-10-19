package com.arbc.development.mvc.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arbc.development.mvc.dto.SignUpDto;
import com.arbc.development.mvc.dto.UserDto;
import com.arbc.development.mvc.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}