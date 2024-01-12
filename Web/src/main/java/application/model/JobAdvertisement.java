package application.model;

import application.model.image.Image;
import application.model.user.User;
import persistenza.DBManager;

public class JobAdvertisement {

    private Long id;
    private String title;
    private String description;
    private String publicationDate;
    private String expirationDate;
    private String province;
    private WorkScope workScope;
    private User customer;
    private Image image = new Image();
    private boolean disponibile = false;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public WorkScope getWorkScope() {
        return workScope;
    }

    public void setWorkScope(WorkScope workScope) {
        this.workScope = workScope;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isDisponibile() {
        return disponibile;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    public void apply(){

        Proposal proposal = new Proposal();
        //DBManager.getInstance().getProposalDao().save(proposal);

    }




}
