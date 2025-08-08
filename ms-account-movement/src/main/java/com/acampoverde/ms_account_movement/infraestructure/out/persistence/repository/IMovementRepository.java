package com.acampoverde.ms_account_movement.infraestructure.out.persistence.repository;

import com.acampoverde.ms_account_movement.infraestructure.out.persistence.entity.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovementRepository extends JpaRepository<MovementEntity, Integer> {

}
