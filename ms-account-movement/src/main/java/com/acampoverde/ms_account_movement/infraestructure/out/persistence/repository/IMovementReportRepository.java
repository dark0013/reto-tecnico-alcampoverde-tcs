package com.acampoverde.ms_account_movement.infraestructure.out.persistence.repository;

import com.acampoverde.ms_account_movement.infraestructure.in.dto.MovementReportDto;
import com.acampoverde.ms_account_movement.infraestructure.out.persistence.entity.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IMovementReportRepository extends JpaRepository<MovementEntity, Integer> {

    @Query("SELECT new com.acampoverde.ms_account_movement.infraestructure.in.dto.MovementReportDto(" +
            "m.date, a.accountNumber, a.accountType, a.initialBalance, m.balance, m.amount, m.status) " +
            "FROM MovementEntity m JOIN m.account a " +
            "WHERE a.accountId = :accountId AND m.date BETWEEN :startDate AND :endDate")
    List<MovementReportDto> findByAccountIdAndDate(@Param("accountId") Integer accountId,
                                                   @Param("startDate") LocalDate startDate,
                                                   @Param("endDate") LocalDate endDate);
}