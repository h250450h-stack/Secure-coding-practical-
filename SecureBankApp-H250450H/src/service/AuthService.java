package service;

import model.User;
import util.FileHandler;
import util.SecurityUtils;


public class AuthService {

    public boolean register(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.length() < 4) {
            System.out.println("Invalid input. Username cannot be empty and password must be at least 4 characters.");
            return false;
        }

        if (FileHandler.findUser(username) != null) {
            System.out.println(" Username already exists. Please choose another.");
            return false;
        }

        String hashedPassword = SecurityUtils.hashPassword(password);
        User newUser = new User(username, hashedPassword);
        FileHandler.saveUser(newUser);
        
        System.out.println("Registration successful! You can now log in.");
        return true;
    }

    public boolean login(String username, String password) {
        User user = FileHandler.findUser(username);
        if (user == null) {
            System.out.println(" User not found.");
            return false;
        }

        String hashedInput = SecurityUtils.hashPassword(password);
        if (user.getHashedPassword().equals(hashedInput)) {
            System.out.println(" Login successful. Welcome, " + username + "!");
            return true;
        } else {
            System.out.println(" Incorrect password.");
            return false;
        }
    }
}