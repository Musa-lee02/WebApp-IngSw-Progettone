package pattern.skillmatchbackend.model;

public class ValidationService {

    public static boolean validateEmail(String email) {
        return email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static boolean validatePasswordUpperLetter(String password) {
        return password.matches("[A-Z]+");
    }

    public static boolean validatePasswordNumber(String password) {
        return password.matches("[0-9]+");
    }

    public static boolean validatePasswordSpecialChar(String password) {
        return password.matches("[!@#$%^&*()_+=-]+");
    }

    public static boolean validatePasswordLength(String password) {
        return password.length() >= 8;
    }


}
