import javax.swing.*;
import java.net.Socket;
import java.io.*;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;


public class MessengerServer implements Runnable {

    final static Object o = new Object();

    private User user;
    private static ArrayList<User> users = new ArrayList<User>();
    public static File userFile;
    //public static int userCounter;
    //public static boolean running = false;

    public static void main(String[] args) throws SocketException, IOException, ClassNotFoundException {

        // create socket on agreed-upon port...
        ServerSocket serverSocket = new ServerSocket(4242);
        //userCounter = 0;

        while (true) {
            // wait for client to connect, get socket connection...
            System.out.println("wait for client to connect, get socket connection...");
            Socket socket = serverSocket.accept();

//            synchronized (o) {
//                userCounter++;
//            }


            Thread t = new Thread(new MessengerServer());
            t.start();

        }
    }


    @Override
    public void run() {

        //Initialize userFile
        synchronized (o) {
            userFile = new File("userFile.csv");
        }

        try {
            userFile.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create new Utils object
        Utils u = new Utils();

        //Get all up-to-date existed users from userFile.csv
        try {

            synchronized (o) {
                users = u.parseUsers(userFile, users);
//                if(users.size() == 1) {
//                    running = true;
//                } else {
//                    running = false;
//                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // so that there are more than one users, so messaging is possible
        if (users.size() > 1) {
            try {
                synchronized (o) {
                        users = u.parseMessage(users);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Prompt user to log in or sign up
        inner:
        while (true) {
            int option = u.loginOrSignUp();

            //If user wants to log in
            if (option == 0) {
                try {

                    synchronized (o) {
                        user = u.logIn(userFile, users);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                synchronized (o) {
                    //handle cases when the entered username is incorrect
                    if (user.getUsername().equals("invalid")) {
                        users.remove(user);
                        continue inner;
                    }
                }

                break inner;

                //if user wants to create new account
            } else if (option == 1) {

                try {

                    synchronized (o) {

                        int userSize = users.size();

                        synchronized (o) {
                            users = u.signUp(userFile, users);
                            user = users.get(users.size() - 1);
                        }

                        //if array list is the same size, then there was no new user created
                        if (userSize == users.size()) {
                            continue inner;
                        }

                        break inner;

                    }

                    //Catch if the newly created account overlaps with existed account
                } catch (DuplicateUserException e) {
                    JOptionPane.showMessageDialog(null,
                            "The entered username is already taken.\n" +
                                    "Please try again!", "Create New Account", JOptionPane.ERROR_MESSAGE);
                    continue inner;

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (option == -1) {
                return;
            }

        } //end of while loop


        SwingUtilities.invokeLater(new GUI(user, users));

    }
}
