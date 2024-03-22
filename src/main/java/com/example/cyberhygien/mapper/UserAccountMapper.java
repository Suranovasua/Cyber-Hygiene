package com.example.cyberhygien.mapper;

import com.example.cyberhygien.dto.UserAccountDTO;
import com.example.cyberhygien.entity.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserAccountMapper {

    UserAccountMapper INSTANCE = Mappers.getMapper(UserAccountMapper.class);


    UserAccountDTO entityToDTO(UserAccount userAccount);

    @Mapping(target = "userId", ignore = true)
    UserAccount dtoToEntity(UserAccountDTO userAccountDTO);
}
