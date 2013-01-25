package dam.psp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HttpServer {

	final static String newLine = "\r\n";
	
	final static String fileNameError404 = "fileError404.html";
	final static String response200 = "HTTP/1.0 200 OK";
	final static String response404 = "HTTP/1.0 404 Not Found";
	
	static String response = response200;
	
	public static void main (String[]args) throws IOException, InterruptedException {
		
		final int puerto = 8090;
		ServerSocket serverSocket = new ServerSocket(puerto);
		
		while(true) {
		
			Socket socket = serverSocket.accept();
			
//			SimpleServer simpleServer = new SimpleServer(socket);
//			simpleServer.run();
			
			ThreadServer threadServer = new ThreadServer(socket);
			new Thread(threadServer).start();
			
		}
	}
	
	static void writeFile(OutputStream outputStream, String fileName) throws IOException {
		
		File file = new File(fileName);
		
		if(!file.exists()) {
			fileName = fileNameError404;
			response = response404;
		}
		
		FileInputStream fileInputStream = new FileInputStream(fileName);
		
		final int bufferSize = 2048;
		byte[]buffer = new byte[bufferSize];
		int count = 0;
		
		while( (count = fileInputStream.read(buffer)) != -1 )
			outputStream.write(buffer, 0, count);
		
		fileInputStream.close();
	}
	
	static void writeHeader(OutputStream outputStream, String fileName) throws IOException {
		
		File file = new File(fileName);
		
		if(!file.exists()) {
			fileName = fileNameError404;
			response = response404;
		}
		
		String header = response + newLine + newLine;
		byte[]headerBuffer = header.getBytes();
		
		outputStream.write(headerBuffer);	
	}

	//TODO implementar correctamente
	static String getFileName(InputStream inputStream) {
		
		Scanner scanner = new Scanner(inputStream);
		
		String fileName = "";
		
		while(true) {
			String line = scanner.nextLine();
			System.out.println(line);
			if(line.contains("GET ")) {
				char[]aux = line.toCharArray();
				int i = 5;
				
				while(aux[i] != ' ') {
					fileName += aux[i];
					i++;
				}
			}
			if(line.equals("")) {
				break;
			}
		}
		
		return fileName;
	}

}

class SimpleServer {
	Socket socket;
	
	public SimpleServer(Socket s) {
		socket = s;
	}
	
	public void run() {
		String fileName;
		
		try {
			fileName = HttpServer.getFileName(socket.getInputStream());
			HttpServer.writeHeader(socket.getOutputStream(), fileName);
			HttpServer.writeFile(socket.getOutputStream(), fileName);
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

class ThreadServer implements Runnable {
	Socket socket;
	
	public ThreadServer(Socket s) {
		socket = s;
	}
	
	public void run() {
		String fileName;
		
		try {
			fileName = HttpServer.getFileName(socket.getInputStream());
			HttpServer.writeHeader(socket.getOutputStream(), fileName);
			HttpServer.writeFile(socket.getOutputStream(), fileName);
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


	
