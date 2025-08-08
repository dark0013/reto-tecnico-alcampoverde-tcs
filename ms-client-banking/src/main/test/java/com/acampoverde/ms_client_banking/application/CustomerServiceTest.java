package com.acampoverde.ms_client_banking.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.acampoverde.ms_client_banking.application.exception.CustomerNotFoundException;
import com.acampoverde.ms_client_banking.application.service.CustomerService;
import com.acampoverde.ms_client_banking.domain.model.Customer;
import com.acampoverde.ms_client_banking.domain.port.out.ICustomerRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class CustomerServiceTest {

    @Mock
    private ICustomerRepositoryPort customerRepositoryPort;

    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerService(customerRepositoryPort);
    }

    @Test
    public void testFindAllCustomers() {
        Customer c1 = new Customer();
        c1.setId(1);
        c1.setName("Alice");
        Customer c2 = new Customer();
        c2.setId(2);
        c2.setName("Bob");
        when(customerRepositoryPort.findAllCustomers()).thenReturn(List.of(c1, c2));

        List<Customer> customers = customerService.findAllCustomers();

        assertNotNull(customers);
        assertEquals(2, customers.size());
        verify(customerRepositoryPort, times(1)).findAllCustomers();
    }

    @Test
    public void testFindCustomerById_Found() {
        Customer c = new Customer();
        c.setId(1);
        c.setName("Alice");
        when(customerRepositoryPort.findCustomerById(1)).thenReturn(Optional.of(c));

        Customer found = customerService.findCustomerById(1);

        assertNotNull(found);
        assertEquals("Alice", found.getName());
        verify(customerRepositoryPort, times(1)).findCustomerById(1);
    }

    @Test
    public void testFindCustomerById_NotFound() {
        when(customerRepositoryPort.findCustomerById(99)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
            customerService.findCustomerById(99);
        });

        assertEquals("NO CUSTOMERS FOUND", exception.getMessage());
        verify(customerRepositoryPort, times(1)).findCustomerById(99);
    }

    @Test
    public void testSaveCustomer() {
        Customer c = new Customer();
        c.setName("New Customer");
        when(customerRepositoryPort.saveCustomer(c)).thenReturn(c);

        Customer saved = customerService.saveCustomer(c);

        assertNotNull(saved);
        assertEquals("New Customer", saved.getName());
        verify(customerRepositoryPort, times(1)).saveCustomer(c);
    }

    @Test
    public void testUpdateCustomer() {
        Customer c = new Customer();
        c.setId(1);
        c.setName("Updated Customer");
        when(customerRepositoryPort.updateCustomer(c)).thenReturn(c);

        Customer updated = customerService.updateCustomer(c);

        assertNotNull(updated);
        assertEquals("Updated Customer", updated.getName());
        verify(customerRepositoryPort, times(1)).updateCustomer(c);
    }

    @Test
    public void testDeleteCustomer() {
        doNothing().when(customerRepositoryPort).deleteCustomer(1);

        customerService.deleteCustomer(1);

        verify(customerRepositoryPort, times(1)).deleteCustomer(1);
    }
}
