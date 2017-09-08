import java.net.*;

public class MainAhorcadoServidor {

	static ServidorAhorcado sa;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sa = new ServidorAhorcado();
		Thread t = new Thread(sa);
		t.start();
	}

}
