package com.acampoverde.ms_account_movement.infraestructure.in.mapper;

import com.acampoverde.ms_account_movement.domain.model.Movement;
import com.acampoverde.ms_account_movement.infraestructure.in.dto.MovementDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMovementMapper {

    @InheritInverseConfiguration
    MovementDto toDto(Movement transaction);

    @Mapping(target = "account.accountId", source = "accountId")
    Movement toDomain(MovementDto transaction);
}