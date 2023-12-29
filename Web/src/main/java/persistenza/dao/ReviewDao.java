package persistenza.dao;

import application.model.Review;


import java.util.List;

public interface ReviewDao {

    public List<Review> findAll();
    public Review findByPrimaryKey(long id);

    public void saveOrUpdate(Review review);

    public void delete(Review review);

    public List<Review> findAllByIdWorker(long id);

}
