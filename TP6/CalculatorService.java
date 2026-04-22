package TP6;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Definition of the remote RMI interface for Calculator operations.
 * This serves as the contract between the Application Client and the Server.
 */
public interface CalculatorService extends Remote {

    /**
     * Adds two integers together.
     *
     * @param a The first integer.
     * @param b The second integer.
     * @return The sum of a and b.
     * @throws RemoteException if the RMI execution fails.
     */
    int addNumbers(int a, int b) throws RemoteException;

    /**
     * Displays a message on the server-side console.
     *
     * @param message The message to display.
     * @throws RemoteException if the RMI execution fails.
     */
    void displayMessage(String message) throws RemoteException;
}
