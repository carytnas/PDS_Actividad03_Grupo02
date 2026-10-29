package command;

import java.util.ArrayDeque;
import java.util.Deque;

public class EncenderLuzCommand implements IComando {
	private final Luz luz;
	// Pila y no un solo campo: el mismo comando puede ejecutarse varias veces antes de deshacerse.
	private final Deque<Boolean> estadosPrevios = new ArrayDeque<Boolean>();

	public EncenderLuzCommand(Luz luz) {
		this.luz = luz;
	}

	@Override
	public void ejecutar() {
		System.out.println("[Comando] Ejecutando orden: Encender luz.");
		estadosPrevios.push(luz.estaEncendida());
		luz.encender();
	}

	@Override
	public void deshacer() {
		System.out.println("[Comando] Deshaciendo: Encender luz.");
		if (!estadosPrevios.pop()) {
			luz.apagar();
		}
	}
}
