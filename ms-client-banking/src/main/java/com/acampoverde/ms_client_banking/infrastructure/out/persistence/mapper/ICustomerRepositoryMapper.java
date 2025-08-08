package com.acampoverde.ms_client_banking.infrastructure.out.persistence.mapper;

import com.acampoverde.ms_client_banking.domain.model.Customer;
import com.acampoverde.ms_client_banking.infrastructure.out.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomerRepositoryMapper {
    Customer toDomain(CustomerEntity customerEntity);
    CustomerEntity toEntity(Customer customer);
}
