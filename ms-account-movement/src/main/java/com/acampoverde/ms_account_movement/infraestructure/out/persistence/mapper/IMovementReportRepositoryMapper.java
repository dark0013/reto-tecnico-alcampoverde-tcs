package com.acampoverde.ms_account_movement.infraestructure.out.persistence.mapper;


import com.acampoverde.ms_account_movement.domain.model.MovementReport;

import com.acampoverde.ms_account_movement.infraestructure.in.dto.MovementReportDto;
import com.acampoverde.ms_account_movement.infraestructure.out.persistence.entity.MovementReportEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMovementReportRepositoryMapper {
    MovementReport toDomain(MovementReportDto dto);
}