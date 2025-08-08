package com.acampoverde.ms_client_banking.application.service;

import com.acampoverde.ms_client_banking.application.exception.CustomerNotFoundException;
import com.acampoverde.ms_client_banking.domain.model.Customer;
import com.acampoverde.ms_client_banking.domain.port.in.ICustomerServicePort;
import com.acampoverde.ms_client_banking.domain.port.out.ICustomerRepositoryPort;

import java.util.List;

public class CustomerService implements ICustomerServicePort {
    private final ICustomerRepositoryPort customerRepositoryPort;

    public CustomerService(ICustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return this.customerRepositoryPort.findAllCustomers();
    }

    @Override
    public Customer findCustomerById(Integer id) {
        return this.customerRepositoryPort.findCustomerById(id).orElseThrow(() -> new CustomerNotFoundException("NO CUSTOMERS FOUND"));
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return this.customerRepositoryPort.saveCustomer(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return this.customerRepositoryPort.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        this.customerRepositoryPort.deleteCustomer(id);
    }


}
