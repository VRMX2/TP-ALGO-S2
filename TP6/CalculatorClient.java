package TP6;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Client application that connects to the CalculatorService via RMI.
 */
public class CalculatorClient {

    private static final Logger LOGGER = Logger.getLogger(CalculatorClient.class.getName());

    // Configuration constants
    private static final String SERVER_HOST = "localhost";
    private static final int PORT = 1099;
    private static final String BINDING_NAME = "CalculatorService";

    public static void main(String[] args) {
        try {
            LOGGER.info(String.format("Attempting to connect to RMI Server at %s:%d...", SERVER_HOST, PORT));

            // Obtain the registry
            Registry registry = LocateRegistry.getRegistry(SERVER_HOST, PORT);

            // Lookup the remote object
            CalculatorService service = (CalculatorService) registry.lookup(BINDING_NAME);
            LOGGER.info("Successfully connected to the remote RMI service.");

            // Perform calculations
            int num1 = 10;
            int num2 = 20;
            LOGGER.info(String.format("Invoking remote addNumbers(%d, %d)...", num1, num2));
            int sum = service.addNumbers(num1, num2);
            LOGGER.info(String.format("Result received successfully. Sum: %d", sum));

            // Perform display message
            String message = "RSD - Remote Systems Development";
            LOGGER.info(String.format("Invoking remote displayMessage(\"%s\")...", message));
            service.displayMessage(message);
            
            LOGGER.info("All remote operations completed successfully.");

        } catch (RemoteException e) {
            LOGGER.log(Level.SEVERE, "Network/RMI error occurred", e);
        } catch (NotBoundException e) {
            LOGGER.log(Level.SEVERE, "The requested service name is not bound in the registry", e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An unexpected error occurred", e);
        }
    }
}
