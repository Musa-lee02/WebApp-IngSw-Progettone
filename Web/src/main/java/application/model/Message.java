package application.model;

import application.model.contenuto.Contenuto;
import application.model.user.User;

import java.sql.Date;

public record Message(long id, Contenuto contenuto, Date date,Proposal proposal,boolean who) {
}
