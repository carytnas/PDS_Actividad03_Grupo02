package command;

import java.util.ArrayDeque;
import java.util.Deque;

public class CambiarVelocidadVentiladorCommand implements IComando {
	private final Ventilador ventilador;
	private final int velocidad;
	private final Deque<Integer> velocidadesPrevias = new ArrayDeque<Integer>();

	public CambiarVelocidadVentiladorCommand(Ventilador ventilador, int velocidad) {
		this.ventilador = ventilador;
		this.velocidad = velocidad;
	}

	@Override
	public void ejecutar() {
		System.out.println("[Comando] Ejecutando orden: Ventilador a velocidad " + velocidad + ".");
		velocidadesPrevias.push(ventilador.getVelocidad());
		ventilador.setVelocidad(velocidad);
	}

	@Override
	public void deshacer() {
		System.out.println("[Comando] Deshaciendo: Ventilador a velocidad " + velocidad + ".");
		ventilador.setVelocidad(velocidadesPrevias.pop());
	}
}
