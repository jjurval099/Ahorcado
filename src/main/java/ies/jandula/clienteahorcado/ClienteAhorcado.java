package ies.jandula.clienteahorcado;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import ies.jandula.servidorahorcado.ServidorAhorcado;

public class ClienteAhorcado 
{
	public static final String HOST = "localhost";

    public static void main(String[] args) 
    {
    	
    	Socket socketCliente = null;
        DataInputStream flujoEntrada = null;
        DataOutputStream flujoSalida = null;
        Scanner scanner = null;

        try 
        {
            socketCliente = new Socket(HOST, ServidorAhorcado.PUERTO);
            flujoEntrada = new DataInputStream(socketCliente.getInputStream());
            flujoSalida = new DataOutputStream(socketCliente.getOutputStream());
            scanner = new Scanner(System.in);
                                    
            
            System.out.println("Introduce ELEGIR_LETRA para comenzar");
            
            flujoSalida.writeUTF(scanner.nextLine());
            
            System.out.println(flujoEntrada.readUTF());
            
            System.out.println("Letras no usadas: "+flujoEntrada.readUTF());
            
            System.out.println("Palabra escogida: "+flujoEntrada.readUTF());
            
            System.out.println("Introduce una letra: ");
            
            flujoSalida.writeUTF(scanner.nextLine());
            
            
        } 
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }
        finally
        {
        	try 
        	{
				flujoEntrada.close();
			} 
        	catch (IOException e) 
        	{
				e.printStackTrace();
			}
            try 
            {
				flujoSalida.close();
			} 
            catch (IOException e) 
            {
				e.printStackTrace();
			}
            try 
            {
				socketCliente.close();
			} 
            catch (IOException e) 
            {
				e.printStackTrace();
			}
            scanner.close();
        }
    }
}
