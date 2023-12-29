package application.model.user;

import application.model.JobAdvertisement;
import application.model.Proposal;
import application.model.WorkScope;
import persistenza.DBManager;


import java.util.LinkedList;
import java.util.List;

public class Worker extends User {


    private WorkScope workScope;
    private List<Proposal> proposal = new LinkedList<>();

    public WorkScope getWorkScope() {
        return workScope;
    }

    public void setWorkScope(WorkScope workScope) {
        this.workScope = workScope;
    }

    public List<Proposal> getProposal() {
        return proposal;
    }

    public void setProposal(List<Proposal> proposal) {
        this.proposal = proposal;
    }

    public List<JobAdvertisement> visualizzaAnnunciDisponibiliPerMe(){
        return DBManager.getInstance().getJobAdvertisementDao().localJobSearch(workScope,province);
    }


}
