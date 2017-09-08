package data;

import java.io.Serializable;

public class Letra implements Serializable {

	private int i;
	private char c;
	private String palabra;
	
	public Letra(String palabra) {
		this.palabra = palabra;
	}
	
	public Letra(char c, int i) {
		this.i = i;
		this.c = c;
	}

	public int getI() {
		return i;
	}
	
	public char getC() {
		return c;
	}
	
	public char setC() {
		return c;
	}

	public void setI(int i) {
		this.i = i;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}
	
}