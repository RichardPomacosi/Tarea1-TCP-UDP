

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;

public class ClienteUDP {

	public static void main(String[] args) {
		System.out.println("CLIENTE UDP");
	try {
		DatagramSocket socketUDP=new DatagramSocket();
		int puerto=8888;
		//el formato debe ser InetAddress
		InetAddress host=InetAddress.getByName("localhost");
		BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
		String cad;
		while(true) {
			cad=sc.readLine();
			//El cliente termina si la entrada es 0
			if(cad.equals("0"))break;
			//creamos un vector de bytes para poder enviar el datagrama
			byte[] msg= cad.getBytes();
			DatagramPacket peticion= new DatagramPacket(msg, cad.length(), host, puerto);
			socketUDP.send(peticion);
			
			//Recibimos los mensajes del servidor
			byte [] bufer=new byte[10000];
			DatagramPacket mensaje=new DatagramPacket(bufer,  bufer.length);
			socketUDP.receive(mensaje);
			System.out.println("Numero de palabras: "+new String(mensaje.getData()));
		}	
		
	} catch (Exception e) {
		System.out.println(e);
	}
		
	}

}
