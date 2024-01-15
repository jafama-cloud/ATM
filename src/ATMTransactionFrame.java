import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

// Represents the main frame for ATM transactions
public class ATMTransactionFrame extends JFrame {
    private ATMUser currentUser;

    // Constructor for the ATMTransactionFrame
    public ATMTransactionFrame(ATMUser user) {
        this.currentUser = user;

        // Frame setup
        setTitle("Choose a Transaction");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout()); // Use BorderLayout

        // Set background color
        getContentPane().setBackground(new Color(200, 200, 200)); // Light gray background

        // Title panel setup
        JPanel titlePanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for more flexibility
        titlePanel.setBackground(new Color(200, 200, 200)); // Light gray background
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left

        // Title label setup
        JLabel titleLabel = new JLabel("Choose a Transaction  ", SwingConstants.LEFT); // Left-align text
        titleLabel.setFont(new Font("Arial", Font.BOLD, 44)); // Increase font size
        titlePanel.add(titleLabel, gbc);

        // Create an icon and add it to the panel to the right of the label
        Icon icon = createIcon("/transactions.png", 160, 160);
        if (icon != null) {
            gbc.gridx = 1;
            gbc.insets = new Insets(0, 20, 0, 0); // Adjust insets for spacing
            JLabel iconLabel = new JLabel(icon);
            titlePanel.add(iconLabel, gbc); // Align icon to the right
        }

        // Info label setup
        JLabel infoLabel = new JLabel("<html>Press <font color='#C83232'><b>Cancel</b></font> on the digital board to exit</html>");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        infoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        gbc.gridx = 0;
        gbc.gridy = 1;
        titlePanel.add(infoLabel, gbc);

        add(titlePanel, BorderLayout.NORTH); // Add the titlePanel to the top

        // Button panel setup
        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // 3 rows, 2 columns
        buttonPanel.setBackground(new Color(100, 100, 100));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Button setup
        JButton withdrawalButton = new JButton("Quick Withdrawal");
        JButton cashButton = new JButton("Cash Transaction");
        JButton balanceButton = new JButton("Balance Inquiry");
        JButton accountStatementButton = new JButton("Account Statement");
        JButton paymentButton = new JButton("Payments");
        JButton onlineBankingButton = new JButton("Online Banking");
        JButton otherServicesButton = new JButton("Other Services");
        JButton cancelButton = new JButton("Cancel");

        // Set preferred size for buttons
        withdrawalButton.setPreferredSize(new Dimension(122, 40));
        cashButton.setPreferredSize(new Dimension(122, 40));
        balanceButton.setPreferredSize(new Dimension(122, 40));
        accountStatementButton.setPreferredSize(new Dimension(122, 40));
        paymentButton.setPreferredSize(new Dimension(122, 40));
        onlineBankingButton.setPreferredSize(new Dimension(122, 40));
        otherServicesButton.setPreferredSize(new Dimension(122, 40));
        cancelButton.setPreferredSize(new Dimension(122, 40));
        
        // Increase font size for button labels
        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        withdrawalButton.setFont(buttonFont);
        cashButton.setFont(buttonFont);
        balanceButton.setFont(buttonFont);
        accountStatementButton.setFont(buttonFont);
        paymentButton.setFont(buttonFont);
        onlineBankingButton.setFont(buttonFont);
        otherServicesButton.setFont(buttonFont);
        cancelButton.setFont(buttonFont);

        // Add buttons to the panel
        buttonPanel.add(withdrawalButton);
        buttonPanel.add(cashButton);
        buttonPanel.add(balanceButton);
        buttonPanel.add(accountStatementButton); 
        buttonPanel.add(paymentButton);
        buttonPanel.add(onlineBankingButton); 
        buttonPanel.add(otherServicesButton); 
        buttonPanel.add(cancelButton);

        // Set button colors
        withdrawalButton.setBackground(new Color(42, 50, 75)); // Blue color 69, 105, 14
        cashButton.setBackground(new Color(42, 50, 75));   // Green color
        balanceButton.setBackground(new Color(42, 50, 75));   // Yellow color
        accountStatementButton.setBackground(new Color(42, 50, 75));    // Blue color
        paymentButton.setBackground(new Color(42, 50, 75));    // Black color
        onlineBankingButton.setBackground(new Color(42, 50, 75));    // Black color
        otherServicesButton.setBackground(new Color(42, 50, 75));    // Black color
        cancelButton.setBackground(new Color(200, 200, 200)); // Red color

        // Set button text color to white
        withdrawalButton.setForeground(Color.WHITE);
        cashButton.setForeground(Color.WHITE);
        balanceButton.setForeground(Color.WHITE);
        accountStatementButton.setForeground(Color.WHITE);
        paymentButton.setForeground(Color.WHITE);
        onlineBankingButton.setForeground(Color.WHITE);
        otherServicesButton.setForeground(Color.WHITE);
        cancelButton.setForeground(new Color(200, 50, 50)); // RED

        // Add action listeners to buttons
        withdrawalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performQuickWithdrawal(); // Call quick withdrawal method
            }
        });

        cashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performCashTransaction(); // Call cash transaction method
            }
        });
        
        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performBalanceInquiry(); // Call balance inquiry method
            }
        });
        
        accountStatementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performAccountStatement(); // Call account statement method
            }
        });

        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performPayments(); // Call payments method
            }
        });

        onlineBankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOnlineBanking(); // Implement this method
            }
        });
        
        otherServicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOtherServices(); // Implement this method
            }
        });        

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the frame when Cancel is pressed
                ATMFrame atmFrame = new ATMFrame();     // An instance of the main frame.
                atmFrame.setVisible(true);
            }
        });

        add(buttonPanel, BorderLayout.CENTER); // Add button panel to the center
        // Finalize frame setup
        pack(); 
        setLocationRelativeTo(null);
    }

    // Create an icon with the specified path, width, and height
    private Icon createIcon(String path, int width, int height) {
        // Load the icon using the resource URL
        java.net.URL imageURL = getClass().getResource(path);

        // Check if the URL is not null
        if (imageURL != null) {
            ImageIcon originalIcon = new ImageIcon(imageURL);
            Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } else {
            // Handle the case where the icon cannot be loaded
            System.err.println("Icon not found: " + path);
            return null;
        }
    }

    // Method to check if the transaction amount is within maintaining balance limit
    private boolean isWithinMaintainingBalanceLimit(double transactionAmount) {
        double remainingBalance = currentUser.getBalance() - transactionAmount;
        return remainingBalance >= currentUser.getMaintainingBalance();
    }

    private void performQuickWithdrawal() {
        // Prompt the user for withdrawal amount
        String withdrawalAmountString = JOptionPane.showInputDialog(
                ATMTransactionFrame.this,
                "Enter withdrawal amount:",
                "Withdrawal",
                JOptionPane.PLAIN_MESSAGE
        );
    
        if (withdrawalAmountString != null && !withdrawalAmountString.isEmpty()) {
            try {
                double withdrawalAmount = Double.parseDouble(withdrawalAmountString);
    
                // Check if the withdrawal amount is greater than 0
                if (withdrawalAmount > 0) {
                    if (isWithinMaintainingBalanceLimit(withdrawalAmount)) {
                        // Calculate balance_before and balance_after
                        double balanceBefore = currentUser.getBalance();
                        double balanceAfter = balanceBefore - withdrawalAmount;
    
                        // Record the withdrawal transaction
                        recordTransaction("withdrawal", withdrawalAmount, balanceBefore, balanceAfter);
    
                        // Perform the withdrawal logic (update balance in the database)
                        try (Connection connection = DatabaseConnector.connect()) {
                            String updateQuery = "UPDATE users SET balance = balance - ? WHERE id = ?";
                            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                                preparedStatement.setDouble(1, withdrawalAmount);
                                preparedStatement.setInt(2, currentUser.getId());
                                preparedStatement.executeUpdate();
    
                                // Update the user's balance locally
                                currentUser.setBalance(currentUser.getBalance() - withdrawalAmount);
    
                                // Display a receipt
                                String receiptMessage = "Withdrawal successful\nAmount: Php " + withdrawalAmount;
                                JOptionPane.showMessageDialog(
                                        ATMTransactionFrame.this,
                                        receiptMessage,
                                        "Withdrawal Receipt",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(
                                    ATMTransactionFrame.this,
                                    "Error processing withdrawal",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                ATMTransactionFrame.this,
                                "Withdrawal failed. The maintaining balance requirement is Php " + currentUser.getMaintainingBalance(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            ATMTransactionFrame.this,
                            "Invalid withdrawal amount. Please enter an amount greater than 0.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        ATMTransactionFrame.this,
                        "Invalid withdrawal amount. Please enter a valid number.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }    
    
    private void performCashTransaction() {
        // Prompt the user for the deposit amount
        String depositAmountString = JOptionPane.showInputDialog(
                ATMTransactionFrame.this,
                "Enter deposit amount in cash:",
                "Cash Transaction",
                JOptionPane.QUESTION_MESSAGE
        );
    
        if (depositAmountString != null && !depositAmountString.isEmpty()) {
            try {
                double depositAmount = Double.parseDouble(depositAmountString);
    
                // Check if the deposit amount is greater than 0
                if (depositAmount > 0) {
                    // Calculate balance_before and balance_after
                    double balanceBefore = currentUser.getBalance();
                    double balanceAfter = balanceBefore + depositAmount;
    
                    recordTransaction("cash_transaction", depositAmount, balanceBefore, balanceAfter);
    
                    // Perform the deposit logic (update balance in the database)
                    try (Connection connection = DatabaseConnector.connect()) {
                        String updateQuery = "UPDATE users SET balance = balance + ? WHERE id = ?";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                            preparedStatement.setDouble(1, depositAmount);
                            preparedStatement.setInt(2, currentUser.getId());
                            preparedStatement.executeUpdate();
    
                            // Update the user's balance locally
                            currentUser.setBalance(currentUser.getBalance() + depositAmount);
    
                            // Display a receipt
                            String receiptMessage = "Cash transaction successful\nAmount: Php " + depositAmount;
                            JOptionPane.showMessageDialog(
                                    ATMTransactionFrame.this,
                                    receiptMessage,
                                    "Cash Transaction Receipt",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(
                                ATMTransactionFrame.this,
                                "Error processing cash transaction",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            ATMTransactionFrame.this,
                            "Invalid transaction amount. Please enter a positive amount.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        ATMTransactionFrame.this,
                        "Invalid transaction amount. Please enter a valid number.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    // Perform the balance inquiry logic
    private void performBalanceInquiry() {
        // Display the balance to the user
        JOptionPane.showMessageDialog(
                ATMTransactionFrame.this,
                "Your current balance is: Php " + currentUser.getBalance(),
                "Balance Inquiry",
                JOptionPane.INFORMATION_MESSAGE
        );
    
        recordTransaction("balance_inquiry", 0, currentUser.getBalance(), currentUser.getBalance());
    }

    // Perform the account statement logic
    private void performAccountStatement() {
        try (Connection connection = DatabaseConnector.connect()) {
            String query = "SELECT * FROM transactions WHERE user_id = ? ORDER BY timestamp DESC LIMIT 5";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, currentUser.getId());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    StringBuilder statementText = new StringBuilder();
                    statementText.append("Account Statement\n\n");
    
                    while (resultSet.next()) {
                        Timestamp timestamp = resultSet.getTimestamp("timestamp");
                        String transactionType = resultSet.getString("transaction_type");
                        double amount = resultSet.getDouble("amount");
                        double balanceBefore = resultSet.getDouble("balance_before");
                        double balanceAfter = resultSet.getDouble("balance_after");
    
                        statementText.append("Date/Time: ").append(timestamp).append("\n");
                        statementText.append("Transaction Type: ").append(transactionType).append("\n");
                        statementText.append("Amount: ").append(amount).append("\n");
                        statementText.append("Balance Before: ").append(balanceBefore).append("\n");
                        statementText.append("Balance After: ").append(balanceAfter).append("\n\n");
                    }
    
                    JOptionPane.showMessageDialog(
                            ATMTransactionFrame.this,
                            statementText.toString(),
                            "Account Statement",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    ATMTransactionFrame.this,
                    "Error fetching account statement",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }    
    
    private void performPayments() {
        // Check if the user's balance is greater than 0
        if (currentUser.getBalance() <= 0) {
            JOptionPane.showMessageDialog(
                    ATMTransactionFrame.this,
                    "Unable to make payment. Your balance is insufficient.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return; // Exit the method
        }
    
        // Prompt the user for payment details
        JPanel paymentPanel = new JPanel(new GridLayout(2, 2));
        JTextField amountField = new JTextField();
        JTextField purposeField = new JTextField();
    
        paymentPanel.add(new JLabel("Enter amount:"));
        paymentPanel.add(amountField);
        paymentPanel.add(new JLabel("Enter purpose:"));
        paymentPanel.add(purposeField);
    
        int result = JOptionPane.showConfirmDialog(
                ATMTransactionFrame.this,
                paymentPanel,
                "Make Payment",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
    
        if (result == JOptionPane.OK_OPTION) {
            String amountString = amountField.getText();
            String purpose = purposeField.getText();
    
            if (!amountString.isEmpty()) {
                try {
                    double paymentAmount = Double.parseDouble(amountString);
    
                    // Check if the payment amount is greater than 0
                    if (paymentAmount > 0) {
                        // Calculate balance_before and balance_after
                        double balanceBefore = currentUser.getBalance();
                        double balanceAfter = balanceBefore - paymentAmount;
    
                        // Check if maintaining balance will be sufficient after payment
                        if (balanceAfter >= currentUser.getMaintainingBalance()) {
                            // Record the payment transaction
                            recordTransaction("payment", paymentAmount, balanceBefore, balanceAfter);
    
                            // Perform the payment logic (update balance in the database)
                            try (Connection connection = DatabaseConnector.connect()) {
                                String updateQuery = "UPDATE users SET balance = balance - ? WHERE id = ?";
                                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                                    preparedStatement.setDouble(1, paymentAmount);
                                    preparedStatement.setInt(2, currentUser.getId());
                                    preparedStatement.executeUpdate();
    
                                    // Update the user's balance locally
                                    currentUser.setBalance(currentUser.getBalance() - paymentAmount);
    
                                    // Display a receipt
                                    String receiptMessage = "Payment successful for: " + purpose + "\nAmount: Php " + paymentAmount;
                                    JOptionPane.showMessageDialog(
                                            ATMTransactionFrame.this,
                                            receiptMessage,
                                            "Payment Receipt",
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(
                                        ATMTransactionFrame.this,
                                        "Error processing payment",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                    ATMTransactionFrame.this,
                                    "Insufficient maintaining balance after payment.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                ATMTransactionFrame.this,
                                "Invalid payment amount. Please enter a positive amount.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            ATMTransactionFrame.this,
                            "Invalid payment amount. Please enter a valid number.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(
                        ATMTransactionFrame.this,
                        "Please enter a payment amount.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }    

    // Perform the online banking logic
    private void performOnlineBanking() {
        // Simulate a dialog for entering online banking details
        JPanel onlineBankingPanel = new JPanel(new GridLayout(2, 2));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        onlineBankingPanel.add(new JLabel("Enter username:"));
        onlineBankingPanel.add(usernameField);
        onlineBankingPanel.add(new JLabel("Enter password:"));
        onlineBankingPanel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(
                ATMTransactionFrame.this,
                onlineBankingPanel,
                "Online Banking",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            // Retrieve username and password
            String username = usernameField.getText();
            char[] password = passwordField.getPassword();

            // Simulate online banking authentication (replace with actual logic)
            boolean authenticationSuccessful = authenticateOnlineBanking(username, password);

            if (authenticationSuccessful) {
                // Display a message for Transaction Authorization
                String authorizationMessage = "Transaction Authorization in progress. Please wait.";
                JOptionPane.showMessageDialog(
                        ATMTransactionFrame.this,
                        authorizationMessage,
                        "Online Banking Authorization",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                // Authentication failed
                JOptionPane.showMessageDialog(
                        ATMTransactionFrame.this,
                        "Online banking authentication failed. Please check your credentials.",
                        "Online Banking",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    // Simulated method to authenticate online banking credentials
    private boolean authenticateOnlineBanking(String username, char[] password) {
        // Replace this with actual authentication logic (e.g., API calls, database checks)
        // For simulation purposes, the method always returns true.
        return true;
    }


    // Perform the other services logic
    private void performOtherServices() {
        // Define the list of available services
        String[] services = {"Update Personal Information", "Change PIN"};

        // Prompt the user to select a service
        String selectedService = (String) JOptionPane.showInputDialog(
                ATMTransactionFrame.this,
                "Select a service:",
                "Other Services",
                JOptionPane.QUESTION_MESSAGE,
                null,
                services,
                services[0]
        );

        if (selectedService != null) {
            switch (selectedService) {
                case "Update Personal Information":
                    showUpdatePersonalInfoWindow();
                    break;
                case "Change PIN":
                    showChangePINWindow();
                    break;
                default:
                    JOptionPane.showMessageDialog(
                            ATMTransactionFrame.this,
                            "Invalid service selection",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    break;
            }
        }
    }

    // Method to show the window for updating personal information
    private void showUpdatePersonalInfoWindow() {
        // Create a new JFrame for updating personal information
        JFrame updatePersonalInfoFrame = new JFrame("Update Personal Information");
        updatePersonalInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel for the form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 240, 240));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 35, 20, 35));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 15, 5);

        // Add components for updating personal information
        JLabel nameLabel = new JLabel("New Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        JTextField newNameField = new JTextField();
        newNameField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(newNameField, gbc);

        JLabel addressLabel = new JLabel("New Address:");
        addressLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(addressLabel, gbc);

        JTextField newAddressField = new JTextField();
        newAddressField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(newAddressField, gbc);

        // Button for updating personal information
        JButton updateInfoButton = new JButton("Update Information");
        updateInfoButton.setPreferredSize(new Dimension(180, 30));
        updateInfoButton.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(updateInfoButton, gbc);

        updatePersonalInfoFrame.add(formPanel);

        // Set up action listener for the update button
        updateInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = newNameField.getText();
                String newAddress = newAddressField.getText();

                // Check if fields are empty
                if (newName.isEmpty() || newAddress.isEmpty()) {
                    JOptionPane.showMessageDialog(updatePersonalInfoFrame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Perform update logic in the database
                try (Connection connection = DatabaseConnector.connect()) {
                    String updateQuery = "UPDATE users SET name=?, address=? WHERE id=?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                        updateStatement.setString(1, newName);
                        updateStatement.setString(2, newAddress);
                        updateStatement.setInt(3, currentUser.getId());

                        int rowsAffected = updateStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(updatePersonalInfoFrame, "Information updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                            updatePersonalInfoFrame.dispose(); // Close the window after success
                        } else {
                            JOptionPane.showMessageDialog(updatePersonalInfoFrame, "Update failed", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(updatePersonalInfoFrame, "Database error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updatePersonalInfoFrame.pack();
        updatePersonalInfoFrame.setLocationRelativeTo(ATMTransactionFrame.this);
        updatePersonalInfoFrame.setVisible(true);
    }

    // Method to show the window for changing PIN
    private void showChangePINWindow() {
        // Create a new JFrame for changing PIN
        JFrame changePINFrame = new JFrame("Change PIN");
        changePINFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel for the form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 240, 240));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 35, 20, 35));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 15, 5);

        // Add components for changing PIN
        JLabel currentPINLabel = new JLabel("Current PIN:");
        currentPINLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(currentPINLabel, gbc);

        JPasswordField currentPINField = new JPasswordField();
        currentPINField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(currentPINField, gbc);

        JLabel newPINLabel = new JLabel("New PIN:");
        newPINLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(newPINLabel, gbc);

        JPasswordField newPINField = new JPasswordField();
        newPINField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(newPINField, gbc);

        // Button for changing PIN
        JButton changePINButton = new JButton("Change PIN");
        changePINButton.setPreferredSize(new Dimension(180, 30));
        changePINButton.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(changePINButton, gbc);

        changePINFrame.add(formPanel);

        // Set up action listener for the change PIN button
        changePINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] currentPINChars = currentPINField.getPassword();
                char[] newPINChars = newPINField.getPassword();

                // Check if fields are empty
                if (currentPINChars.length == 0 || newPINChars.length == 0) {
                    JOptionPane.showMessageDialog(changePINFrame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Perform change PIN logic in the database
                try (Connection connection = DatabaseConnector.connect()) {
                    // Check if the current PIN matches the stored PIN
                    String checkPINQuery = "SELECT * FROM users WHERE id=? AND pin=?";
                    try (PreparedStatement checkPINStatement = connection.prepareStatement(checkPINQuery)) {
                        checkPINStatement.setInt(1, currentUser.getId());
                        checkPINStatement.setString(2, new String(currentPINChars));

                        try (ResultSet resultSet = checkPINStatement.executeQuery()) {
                            if (resultSet.next()) {
                                // Current PIN is correct, proceed to update the PIN
                                String updatePINQuery = "UPDATE users SET pin=? WHERE id=?";
                                try (PreparedStatement updatePINStatement = connection.prepareStatement(updatePINQuery)) {
                                    updatePINStatement.setString(1, new String(newPINChars));
                                    updatePINStatement.setInt(2, currentUser.getId());

                                    int rowsAffected = updatePINStatement.executeUpdate();

                                    if (rowsAffected > 0) {
                                        JOptionPane.showMessageDialog(changePINFrame, "PIN changed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        changePINFrame.dispose(); // Close the window after success
                                    } else {
                                        JOptionPane.showMessageDialog(changePINFrame, "PIN change failed", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(changePINFrame, "Incorrect current PIN", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(changePINFrame, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });

        changePINFrame.pack();
        changePINFrame.setLocationRelativeTo(ATMTransactionFrame.this);
        changePINFrame.setVisible(true);
    }



    /**
     * Record a transaction in the database.
     *
     * @param transactionType Type of transaction.
     * @param amount          Transaction amount.
     * @param balanceBefore   Balance before the transaction.
     * @param balanceAfter    Balance after the transaction.
     */
    private void recordTransaction(String transactionType, double amount, double balanceBefore, double balanceAfter) {
        try (Connection connection = DatabaseConnector.connect()) {
            String insertQuery = "INSERT INTO transactions (user_id, timestamp, transaction_type, amount, balance_before, balance_after) VALUES (?, CURRENT_TIMESTAMP, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, currentUser.getId());
                preparedStatement.setString(2, transactionType);
                preparedStatement.setDouble(3, amount);
                preparedStatement.setDouble(4, balanceBefore);
                preparedStatement.setDouble(5, balanceAfter);
    
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    ATMTransactionFrame.this,
                    "Error recording transaction",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
    
    
