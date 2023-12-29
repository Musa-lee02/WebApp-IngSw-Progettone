package application.model;

import application.model.user.Customer;
import application.model.user.Worker;

import java.sql.Date;

public record Transaction(long id, Proposal proposal, float amount, Customer customer, Worker worker, Date date, String paymentMethod) {
}