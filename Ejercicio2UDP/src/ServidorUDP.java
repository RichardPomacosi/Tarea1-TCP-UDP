

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

public class ServidorUDP {
	public static String recuperar(String cad, int t) {
		String res="";
		for (int i = 0; i < t; i++) {
			res+=cad.charAt(i);
		}
		return res;
	}
	
	public static int contar(String x) {
		StringTokenizer st=new StringTokenizer(x);
		return st.countTokens();
	}
	
	public static void main(String[] args) {
		System.out.println("SERVIDOR UDP");
		try {
			DatagramSocket socketUDP=new DatagramSocket(8888);
			byte[] bufer =new byte[10000];
			while(true) {
				DatagramPacket peticion= new DatagramPacket(bufer, bufer.length);
				//recibimos en el contenedor el mensaje enviado por el cliente
				socketUDP.receive(peticion);
				
				String res=new String(peticion.getData());
				
				String x=recuperar(res, peticion.getLength());
				
				int p=contar(x);
				x=Integer.toString(p);
				
				byte [] enviar=x.getBytes();
				DatagramPacket mensaje=new DatagramPacket(enviar, x.length(),peticion.getAddress(),peticion.getPort() );
				socketUDP.send(mensaje);
				
				System.out.println("Datos del cliente:");
				System.out.println("Direccion: "+peticion.getAddress());
				System.out.println("Puerto: "+ peticion.getPort());
				System.out.println("datos: "+new String(peticion.getData()));
				System.out.println("Datos enviados al cliente: "+x);
				System.out.println("***********************************************************************");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
