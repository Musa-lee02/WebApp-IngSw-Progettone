package application.model;

import application.model.user.User;
import persistenza.DBManager;

public class Proposal {

    long id;
    JobAdvertisement jobAdvertisement;
    User worker;
    //Chat chat;
    boolean state;


    public void acceptProposal() {




    }

    public void makePayment(){

        if(state)
            return;

       // DBManager.getInstance().getTransactionDao().saveOrUpdate(new Transaction());

    }

    



}
