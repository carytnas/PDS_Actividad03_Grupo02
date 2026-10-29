package command;

import java.util.ArrayList;
import java.util.List;

public class MacroComando implements IComando {
	private final String nombre;
	private final List<IComando> comandos;

	public MacroComando(String nombre, List<IComando> comandos) {
		this.nombre = nombre;
		this.comandos = new ArrayList<IComando>(comandos);
	}

	@Override
	public void ejecutar() {
		System.out.println("[Macro] Activando escena: " + nombre + ".");
		for (IComando comando : comandos) {
			comando.ejecutar();
		}
	}

	// Se deshace en orden inverso para volver exactamente al estado anterior a la escena.
	@Override
	public void deshacer() {
		System.out.println("[Macro] Deshaciendo escena: " + nombre + ".");
		for (int i = comandos.size() - 1; i >= 0; i--) {
			comandos.get(i).deshacer();
		}
	}
}
