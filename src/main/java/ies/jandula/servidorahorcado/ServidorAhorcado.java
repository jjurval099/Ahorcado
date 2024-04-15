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
        
        Random random = new Random();
        String [] palabras = {"perro","gato","raton","comadreja"};
        
        // Posicion aleatoria de la array
        int indice = random.nextInt(palabras.length);
        
        // Seleccionar la palabra correspondiente al índice aleatorio
        String palabraEscogida = palabras[indice];
        
        List<Character> letras = new ArrayList<>();
        
        int cont = 5;
        
        try 
        {
            socketServidor = new ServerSocket(PUERTO);
            System.out.println("Escuchando en el puerto " + PUERTO);

            socketCliente = socketServidor.accept();
            flujoEntrada = new DataInputStream(socketCliente.getInputStream());
            flujoSalida = new DataOutputStream(socketCliente.getOutputStream());
            
            
            if(flujoEntrada.readUTF().equals("ELEGIR_LETRA")) 
            {
            	String partidaIniciada = "Comienza la partida";
                
                flujoSalida.writeUTF(partidaIniciada);
                
                for(char letra = 'a';letra<'z';letra++)
                {
                	letras.add(letra);
                }
                
                // Letras de la lista
                flujoSalida.writeUTF(letras.toString());
                
                // Mostracion de la palabra escogida
                flujoSalida.writeUTF(palabraEscogida);
                
                // Averiduar si la letra del cliente la contiene la palabra                 
                
                for (int i = 0; i < palabraEscogida.length(); i++)
                {
                	if(flujoEntrada.readUTF().charAt().equals(i))
                	{
                		flujoSalida.writeUTF(String.valueOf(i));
                	}
                	else
                	{
                		cont --;
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
