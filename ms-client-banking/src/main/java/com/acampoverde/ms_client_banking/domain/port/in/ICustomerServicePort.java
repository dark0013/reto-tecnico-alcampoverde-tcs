package com.acampoverde.ms_client_banking.domain.port.in;
import com.acampoverde.ms_client_banking.domain.model.Customer;

import java.util.List;

public interface ICustomerServicePort {
    List<Customer> findAllCustomers();

    Customer findCustomerById(Integer id);

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Integer id);
}
