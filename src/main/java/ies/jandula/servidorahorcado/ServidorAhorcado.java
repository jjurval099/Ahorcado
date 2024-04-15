package ies.jandula.servidorahorcado;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServidorAhorcado
{

	public static final int PUERTO = 2000;

    public static void main(String[] args) 
    {
    	ServerSocket socketServidor = null;
    	Socket socketCliente = null;
        DataInputStream flujoEntrada = null;
        DataOutputStream flujoSalida = null;
        
        String [] palabras = {"Perro","Gato","Raton","Comadreja"};
        
        List<Character> letras = new ArrayList<>();

        try 
        {
            socketServidor = new ServerSocket(PUERTO);
            System.out.println("Escuchando en el puerto " + PUERTO);

            socketCliente = socketServidor.accept();
            flujoEntrada = new DataInputStream(socketCliente.getInputStream());
            flujoSalida = new DataOutputStream(socketCliente.getOutputStream());
            
            Random random = new Random();
            
            int vidas = 5;
            
            String palabraEscogida = palabras[random.nextInt()];
            
            if(flujoEntrada.readUTF().equals("ELEGIR_LETRA")) 
            {
            	String partidaIniciada = "Comienza la partida";
                
                flujoSalida.writeUTF(partidaIniciada);
                
                for(char letra = 'a';letra<='z';letra++)
                {
                	letras.add(letra);
                }
                
                flujoSalida.writeUTF(letras.toString());
                
                for(char letra = 'a'; letra <= 'z';letra++)
                {
                	if(letra == (Character) flujoEntrada.readUTF())
                	{
                		
                	}
                	
                }
                
            }            
            
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
            try 
            {
				socketServidor.close();
			} 
            catch (IOException e) 
            {
				e.printStackTrace();
			}
        }
    }
}
