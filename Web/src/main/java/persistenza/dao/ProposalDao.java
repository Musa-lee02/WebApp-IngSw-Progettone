package persistenza.dao;

import application.model.Proposal;

import java.util.List;

public interface ProposalDao {

    public List<Proposal> findAll();
    public Proposal findByPrimaryKey(long id);

    public void saveOrUpdate(Proposal proposal);

    public void delete(Proposal proposal);


}
