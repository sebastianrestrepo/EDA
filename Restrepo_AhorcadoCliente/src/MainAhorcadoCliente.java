import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import data.Letra;
import processing.core.PApplet;

public class MainAhorcadoCliente extends PApplet implements Observer {

	static ClienteAhorcado ca;
	private String palabra;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ca = new ClienteAhorcado();
		Thread t = new Thread(ca);
		t.start();
		PApplet.main("MainAhorcadoCliente");
		// do {
		// escribir();
		// } while (true);
	}

	public void setup() {
		ca.addObserver(this);
	}

	public void settings() {
		size(400, 400);
	}

	public void draw() {

	}

	public void keyReleased() {

		char letra = key;

		if (key == ENTER) {
			ca.enviarMensaje("Hola");
			System.out.println("envie: " + letra);
		}
	}

	public static void escribir() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter a sentence: ");
		String sentence = scanner.next();
		ca.enviarMensaje(sentence);
	}

	public void mouseClicked() {

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof Letra) {
			Letra miLetra = (Letra) arg;
			palabra = miLetra.getPalabra();
			//text(miLetra.getC(), 20 * miLetra.getI(), 50);
			println(miLetra.getC());
		}
		if (arg instanceof String) {
			println("se equivocó");
		}
	}

}
