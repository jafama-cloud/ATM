import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Represents the main frame for ATM user log in/sign up 
public class ATMFrame extends JFrame {
    private JTextField cardNumberField;
    private JPasswordField pinField;

    // Constructor for the ATMFrame
    public ATMFrame() {
        // Frame setup
        setTitle("ATM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title panel setup
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(100, 100, 100)); // Dark gray background
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding

        // Title label setup
        JLabel titleLabel = new JLabel(" WELCOME TO ATM", createIcon("/icon.png", 120, 120), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 44));
        titleLabel.setForeground(Color.WHITE); // White text color

        titlePanel.add(titleLabel);

        add(titlePanel, BorderLayout.NORTH); // Add the title panel to the top

        // Create a panel for the form
        JPanel formPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for more flexibility
        formPanel.setBackground(new Color(240, 240, 240)); // Light gray background
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 35, 20, 35)); // Add padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 28, 5); // Adjust insets for spacing

        // Create a label for "Card No."
        JLabel cardLabel = new JLabel("Card No.:");
        cardLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Increase font size
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(cardLabel, gbc);

        cardNumberField = new JTextField();
        cardNumberField.setPreferredSize(new Dimension(300, 40)); // Set preferred size
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(cardNumberField, gbc);

        // Create a label for "PIN"
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Increase font size
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(pinLabel, gbc);

        pinField = new JPasswordField();
        pinField.setPreferredSize(new Dimension(300, 40)); // Set preferred size
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(pinField, gbc);

        add(formPanel, BorderLayout.CENTER); // Add the form panel to the center

        // Button panel setup
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(200, 200, 200)); // Light gray background
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0)); // Add padding

        JButton signInButton = new JButton("Sign In");
        JButton signUpButton = new JButton("Sign Up");
        JButton clearButton = new JButton("Clear");

        // Set preferred size for buttons
        signInButton.setPreferredSize(new Dimension(122, 40));
        signUpButton.setPreferredSize(new Dimension(122, 40));
        clearButton.setPreferredSize(new Dimension(122, 40));

        // Increase font size for button labels
        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        signInButton.setFont(buttonFont);
        signUpButton.setFont(buttonFont);
        clearButton.setFont(buttonFont);

        // Add buttons to the panel
        buttonPanel.add(signInButton);
        buttonPanel.add(signUpButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the bottom

        // Set button colors
        signInButton.setBackground(new Color(69, 105, 144)); // Blue color
        signUpButton.setBackground(new Color(50, 150, 50));   // Green color
        clearButton.setBackground(new Color(200, 50, 50));    // Red color

        // Set button text color to white
        signInButton.setForeground(Color.WHITE);
        signUpButton.setForeground(Color.WHITE);
        clearButton.setForeground(Color.WHITE);

        // Add action listeners to buttons
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransactionWindow(); 
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationDialog registrationDialog = new RegistrationDialog(ATMFrame.this);
                registrationDialog.setVisible(true);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardNumberField.setText("");
                pinField.setText("");
            }
        });
        // Finalize frame setup
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
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

    private void showTransactionWindow() {
        String cardNumber = cardNumberField.getText();
        char[] pinChars = pinField.getPassword();
        String pin = new String(pinChars);

        // Check for empty fields
        if (cardNumber.isEmpty() || pin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter card number and PIN", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection connection = DatabaseConnector.connect()) {
            String query = "SELECT * FROM users WHERE card_number = ? AND pin = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, cardNumber);
                preparedStatement.setString(2, pin);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    ATMUser user = new ATMUser(resultSet.getInt("id"), cardNumber, pin, resultSet.getDouble("balance"),resultSet.getDouble("maintaining_balance"));
                    ATMTransactionFrame transactionFrame = new ATMTransactionFrame(user);
                    transactionFrame.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid card number or PIN", "Authentication Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    class RegistrationDialog extends JDialog {
        private JTextField nameField;
        private JTextField addressField; 
        private JTextField cardNumberField;
        private JPasswordField pinField;
        private JTextField initialBalanceField; // Add a new field for initial balance
    
        public RegistrationDialog(Frame owner) {
            super(owner, "User Registration", true);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    
            JPanel registrationPanel = new JPanel(new GridLayout(6, 2, 10, 10)); // Use GridLayout
            registrationPanel.setBackground(new Color(240, 240, 240)); // Light gray background
            registrationPanel.setBorder(BorderFactory.createEmptyBorder(20, 35, 20, 35)); // Add padding
    
            JLabel nameLabel = new JLabel("Name:");
            registrationPanel.add(nameLabel);
    
            nameField = new JTextField();
            registrationPanel.add(nameField);

            JLabel addressLabel = new JLabel("Address:");
            registrationPanel.add(addressLabel);
    
            addressField = new JTextField();
            registrationPanel.add(addressField);
    
            JLabel cardLabel = new JLabel("Card No.:");
            registrationPanel.add(cardLabel);
    
            cardNumberField = new JTextField();
            registrationPanel.add(cardNumberField);
    
            JLabel pinLabel = new JLabel("PIN:");
            registrationPanel.add(pinLabel);
    
            pinField = new JPasswordField();
            registrationPanel.add(pinField);

            JLabel initialBalanceLabel = new JLabel("Initial Balance:");
            registrationPanel.add(initialBalanceLabel);

        initialBalanceField = new JTextField();
        registrationPanel.add(initialBalanceField);
    
            JButton registerButton = new JButton("Register");
            registrationPanel.add(new JLabel()); // Empty label for layout
            registrationPanel.add(registerButton);
    
            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Perform user registration logic here
                    // Provide appropriate feedback to the user
    
                    String name = nameField.getText();
                    String address = addressField.getText(); 
                    String cardNumber = cardNumberField.getText();
                    char[] pinChars = pinField.getPassword();
                    String pin = new String(pinChars);
                    double initialBalance = Double.parseDouble(initialBalanceField.getText());
                    double maintainingBalance = 500.0; // Set your maintaining balance requirement here
    
                    // Validate maintaining balance requirement
                    if (initialBalance < maintainingBalance) {
                        JOptionPane.showMessageDialog(RegistrationDialog.this, "Initial balance must meet or exceed the maintaining balance requirement: Php" + maintainingBalance, "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }    

                    // Validate input
                    if (name.isEmpty() || address.isEmpty() || cardNumber.isEmpty() || pin.isEmpty()) {
                        JOptionPane.showMessageDialog(RegistrationDialog.this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try (Connection connection = DatabaseConnector.connect()) {
                        // Check if the card number already exists
                        String checkCardNumberQuery = "SELECT * FROM users WHERE card_number = ?";
                        try (PreparedStatement checkCardNumberStatement = connection.prepareStatement(checkCardNumberQuery)) {
                            checkCardNumberStatement.setString(1, cardNumber);

                            ResultSet cardNumberResultSet = checkCardNumberStatement.executeQuery();

                            if (cardNumberResultSet.next()) {
                                JOptionPane.showMessageDialog(RegistrationDialog.this, "Card number already exists", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }

                        // Insert new user into the database
                        String insertUserQuery = "INSERT INTO users (name, address, card_number, pin, balance, maintaining_balance) VALUES (?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement insertUserStatement = connection.prepareStatement(insertUserQuery)) {
                            insertUserStatement.setString(1, name);
                            insertUserStatement.setString(2, address);
                            insertUserStatement.setString(3, cardNumber);
                            insertUserStatement.setString(4, pin);
                            insertUserStatement.setDouble(5, initialBalance);
                            insertUserStatement.setDouble(6, maintainingBalance); // Set maintaining balance to $500 initially

                            int rowsAffected = insertUserStatement.executeUpdate();

                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(RegistrationDialog.this, "Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(RegistrationDialog.this, "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(RegistrationDialog.this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
    
            add(registrationPanel);
            pack();
            setLocationRelativeTo(owner);
        }
    }
    
}
