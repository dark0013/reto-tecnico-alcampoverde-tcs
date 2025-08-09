package com.acampoverde.ms_account_movement.infraestructure.out.persistence.adapter;


import com.acampoverde.ms_account_movement.domain.model.MovementReport;
import com.acampoverde.ms_account_movement.domain.port.out.IMovementReportRepositoryPort;
import com.acampoverde.ms_account_movement.infraestructure.in.dto.MovementReportDto;
import com.acampoverde.ms_account_movement.infraestructure.out.persistence.repository.IMovementReportRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovementReportRepositoryAdapter implements IMovementReportRepositoryPort {

    private final IMovementReportRepository movementReportRepository;

    public MovementReportRepositoryAdapter(IMovementReportRepository movementReportRepository) {
        this.movementReportRepository = movementReportRepository;
    }

    @Override
    public List<MovementReport> findByAccountIdAndDate(Integer accountId, LocalDate startDate, LocalDate endDate) {
        //List<MovementReportDto> dtos = movementReportRepository.findByAccountIdAndDate(accountId, startDate, endDate);
        List<MovementReportDto> dtos = List.of(); // Placeholder for actual repository call
        return dtos.stream()
                .map(dto -> MovementReport.builder()
                        .date(dto.getDate())
                        .accountNumber(dto.getAccountNumber())
                        .accountType(dto.getAccountType())
                        .balance(dto.getBalance())
                        .status(dto.getStatus())
                        .amount(dto.getAmount())
                        .initialBalance(dto.getInitialBalance())
                        .build())
                .collect(Collectors.toList());
    }
}