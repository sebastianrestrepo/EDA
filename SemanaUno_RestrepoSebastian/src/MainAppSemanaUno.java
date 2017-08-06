import java.net.InetAddress;
import java.net.UnknownHostException;

import controlP5.ControlP5;
import controlP5.Textfield;
import processing.core.PApplet;

public class MainAppSemanaUno extends PApplet {

	ControlP5 cp5;
	String texto;
	InetAddress ipweb;

	public static void main(String[] args) {
		PApplet.main("MainAppSemanaUno");
	}

	public void settings() {
		size(400, 300);
	}

	public void setup() {
		cp5 = new ControlP5(this);
		int blanco = color(255);
		cp5.addTextfield("Introduce el nombre del sitio web").setPosition(width / 2 - 150, height / 2)
				.setSize(300, 30).setAutoClear(false).setColorBackground(blanco).setColorLabel(0).setColor(0);
	}

	public void draw() {
		background(255);
		tomarDireccion();
		encontrarIP();
		mostrarIP();
	}

	public void tomarDireccion() {
		texto = cp5.get(Textfield.class, "Introduce el nombre del sitio web").getText();
	}

	public void encontrarIP() {
		try {
			ipweb = InetAddress.getByName(texto);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ipweb);
	}
	
	public void mostrarIP() {
		fill(0);
		text("La dirección IP es: " + ipweb.getHostAddress(), width/2-80, height/2-50);
	}

	// FINAL DE LA CLASE MAIN
}
