package com.acampoverde.ms_account_movement.infraestructure.in.mapper;

import com.acampoverde.ms_account_movement.domain.model.Account;
import com.acampoverde.ms_account_movement.infraestructure.in.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAccountMapper {

    @Mapping(target = "movements", ignore = true)
    Account toDomain(AccountDto account);

    AccountDto toDto(Account account);

}

