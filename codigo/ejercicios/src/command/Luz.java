package command;

public class Luz {

	private final String ubicacion;
	private boolean encendida = false;

	public Luz(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public void encender() {
		encendida = true;
		System.out.println("-> [Luz " + ubicacion + "] ENCENDIDA.");
	}

	public void apagar() {
		encendida = false;
		System.out.println("-> [Luz " + ubicacion + "] APAGADA.");
	}

	public boolean estaEncendida() {
		return encendida;
	}
}
