import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Project 4 - Message Class
 * This class is for a certain message.
 *
 * @author Mahad Faruqi, Yagmur Onder, Kasidit Muenprasitivej, Haohan Wu
 * @version July 21, 2021
 */
public class Message {

    /**
     * This is the specific user that contains this message
     */
    private User user;

    /**
     * This is all the other users that "share" this message, basically write to all the other users files too
     */
    private ArrayList<User> users = new ArrayList<User>();

    /**
     * This is the file that the messages are written to
     */
    private File file;

    /**
     * This is the constructor of the message and it initializes the file, list of users, and user.
     *
     * @param file   the file to be written to
     * @param users1 the list of users
     * @param user   the user this message is contained in
     */
    public Message(File file, ArrayList<User> users1, User user) {
        users = new ArrayList<>();
        this.file = file;
        for (int i = 0; i < users1.size(); i++) {
            this.users.add(users1.get(i));
        }
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    /**
     * This method gets the users.
     *
     * @return users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * This method returns the file
     *
     * @return file
     */
    public File getFile() {
        return file;
    }

    /**
     * This method prints the conversation in a nice format from the csv file
     *
     * @throws FileNotFoundException if file is not found
     * @throws IOException           if file cannot be written to
     */
    public void printConvo() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(file);
        BufferedReader bfr = new BufferedReader(fr);
        String line = "";

        while (true) {
            line = bfr.readLine();
            if (line == null) {
                break;
            }

            String[] formatLine = line.split(",");
            formatLine[1] = formatLine[1].replaceAll("/", ",");

            //FORMAT: username: message timestamp
            line = formatLine[0] + ": " + formatLine[1] + " " + formatLine[2];
            System.out.println(line);
        }
    }

    /**
     * This method checks for equality
     *
     * @param o the other object to check
     * @return true if equal, false if not
     */
    public boolean equals(Object o) {
        if (o instanceof Message) {
            Message m = (Message) o;
            if (m.getFile().equals(file)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method prints out the conversation with lines so that the user can easily pick which message to edit.
     *
     * @throws FileNotFoundException if file is not found
     * @throws IOException           if file cannot be written to
     */
    public void printConvoLines() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(file);
        BufferedReader bfr = new BufferedReader(fr);
        String line = "";

        int lineNumber = 1;
        while (true) {
            line = bfr.readLine();
            if (line == null) {
                break;
            }

            String[] formatLine = line.split(",");
            formatLine[1] = formatLine[1].replaceAll("/", ",");

            //FORMAT: username: message timestamp
            line = "(" + lineNumber + ") " + formatLine[0] + ": " + formatLine[1] + " " + formatLine[2];
            System.out.println(line);
            lineNumber++;
        }
    }

    /**
     * This method adds the edited message to the message file
     *
     * @param user    the user using the program
     * @param message the message string
     * @throws FileNotFoundException if file is not found
     * @throws IOException           if file cannot be written to
     */
    public void addEditedMessage(User user, ArrayList<String> message) throws FileNotFoundException, IOException {

        FileOutputStream fos;
        PrintWriter pw;

        try {
            fos = new FileOutputStream(file, false);
            pw = new PrintWriter(fos);

        } catch (FileNotFoundException e) {
            fos = new FileOutputStream(new File(file.getName()));
            pw = new PrintWriter(fos);
        }

        for (int i = 0; i < message.size(); i++) {
            String line = message.get(i);
            pw.println(line);
        }

        pw.flush();
        pw.close();
    }

    /**
     * This method adds the user to this message
      * @param user the user to add
     * @param message the string that is the message
     * @throws FileNotFoundException if file is not found
     * @throws IOException if file cannot be written to
     */
    public void addMessage(User user, String message) throws FileNotFoundException, IOException {
        String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        FileOutputStream fos;
        PrintWriter pw;

        try {
            fos = new FileOutputStream(file, true);
            pw = new PrintWriter(fos);

        } catch (FileNotFoundException e) {
            fos = new FileOutputStream(new File(file.getName()));
            pw = new PrintWriter(fos);
        }
            pw.println(user.getFileUsername() + "," + message + "," + timeStamp);
        pw.flush();
        pw.close();
    }

    /**
     * This method returns the current time
     * @return the time;
     */
    public String getTimeStamp() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    /**
     * This method returns a string representations of the users currently using it.
     * @return string of users
     */
    public String toString() {
        String res = "";
        for (int i = 0; i < users.size(); i++) {
            if (!users.get(i).equals(user)) {
                if (i != users.size() - 1) {
                    res += users.get(i).getFirstName().substring(0, 1).toUpperCase()
                            +  users.get(i).getFirstName().substring(1) + " "
                            + users.get(i).getLastName().substring(0, 1).toUpperCase() +
                            users.get(i).getLastName().substring(1) + ", ";
                } else {
                    res += users.get(i).getFirstName().substring(0, 1).toUpperCase()
                            +  users.get(i).getFirstName().substring(1) + " "
                            + users.get(i).getLastName().substring(0, 1).toUpperCase() +
                            users.get(i).getLastName().substring(1);

                }
            }
        }
        return res;
    }
}

