package com.acampoverde.ms_account_movement.infraestructure.in.mapper;

import com.acampoverde.ms_account_movement.domain.model.Movement;
import com.acampoverde.ms_account_movement.domain.model.MovementReport;
import com.acampoverde.ms_account_movement.infraestructure.in.dto.MovementDto;
import com.acampoverde.ms_account_movement.infraestructure.in.dto.MovementReportDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMovementRepo {
    MovementReportDto toDto(MovementReport movement);


    MovementReport toDomain(MovementReportDto movement);
}
