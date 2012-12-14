package serpis.psp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class TCPServer {

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
    	//holaMundo();
    	//pingPong();
    	connectAndWrite();
    }
    
    private static void connectAndWrite() throws IOException {
    	
    	int port = 10001;
    	ServerSocket serverSocket = new ServerSocket(port);
    	Socket socket = serverSocket.accept();
    	
    	PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true); //autoflush=true
    	printWriter.println("La hora 1 es " + new Date());
    	printWriter.println("La hora 2 es " + new Date());
    	printWriter.println("La hora 3 es " + new Date());
    	
    	socket.close();
    	serverSocket.close();
    }
    
    private static void pingPong() {
    	
    }
    
    private static void holaMundo() throws IOException {
    	
    	int port = 10001;
        
        ServerSocket serverSocket = new ServerSocket(port);
        
        Socket socket = serverSocket.accept();
        
        Scanner scanner = new Scanner(socket.getInputStream());    
        String line = scanner.nextLine();
        System.out.println("line = " + line);
        
        socket.close();
        serverSocket.close();


    }

}
