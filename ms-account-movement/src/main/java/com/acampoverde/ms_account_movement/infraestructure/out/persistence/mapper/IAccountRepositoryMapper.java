package com.acampoverde.ms_account_movement.infraestructure.out.persistence.mapper;

import com.acampoverde.ms_account_movement.domain.model.Account;
import com.acampoverde.ms_account_movement.infraestructure.out.persistence.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAccountRepositoryMapper {

    @Mapping(target = "movements", ignore = true)
    Account toDomain(AccountEntity account);

    @Mapping(target = "movements", ignore = true)
    AccountEntity toEntity(Account account);
}
