package application.model.user;

import application.model.JobAdvertisement;
import application.model.Review;
import persistenza.DBManager;

import java.util.LinkedList;
import java.util.List;


public class Customer extends User {

    private List<JobAdvertisement> jobAdvertisements = new LinkedList<>();

    public List<JobAdvertisement> getJobAdvertisements() {
        return jobAdvertisements;
    }

    public void setJobAdvertisements(List<JobAdvertisement> jobAdvertisements) {
        this.jobAdvertisements = jobAdvertisements;
    }

    public boolean publishAd(){

        JobAdvertisement jobAdvertisement = new JobAdvertisement();
        //jobAdvertisements.add(jobAdvertisement);
        DBManager.getInstance().getJobAdvertisementDao().saveOrUpdate(jobAdvertisement);
        return true;
    }

    public boolean writeReview(){

        /*Review review = new Review();
        DBManager.getInstance().getReviewDao().saveOrUpdate(review);*/
        return true;
    }





}
