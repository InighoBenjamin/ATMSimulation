# 🏧 Mini ATM Simulation

A comprehensive console-based ATM simulation built in Java, demonstrating Object-Oriented Programming principles with persistent data storage.

## 📋 Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Class Architecture](#class-architecture)
- [File Storage](#file-storage)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

- **💰 Balance Inquiry**: Check current account balance
- **📥 Deposit Money**: Add funds to your account with validation
- **📤 Withdraw Money**: Remove funds with insufficient balance protection
- **📊 Transaction History**: View detailed transaction records with timestamps
- **💾 Data Persistence**: Account balance and transaction history saved to files
- **🔐 User Authentication**: Simple account verification system
- **✅ Input Validation**: Robust error handling for all user inputs
- **🎨 Professional UI**: Clean, formatted console interface

## 🛠️ Technologies Used

- **Java 11+** (uses `String.repeat()` method)
- **File I/O** for data persistence
- **Object-Oriented Programming** principles
- **Collections Framework** (ArrayList)
- **Java Time API** for timestamps

## 📋 Prerequisites

- **Java JDK 11 or higher** installed on your system
- Command line interface (Terminal/Command Prompt)
- Text editor or IDE (optional but recommended)

### Checking Java Version
```bash
java -version
javac -version
```

If you need to install Java, download from:
- [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
- [OpenJDK](https://openjdk.org/)

## 🚀 Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/mini-atm-simulation.git
   cd mini-atm-simulation
   ```

2. **Compile the Java file**
   ```bash
   javac ATMSimulation.java
   ```

3. **Run the application**
   ```bash
   java ATMSimulation
   ```

## 💻 Usage

1. **Start the application**
   ```bash
   java ATMSimulation
   ```

2. **Enter your credentials**
   - Account Number: Enter any account number
   - Account Holder Name: Enter your name

3. **Use ATM features**
   - Select from the main menu (1-5)
   - Follow on-screen prompts
   - Data is automatically saved after each transaction

### Sample Session
```
**************************************************
*            WELCOME TO MINI ATM            *
**************************************************

Enter Account Number: 12345
Enter Account Holder Name: John Doe

Authentication successful!
Welcome, John Doe!
----------------------------------------

========================================
           ATM MAIN MENU
========================================
1. Check Balance
2. Deposit Money
3. Withdraw Money
4. Transaction History
5. Exit
========================================
Please select an option (1-5): 
```

## 📁 Project Structure

```
mini-atm-simulation/
│
├── ATMSimulation.java      # Main application file
├── balance.txt            # Stores current balance (auto-generated)
├── transactions.txt       # Stores transaction history (auto-generated)
├── README.md             # Project documentation
└── .gitignore           # Git ignore file
```

## 🏗️ Class Architecture

### 🔹 Transaction Class
- Represents individual transactions
- Stores transaction type, amount, timestamp, and resulting balance
- Provides formatted string representation

### 🔹 Account Class
- Manages account operations and data
- Handles file I/O for balance and transaction persistence
- Validates deposits and withdrawals
- Maintains transaction history

### 🔹 ATM Class
- Manages user interface and interactions
- Handles menu navigation and user input
- Provides formatted display for all operations
- Includes input validation and error handling

### 🔹 ATMSimulation Class
- Main application entry point
- Initializes and starts the ATM system

## 💾 File Storage

The application uses two files for data persistence:

- **`balance.txt`**: Stores the current account balance
- **`transactions.txt`**: Stores complete transaction history with timestamps

These files are automatically created and updated with each transaction.

## 📸 Screenshots

### Main Menu
```
========================================
           ATM MAIN MENU
========================================
1. Check Balance
2. Deposit Money
3. Withdraw Money
4. Transaction History
5. Exit
========================================
```

### Transaction History
```
----------------------------------------------------------------------
                    TRANSACTION HISTORY
----------------------------------------------------------------------
Type       | Amount   | Date & Time         | Balance After
----------------------------------------------------------------------
DEPOSIT    |   500.00 | 2024-01-15 10:30:25 | Balance: $500.00
WITHDRAW   |   100.00 | 2024-01-15 11:45:10 | Balance: $400.00
DEPOSIT    |   250.00 | 2024-01-15 14:20:35 | Balance: $650.00
----------------------------------------------------------------------
```

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Commit your changes**
   ```bash
   git commit -m 'Add some amazing feature'
   ```
4. **Push to the branch**
   ```bash
   git push origin feature/amazing-feature
   ```
5. **Open a Pull Request**

### Contribution Ideas
- Add PIN-based authentication
- Implement multiple account support
- Add account transfer functionality
- Create GUI version using JavaFX/Swing
- Add unit tests
- Implement encryption for data files
- Add transaction limits and daily withdrawal limits

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🎓 Learning Objectives

This project demonstrates:
- **Object-Oriented Programming** concepts (Encapsulation, Inheritance, Polymorphism)
- **File I/O operations** in Java
- **Exception handling** and input validation
- **Collections Framework** usage
- **Java Time API** for timestamp management
- **Console application** development
- **Data persistence** techniques

## 🐛 Known Issues

- Transaction history shows only basic formatting in console
- No data encryption for stored files
- Single account support only

## 🔮 Future Enhancements

- [ ] Multi-account support
- [ ] PIN-based authentication
- [ ] Account-to-account transfers
- [ ] Transaction limits
- [ ] GUI interface
- [ ] Database integration
- [ ] Network connectivity for remote ATM access

## 📞 Support

If you encounter any issues or have questions:
- Open an issue on GitHub
- Check existing issues for solutions
- Review the code comments for implementation details

---

**Made with ❤️ by [Your Name]**

*Don't forget to ⭐ star this repository if you found it helpful!*
