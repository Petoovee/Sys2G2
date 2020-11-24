package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Questions.Course;
import Questions.Sixth;
import sharedEntities.User;


/**
 * MathTrainer Server class, controls logic/communications with the MathTrainerClient.
 *
 * @author abdulsamisahil
 * @version 1.5
 * @since 2020-05-27
 */
public class MServer {

    private ServerSocket serverSocket;
    private boolean keepRunning;
    private int port;
    private String fileLocation;
    private ArrayList<User> usersList;
    private User user;
    private boolean isUserNew = true;

    /**
     * Constructs MServer
     * @param port that the server will be listening on
     */
    public MServer(int port) throws FileNotFoundException {
        this.port = port;
        keepRunning = true;
        usersList = new ArrayList<>();
        usersList.add(new User("admin", "admin"));
        fileLocation = System.getProperty("user.dir") + "/inloggningsUppgifter.dat";
        System.out.println(fileLocation);
        try {
            startServer();
            readFile(fileLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is activating the MServer, Server accepts any client that wants to communicate
     */
    private void startServer() throws IOException {

        // Starting server
        serverSocket = new ServerSocket(port);
        System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");

        while (keepRunning) {
            try {
                Socket connect = serverSocket.accept();
                System.out.println("Connection successful");
                Thread t = new ClientHandler(connect);
                t.start();
            } catch (Exception e) {
                serverSocket.close();
                e.printStackTrace();
            }
        }
    }

    /**
     * As soon as the MServer gets connected, this method goes through a text file which includes
     * the existing users of the system and puts them to a collection called userList,
     * @param fileLocation the text file location
     */
    private void readFile(String fileLocation) {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileLocation));

            while ((user = (User)ois.readObject()) != null)
            {
                usersList.add(user);
                System.out.println(user);
            }
            ois.close();
            System.out.println("Size of user array: " + usersList.size());
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        new MServer(7890);
    }

    /**
     * The inner class the handles the users, we have not decided to build the product
     * server to handle different of users connected on different network (Multithreading),
     * but I will try to code,  in case we change our mind.
     *
     * @author abdulsamisahil
     * @version 1.5
     * @since 2020.04.29
     */
    class ClientHandler extends Thread {

        private Socket server;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;
        /**
         * Constructor
         * @param server connects the client to server and initiates the object streams
         */
        public ClientHandler(Socket server) throws IOException {
            this.server = server;
            try {
                setupStreams();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Through this method a client starts operating/communicating with the server,
         * the start method of the thread.
         */
        public void run() {
            try {
                Course course = null;
                try {
                    String input = ois.readUTF();
                    if (input.equals("Login")) {
                        User user = (User) ois.readObject();
                        user = isLoginSucceeded(user);
                        if (user != null) {
                            System.out.println("Writing " + user);
                            oos.writeObject(user);
                        } else {
                            System.out.println("Login failed");
                            oos.writeObject("Inloggning misslyckad: Felaktigt användarnamn eller lösenord!");
                        }
                    } else if (input.equals("NewUser")) {

                        User user = (User) ois.readObject();
                        boolean isUserNew = newUser(user);
                        if (isUserNew) {
                            //Implementing thread safety in case of concurrent programming
                            Object lock = new Object();
                            synchronized (lock){
                                usersList.add(user);
                                //Adding new user to the text file as well
                                ObjectOutputStream fileStream = new ObjectOutputStream(new FileOutputStream(fileLocation));
                                fileStream.writeObject(user);
                                fileStream.flush();
                            }
                            //Sending the user back to the client
                            oos.writeObject(user);
                        } else {
                            oos.writeObject("Inloggning misslyckad: Användarnamnet är upptaget");
                        }
                    } else if (input.equals("Questions")) {
                        System.out.println("Checking questions");
                        course = new Sixth();
                        String answerTypeOfQuestion = (String) ois.readObject();
                        System.out.println("Recieved: " + answerTypeOfQuestion);
                        if (answerTypeOfQuestion.equals("Geometry")) {
                            oos.writeObject(course.getGeometryQuestions());
                        } else if (answerTypeOfQuestion.equals("Counting")) {
                            oos.writeObject(course.getFourCountQuestions());
                        } else if (answerTypeOfQuestion.equals("Statistics")) {
                            oos.writeObject(course.getStatisticQuestions());
                        } else if (answerTypeOfQuestion.equals("Random")) {
                            oos.writeObject(course.getRandomiseQuestions());
                        }
                    } else if (input.equals("Result")) {
                        User user =(User) ois.readObject();
                        user = result(user);
                        if (user != null)
                        {
                            System.out.println("Writing user's results " + user);
                            oos.writeObject(user);
                        }
                        else {
                            oos.writeUTF("Dessvärre hittar inte användaren");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * This method is taking the user object as an argument and checking it if it is not
         * already there in the list of users, so this method returns true, otherwise false
         * @author parprogramming Abdul Sami Sahil, johanna and Motaz
         */
        private boolean newUser(User user) {
            for (int i = 0; i < usersList.size(); i++) {
                if (user.getUserName().equals(usersList.get(i).getUserName())) {
                    isUserNew = false;
                }
            }
            return isUserNew;
        }
        /**
         * This method is taking the user as an argument and checking it
         * if the user is already registered in the system, will be returned
         * @author parprogramming Abdul Sami Sahil, johanna and Motaz
         */
        private User isLoginSucceeded(User receivedUser) {
            String username = receivedUser.getUserName();
            String password = receivedUser.getPassword();
            User user = null;
            for (int i = 0; i < usersList.size(); i++) {
                System.out.println("Testing array -  " + usersList.get(i));
                System.out.println("Usernames: " + usersList.get(i).getUserName());
                System.out.println("Passwords: " + usersList.get(i).getPassword());
                if ((username.equals(usersList.get(i).getUserName())) && (password.equals(usersList.get(i).getPassword()))) {
                    user = usersList.get(i);
                }
            }
            return user;
        }
        /**
         * Checking if the user is in the collection
         * @author Abdul Sami Sahil
         * @param receivedUser if receivedUser is there in the collection, it will be initiated to the one in the list.
         * @return sends back the initiated user to the client.
         */
        private User result (User receivedUser)
        {
            User user = null;
            String username = receivedUser.getUserName();
            System.out.println(usersList.toString());
            for (User u: usersList)
            {
                System.out.println(usersList.toString());
                if (username.equals(u.getUserName())){
                    user = receivedUser;
                    usersList.set(usersList.indexOf(u), user);
                }
            }
            System.out.println(usersList.toString());
            return user;
        }
        /**
         * This method initializes and setups the streams that holds/read/writes the data input from the users
         */
        private void setupStreams() throws IOException {
            ois = new ObjectInputStream(server.getInputStream());
            oos = new ObjectOutputStream(server.getOutputStream());
            oos.flush(); // sends everything that the user wants
        }
    }
}