package controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/** Class Network handles the outgoing and incoming information to and from the server. It connects with a socket,
 * sets up streams and has two inner classes handling the monitoring for new information to send or recieve.
 * @author Niklas Hultin, Bajram Gerbeshi
 * @version 1.0
 */

public class NetworkController {
    private Socket socket;
    private String IP;
    private static final int PORT = 7890;

    /**
     * Creates the socket that connects to the server, gets buffers (from MainController) and starts the threads for
     * communicating with the server.
     * @author Niklas Hultin
     */
    private void establishConnection(){
        try {
            IP = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            socket = new Socket(IP, PORT);
        } catch (IOException e) {
            System.out.println("Error trying to connect");
            e.printStackTrace();
        }
    }

    /**
     * Sends a request to the server that consists only of a string.
     * @param request The request. Contains information of the type of request, and relevant data
     * @return The answer from the server (an Object).
     * @author Bajram Gerbeshi
     */
    public Object sendRequest(String request){
        establishConnection();

        NetworkHandler networkHandler = new NetworkHandler(request);
        return networkHandler.sendRequest();
    }

    /**
     * Sends a request to the server that consists of a string and an object.
     * @param request The request only contains information of the type of request.
     * @param object The object is the relevant data that the server is to process.
     * @return The answer from the server (an Object).
     * @author Bajram Gerbeshi
     */
    public Object sendRequest(String request, Object object){
        establishConnection();

        NetworkHandler networkHandler = new NetworkHandler(request, object);
        return networkHandler.sendRequest();
    }

    /**
     * Receives the incoming messages from the server and adds them to the incoming buffer.
     * @author Niklas Hultin, Bajram Gerbeshi
     */
    private class NetworkHandler{
        private String request;
        private Object object;

        /**
         * Handles a the splitting of the request and the data for a String-only request.
         * @param request Contains both the request type and the data, separated by a blank space.
         * @author Bajram Gerbeshi
         */
        public NetworkHandler(String request) {
            this.request = request.substring(0,request.indexOf(' '));
            this.object = request.substring(request.indexOf(' ')+1);
        }

        /**
         * Handles a the request that contains a string with type and an object of data.
         * @param request Contains only the request.
         * @param object The relevant data for the server.
         * @author Bajram Gerbeshi
         */
        public NetworkHandler(String request, Object object) {
            this.request = request;
            this.object = object;
        }

        /**
         * Sends a request to the server and gets an object in return in a sequential manner.
         * @return The object returned by the server. This is either a User or a String (handled in MainController)
         * @author Niklas Hultin, Bajram Gerbeshi
         */
        public Object sendRequest(){
            ObjectOutputStream objectOutputStream;
            ObjectInputStream objectInputStream;

            Object returnValue = null;
            try {
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                objectOutputStream.writeUTF(request);
                System.out.println("Sent " + request);
                objectOutputStream.writeObject(object);
                System.out.println("Sent " + object);
                objectOutputStream.flush();
                returnValue = objectInputStream.readObject();
                System.out.println(returnValue);
                objectOutputStream.close();
                objectInputStream.close();


            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error in network communcation");
                e.printStackTrace();
            }
            System.out.println("Received " + returnValue);
        return returnValue;
        }
    }
}
