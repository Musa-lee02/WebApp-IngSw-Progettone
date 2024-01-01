package persistenza.dao;

import application.model.Transaction;

import java.util.List;

public interface TransactionDao {

    public List<Transaction> findAll();
    public Transaction findByPrimaryKey(long id);

    public void saveOrUpdate(Transaction transaction);

    public void delete(Transaction Transaction);

    public List<Transaction> findByEmail(String email);
    
}
