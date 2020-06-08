

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

	public static boolean letraA(String a) {
		boolean sw=false;
		for (int i = 0; i < a.length(); i++) {
			if(a.charAt(i)=='a')
				sw=true;
		}
		return sw;
	}
	public static void main(String[] args) throws IOException{
		ServerSocket socketServidor=null;
		Socket socketCliente=null;	
		BufferedReader entrada=null;
		PrintWriter salida=null;
		System.out.println("SERVIDOR TCP");
		try {
			socketServidor =new ServerSocket(8888);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			while(true) {
				socketCliente=socketServidor.accept();
				//Creamos el flujo de entrada
				entrada=new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
				//creamos el flujo de salida
				salida=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);
				
				while(true) {
					//Recibimos los datos
					String cad=entrada.readLine();
					String res="";
					switch (cad.charAt(0)) {
					case 49:
						res="Papel";
						break;
					case 50:
						res="Piedra";
						break;
					case 51:
						res="Tijeras";
						break;
					case 52:
						res="4";
						break;
						
					default:
						res="Intente de nuevo";
						break;
					}
					System.out.println(cad);
					
					salida.println(res);
					
					if(res.equals("4")) break;
				}
    		}
		} catch (Exception e) {
			System.out.println(e);
		}
		socketCliente.close();
		salida.close();
		entrada.close();
		socketCliente.close();
		socketServidor.close();
	}

}
