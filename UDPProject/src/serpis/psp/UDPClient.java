package serpis.psp;

import java.net.*;

public class UDPClient {

	public static void main(String[] args) throws Exception{
		
		String mensaje = "Soy un mensaje";
			
		//while(mensaje.length()<1600) {
		//	mensaje += "mensaje-";
		//}
			
		byte[]buf = mensaje.getBytes();
		InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
		int puerto = 10001;
			
		DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, inetAddress, puerto);
		DatagramSocket datagramSocket = new DatagramSocket();
			
		datagramSocket.send(datagramPacket);
			
		System.out.println("Fin del programa.");

	}

}
