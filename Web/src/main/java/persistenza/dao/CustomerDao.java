package persistenza.dao;

import application.model.user.Customer;

import java.util.List;

public interface CustomerDao {

    public List<Customer> findAll();
    public Customer findByPrimaryKey(String email);

    public void saveOrUpdate(Customer customer);

    public void delete(Customer customer);
}
