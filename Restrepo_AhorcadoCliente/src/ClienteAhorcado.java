import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

public class ClienteAhorcado extends Observable implements Runnable{

	private Socket s;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;

	public ClienteAhorcado() {
		try {
			s = new Socket(InetAddress.getByName("127.0.0.1"), 6969);
			salida = new ObjectOutputStream(s.getOutputStream());
			entrada = new ObjectInputStream(s.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(200);
			while(true) {
				recibirMensaje();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void recibirMensaje() {
		try {
			System.out.println("Esperando por mensaje...");
			Object atraparObjeto = entrada.readObject();
			
	 //Apenas llegue algo notificar
			setChanged();
			notifyObservers(atraparObjeto);
			clearChanged();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void enviarMensaje(Object obj) {
		try {
			salida.writeObject(obj);
			salida.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	//---------FINAL------//
}
