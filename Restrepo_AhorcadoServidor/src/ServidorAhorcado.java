import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

import data.Letra;

public class ServidorAhorcado implements Runnable {

	// Cargar palabras
	private File archivo;
	private FileReader fr;
	private BufferedReader br;
	private String[] txt;
	private String[] palabras;
	//
	private Socket s;
	private ServerSocket ss;
	private final int PUERTO = 6969;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private int palabraAleatoria;
	private Letra letraEnviar;

	public ServidorAhorcado() {
		palabras = new String[5];
		palabraAleatoria = 0 /* (int) (1 + Math.random() * 5) */;
		// cargarPalabras();
		try {
			ss = new ServerSocket(PUERTO);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cargarPalabras() {

		try {
			// Apertura del fichero y creación de BufferedReader
			// archivo = new File("../data/palabras.txt");
			fr = new FileReader("palabras.txt");
			br = new BufferedReader(fr);
			System.out.println("Lectura completada: " + br);
			// br.readLine(); //Se lee la primera línea del fichero
			for (int i = 0; i < palabras.length; i++) {
				// while (br != null) {
				palabras[i] = br.readLine();
				System.out.println("Escribiendo Strings: " + palabras[i]);
				// }
			}
			System.out.println(palabras);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != fr)
					fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		try {
			Thread.sleep(200);
			while (true) {
				if (s == null) {
					System.out.println("Esperando cliente...");
					s = ss.accept();
					salida = new ObjectOutputStream(s.getOutputStream());
					entrada = new ObjectInputStream(s.getInputStream());
				} else {
					// Siempre estoy escuchando
					recibirMensaje();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void validarCaracteres(Letra letra) {
		switch (palabraAleatoria) {
		case 0:
			char[] palabraTemp = palabras[0].toCharArray();
			for (int i = 0; i < palabraTemp.length; i++) {
				if (palabraTemp[i] == letra.getC()) {
					letraEnviar = new Letra(letra.getC(), i);
				} else {
				}
			}
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;

		}
		// FINAL DEL MÉTODO VALIDAR CARÁCTERES
	}

	public void recibirMensaje() {
		try {
			System.out.println("Esperando por mensaje...");
			Object atraparObjeto = entrada.readObject();
			System.out.println("Objeto atrapado: " + atraparObjeto);
			if (atraparObjeto instanceof Letra) {

				Letra l = (Letra) atraparObjeto;
				// String s = (String) atraparObjeto;

				validarCaracteres(l);

				if (letraEnviar != null) {
					enviarMensaje(letraEnviar);
				} else {
					enviarMensaje("a");
				}

				// System.out.println("recibi: " + l.getC());
				System.out.println("recibi: " + l);

			}

			// Apenas llegue algo notificar

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

	// --------FINAL-------//
}
