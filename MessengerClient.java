import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MessengerClient {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, NotSerializableException {
            Socket socket = new Socket("localhost", 4242);
    }
}
