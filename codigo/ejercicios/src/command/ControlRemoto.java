package command;

import java.util.ArrayDeque;
import java.util.Deque;

public class ControlRemoto {
	private final IComando[] ranuras;
	private final Deque<IComando> historial = new ArrayDeque<IComando>();

	public ControlRemoto(int numeroRanuras) {
		this.ranuras = new IComando[numeroRanuras];
	}

	public void asignarComando(int ranura, IComando comando) {
		validarRanura(ranura);
		ranuras[ranura] = comando;
	}

	public void presionarBoton(int ranura) {
		validarRanura(ranura);
		IComando comando = ranuras[ranura];
		if (comando == null) {
			System.out.println("El botón " + ranura + " no tiene ningún comando asignado.");
			return;
		}
		comando.ejecutar();
		historial.push(comando);
	}

	public void presionarDeshacer() {
		if (historial.isEmpty()) {
			System.out.println("No hay acciones para deshacer.");
			return;
		}
		historial.pop().deshacer();
	}

	private void validarRanura(int ranura) {
		if (ranura < 0 || ranura >= ranuras.length) {
			throw new IllegalArgumentException("El control solo tiene los botones 0 a " + (ranuras.length - 1) + ".");
		}
	}
}
