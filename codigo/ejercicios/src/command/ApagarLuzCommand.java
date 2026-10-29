package command;

import java.util.ArrayDeque;
import java.util.Deque;

public class ApagarLuzCommand implements IComando {
	private final Luz luz;
	private final Deque<Boolean> estadosPrevios = new ArrayDeque<Boolean>();

	public ApagarLuzCommand(Luz luz) {
		this.luz = luz;
	}

	@Override
	public void ejecutar() {
		System.out.println("[Comando] Ejecutando orden: Apagar luz.");
		estadosPrevios.push(luz.estaEncendida());
		luz.apagar();
	}

	@Override
	public void deshacer() {
		System.out.println("[Comando] Deshaciendo: Apagar luz.");
		if (estadosPrevios.pop()) {
			luz.encender();
		}
	}
}
