package TP6;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The RMI Server implementation for the CalculatorService.
 */
public class CalculatorServer extends UnicastRemoteObject implements CalculatorService {

    private static final Logger LOGGER = Logger.getLogger(CalculatorServer.class.getName());
    
    // Configuration constants
    private static final int PORT = 1099;
    private static final String BINDING_NAME = "CalculatorService";

    protected CalculatorServer() throws RemoteException {
        super();
    }

    @Override
    public int addNumbers(int a, int b) throws RemoteException {
        LOGGER.info(String.format("Received request to add numbers: %d + %d", a, b));
        return a + b;
    }

    @Override
    public void displayMessage(String message) throws RemoteException {
        LOGGER.info(String.format("Received display message: [%s]", message));
    }

    public static void main(String[] args) {
        try {
            LOGGER.info("Starting up the RMI Server...");
            
            // Instantiate the service object
            CalculatorServer server = new CalculatorServer();
            
            // Create the RMI registry on the specified port
            Registry registry = LocateRegistry.createRegistry(PORT);
            
            // Bind the remote object to the registry
            registry.rebind(BINDING_NAME, server);
            
            LOGGER.info(String.format("RMI Server is successfully running and listening on port %d...", PORT));
            LOGGER.info(String.format("Service bound to name: %s", BINDING_NAME));

        } catch (RemoteException e) {
            LOGGER.log(Level.SEVERE, "Failed to start the RMI Server", e);
        }
    }
}
