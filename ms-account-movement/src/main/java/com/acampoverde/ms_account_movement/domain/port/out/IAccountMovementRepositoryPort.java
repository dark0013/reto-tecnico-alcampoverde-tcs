package com.acampoverde.ms_account_movement.domain.port.out;

import com.acampoverde.ms_account_movement.domain.model.Movement;

import java.util.List;
import java.util.Optional;

public interface IAccountMovementRepositoryPort {
    Optional<Movement> findById(Integer id);

    List<Movement> findAll();

    Movement transaction(Movement transaction);
    void deactivateMovement(Integer movementId);
}
