package command;

public class Ventilador {

	public static final int APAGADO = 0;
	public static final int VELOCIDAD_MAXIMA = 3;

	private final String ubicacion;
	private int velocidad = APAGADO;

	public Ventilador(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public void setVelocidad(int velocidad) {
		if (velocidad < APAGADO || velocidad > VELOCIDAD_MAXIMA) {
			throw new IllegalArgumentException("Velocidad fuera de rango: " + velocidad);
		}
		this.velocidad = velocidad;
		System.out.println("-> [Ventilador " + ubicacion + "] "
				+ (velocidad == APAGADO ? "APAGADO." : "Velocidad " + velocidad + "."));
	}

	public int getVelocidad() {
		return velocidad;
	}
}
