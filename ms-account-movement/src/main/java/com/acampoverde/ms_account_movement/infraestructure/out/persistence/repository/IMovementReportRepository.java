package com.acampoverde.ms_account_movement.infraestructure.out.persistence.repository;


import com.acampoverde.ms_account_movement.infraestructure.out.persistence.dto.MovementReportDto;
import com.acampoverde.ms_account_movement.infraestructure.out.persistence.entity.MovementEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IMovementReportRepository extends Repository<MovementEntity, Integer> {


    @Query("select new com.acampoverde.ms_account_movement.infraestructure.out.persistence.dto.MovementReportDto(" +
            "m.date, c.name, a.accountNumber, a.accountType, a.availableBalance, a.status, m.transactionAmount, m.availableBalance) " +
            "from CustomerEntity c " +
            "join AccountEntity a on c.id = a.customerId " +
            "join MovementEntity m on a.accountId = m.account.accountId " +
            "where a.accountId = :accountId and m.date >= :startDate and m.date <= :endDate"
    )
    List<MovementReportDto> findByAccountIdAndDate(
            @Param("accountId") Integer accountId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}