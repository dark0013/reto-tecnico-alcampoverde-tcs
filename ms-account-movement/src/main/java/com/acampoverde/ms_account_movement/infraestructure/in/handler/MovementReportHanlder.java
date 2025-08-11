package com.acampoverde.ms_account_movement.infraestructure.in.handler;

import com.acampoverde.ms_account_movement.application.service.MovementService;
import com.acampoverde.ms_account_movement.domain.model.MovementReport;
import com.acampoverde.ms_account_movement.infraestructure.in.dto.MovementReportDto;
import com.acampoverde.ms_account_movement.infraestructure.in.mapper.IMovementRepo;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class MovementReportHanlder {

    private final MovementService movementService;
    private final IMovementRepo movementRepo;

    public List<MovementReportDto> findByAccountIdAndDate(Integer accountId,LocalDate startDate, LocalDate endDate) {
        List<MovementReport> obj = movementService.findByAccountIdAndDate(accountId,startDate, endDate);

        return obj.stream()
                .map(movementRepo::toDto)
                .collect(Collectors.toList());
    }

}
