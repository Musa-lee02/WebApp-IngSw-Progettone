package persistenza.dao.postgres;

import persistenza.dao.ProposalDao;
import application.model.Proposal;

import java.sql.Connection;
import java.util.List;

public class ProposalDaoPostgres implements ProposalDao {

    Connection conn;

    public ProposalDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Proposal> findAll() {
        return null;
    }

    @Override
    public Proposal findByPrimaryKey(long id) {
        return null;
    }

    @Override
    public void saveOrUpdate(Proposal proposal) {

    }

    @Override
    public void delete(Proposal proposal) {

    }
}
