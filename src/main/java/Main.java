import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Сервер запущен");
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true){
                try (Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    System.out.println("New connection accepted");
                    final String name = in.readLine();
                    out.println(String.format("Hi %s, your port is %d. Are you student? y/n", name, clientSocket.getPort()));
                    String message = in.readLine();
                    if (message.equals("y")){
                        out.println("Oh, are you studying java? y/n");
                        message = in.readLine();
                        if (message.equals("y")){
                            out.println("That's cool! Excellent studies! How old are you?");
                            message = in.readLine();
                            int messageInt = Integer.parseInt(message);
                            if (messageInt <= 18 && messageInt >= 5){
                                out.println("Oh! You so young student! That's cool! Good luck! Bye! Off");
                                break;
                            } else if (messageInt >= 18) {
                                out.println("It's never too late to start learning! I wish you good luck! Off");
                                break;
                            } else {
                                out.println("Your age is incorrect. You're trying to deceive me! Goodbye. Off");
                                break;
                            }
                        } else if (message.equals("n")) {
                            out.println("It's sad! You need to study java! Off");
                            break;
                        } else {
                            out.println("Your age is incorrect. You're trying to deceive me! Goodbye. Off");
                            break;
                        }
                    } else if (message.equals("n")){
                        out.println("It's bad! It's very very bad! I only want to talk to students. Bye. Off");
                        break;
                    } else {
                        out.println("Your age is incorrect. You're trying to deceive me! Goodbye. Off");
                        break;
                    }
                }
            }
        }
    }
}
