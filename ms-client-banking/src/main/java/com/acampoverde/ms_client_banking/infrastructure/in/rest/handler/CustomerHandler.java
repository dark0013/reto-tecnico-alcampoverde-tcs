package com.acampoverde.ms_client_banking.infrastructure.in.rest.handler;

import com.acampoverde.ms_client_banking.domain.port.in.ICustomerServicePort;
import com.acampoverde.ms_client_banking.infrastructure.in.rest.dto.CustomerDto;
import com.acampoverde.ms_client_banking.infrastructure.in.rest.mapper.ICustomerMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerHandler {
    private final ICustomerServicePort customerService;
    private final ICustomerMapper customerMapper;

    public CustomerHandler(ICustomerServicePort customerService, ICustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }


    public List<CustomerDto> findAllCustomers() {
        return customerService.findAllCustomers()
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }


    public CustomerDto findCustomerById(Integer id) {
        return customerMapper.toDto(customerService.findCustomerById(id)) ;
    }


    public CustomerDto saveCustomer(CustomerDto customer) {
        return customerMapper.toDto(customerService.saveCustomer(customerMapper.toDomain(customer)));
    }


    public CustomerDto updateCustomer(CustomerDto customer) {
        return customerMapper.toDto(customerService.saveCustomer(customerMapper.toDomain(customer)));
    }

    public void deleteCustomer(Integer id) {
        customerService.deleteCustomer(id);
    }
}

