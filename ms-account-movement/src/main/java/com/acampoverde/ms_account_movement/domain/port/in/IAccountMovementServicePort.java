package com.acampoverde.ms_account_movement.domain.port.in;

import com.acampoverde.ms_account_movement.domain.model.Movement;

import java.util.List;

public interface IAccountMovementServicePort {

    Movement findById(Integer id);

    List<Movement> findAll();

    Movement transaction(Movement transaction);

    void deactivateMovement(Integer movementId);
}