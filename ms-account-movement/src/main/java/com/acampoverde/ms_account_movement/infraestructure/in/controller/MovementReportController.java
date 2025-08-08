package com.acampoverde.ms_account_movement.infraestructure.in.controller;

import com.acampoverde.ms_account_movement.application.service.MovementService;
import com.acampoverde.ms_account_movement.domain.model.MovementReport;
import com.acampoverde.ms_account_movement.infraestructure.in.dto.MovementReportDto;
import com.acampoverde.ms_account_movement.infraestructure.in.handler.MovementHandler;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("v1/movements/report")
public class MovementReportController {

    private final MovementService movementService;

    public MovementReportController(MovementService movementService) {
        this.movementService = movementService;
    }

    @GetMapping("/v1/movements/report")
    public List<MovementReport> getMovementReportByAccountIdAndDate(
            @RequestParam Integer accountId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return movementService.getMovementReportByAccountIdAndDate(accountId, startDate, endDate);
    }
    }