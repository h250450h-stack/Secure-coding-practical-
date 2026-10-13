# Project Title: Secure Banking Application

## Description
A console-based banking application built in Java that demonstrates strong Object-Oriented Programming (OOP) principles and secure coding practices. The application allows users to register, log in, manage bank accounts, and perform transactions with data persistence.

## Student Details
- **Name:** [Your Name Here]
- **Registration Number:** [Your Reg Number Here]

## Features
- **User Authentication:** Secure login and registration system using SHA-256 password hashing.
- **Account Management:** Users can create new bank accounts and view account balances.
- **Transactions:** Users can deposit and withdraw funds securely.
- **Data Persistence:** All user credentials, account details, and transaction histories are saved to text files (`users.txt`, `accounts.txt`, `transactions.txt`) and loaded upon startup.
- **Robust Error Handling:** Input validation prevents crashes from invalid inputs (e.g., negative amounts, non-numeric inputs).

## OOP Principles Applied
- **Encapsulation:** Model classes (`User`, `Account`, `Transaction`) have private fields accessed via public getters/setters.
- **Abstraction:** Service classes (`AuthService`, `BankService`) hide complex business logic and file handling from the main UI.
- **Single Responsibility Principle:** `FileHandler` is solely responsible for file I/O, while `SecurityUtils` handles hashing.
