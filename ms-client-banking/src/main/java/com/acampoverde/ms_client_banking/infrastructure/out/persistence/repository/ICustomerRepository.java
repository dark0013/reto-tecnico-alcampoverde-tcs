package com.acampoverde.ms_client_banking.infrastructure.out.persistence.repository;

import com.acampoverde.ms_client_banking.infrastructure.out.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<CustomerEntity,Integer> {
}
