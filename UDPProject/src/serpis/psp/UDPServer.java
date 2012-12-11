package serpis.psp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

	public static void main(String[]args) throws Exception{
		
		byte[]buf = new byte[2048];
		DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
		DatagramSocket datagramSocket = new DatagramSocket(10001);

			
		datagramSocket.receive(datagramPacket);
			
		String mensaje = new String(datagramPacket.getData());
		System.out.println(
			"Mensaje enviado desde la dirección " + datagramPacket.getAddress() + "\n" +
			"El mensaje procede del puerto " + datagramPacket.getPort() + "\n" +
			"La longitud del mensaje es " + datagramPacket.getLength() + "\n" +
			"El mensaje es: " + mensaje
		);
		
	}
	
}
