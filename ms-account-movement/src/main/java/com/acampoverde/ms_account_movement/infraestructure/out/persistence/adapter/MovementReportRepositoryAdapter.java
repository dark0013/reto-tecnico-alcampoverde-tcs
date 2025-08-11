package com.acampoverde.ms_account_movement.infraestructure.out.persistence.adapter;


import com.acampoverde.ms_account_movement.domain.model.MovementReport;
import com.acampoverde.ms_account_movement.domain.port.out.IMovementReportRepositoryPort;
import com.acampoverde.ms_account_movement.infraestructure.out.persistence.dto.MovementReportDto;
import com.acampoverde.ms_account_movement.infraestructure.out.persistence.mapper.IMovementReportRepositoryMapper;
import com.acampoverde.ms_account_movement.infraestructure.out.persistence.repository.IMovementReportRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovementReportRepositoryAdapter implements IMovementReportRepositoryPort {

    private final IMovementReportRepository movementReportRepository;
    private final IMovementReportRepositoryMapper movementReportRepositoryMapper;

    public MovementReportRepositoryAdapter(IMovementReportRepository movementReportRepository, IMovementReportRepositoryMapper movementReportRepositoryMapper) {
        this.movementReportRepository = movementReportRepository;
        this.movementReportRepositoryMapper = movementReportRepositoryMapper;
    }

    @Override
    public List<MovementReport> findByAccountIdAndDate(Integer accountId,LocalDate startDate, LocalDate endDate) {
        List<MovementReportDto> movementEntity = movementReportRepository.findByAccountIdAndDate(accountId,startDate,endDate);
        return movementEntity.stream()
                .map(movementReportRepositoryMapper::toDomain)
                .collect(Collectors.toList());
    }

}