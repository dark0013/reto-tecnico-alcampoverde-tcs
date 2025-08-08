package com.acampoverde.ms_account_movement.infraestructure.out.persistence.mapper;

import com.acampoverde.ms_account_movement.domain.model.Movement;
import com.acampoverde.ms_account_movement.infraestructure.out.persistence.entity.MovementEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccountMovementRepositoryMapper {

    Movement toDomain(MovementEntity account);

    MovementEntity toEntity(Movement account);
}

