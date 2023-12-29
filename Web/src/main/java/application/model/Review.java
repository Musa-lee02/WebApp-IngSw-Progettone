package application.model;

import application.model.user.User;

public record Review(long id, String title, String description, int rating, User customer, User worker) {
}

