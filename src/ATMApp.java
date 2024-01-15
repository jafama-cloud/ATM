import javax.swing.SwingUtilities;

/**
 * The `ATMApp` class serves as the entry point for the ATM application, 
 * launching the main frame on the Event Dispatch Thread (EDT).
 */
public class ATMApp {
    // The main method of the ATM application. It launches the main frame on the EDT.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ATMFrame atmFrame = new ATMFrame();     // An instance of the main frame.
            atmFrame.setVisible(true);            // Set the main frame to be visible.
        });
    }
}
