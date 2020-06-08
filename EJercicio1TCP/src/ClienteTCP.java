

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP {

	public static void main(String[] args) throws IOException {
		
		
		Socket socketCliente=null;
		BufferedReader entrada=null;
		PrintWriter salida=null;
		System.out.println("CLIENTE...");
		try {
			socketCliente=new Socket("localhost",8888);
			//Creamos el flujo de entrada
			entrada=new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			//creamos el flujo de salida
			salida=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		BufferedReader sc= new BufferedReader(new InputStreamReader(System.in));
			
		try {
			while(true) {
				System.out.println("***********MENÚ************");
				System.out.println("*1. Papel                 *");
				System.out.println("*2. Piedra                *");
				System.out.println("*3. Tijeras               *");
				System.out.println("*4. Salir                 *");
				System.out.println("***************************");
				
				String cad=sc.readLine();
				
				//enviando datos al servidor
				salida.println(cad);
				//Recibimos la respuesta del servidor
				cad=entrada.readLine();
				System.out.println(cad);
				if(cad.equals("4")) break;
			}
		} catch (Exception e) {
			
		}
		salida.close();
		entrada.close();
		sc.close();
		socketCliente.close();
		
	}

}
