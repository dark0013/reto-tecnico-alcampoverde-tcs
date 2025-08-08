package com.acampoverde.ms_client_banking.infrastructure.in.rest.mapper;

import com.acampoverde.ms_client_banking.domain.model.Customer;
import com.acampoverde.ms_client_banking.infrastructure.in.rest.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {
    CustomerDto toDto(Customer customer);

    Customer toDomain(CustomerDto customer);
}

