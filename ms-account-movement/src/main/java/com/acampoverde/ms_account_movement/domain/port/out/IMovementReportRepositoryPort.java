package com.acampoverde.ms_account_movement.domain.port.out;

import com.acampoverde.ms_account_movement.domain.model.MovementReport;
import java.time.LocalDate;
import java.util.List;

public interface IMovementReportRepositoryPort {
    List<MovementReport> findByAccountIdAndDate(Integer accountId, LocalDate startDate, LocalDate endDate);
}