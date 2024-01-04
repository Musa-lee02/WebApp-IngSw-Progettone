package persistenza.dao;


import application.model.JobAdvertisement;
import application.model.WorkScope;


import java.util.List;

public interface JobAdvertisementDao {

    public List<JobAdvertisement> localJobSearch(WorkScope workScope, String province);
    public List<JobAdvertisement> findAll();
    public JobAdvertisement findByPrimaryKey(long id);
    public void saveOrUpdate(JobAdvertisement jobAdvertisement);
    public void delete(JobAdvertisement jobAdvertisementDao);
    public List<JobAdvertisement> findByEmail(String email);
}
