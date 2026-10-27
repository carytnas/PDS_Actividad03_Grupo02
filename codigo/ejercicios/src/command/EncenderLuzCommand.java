package command;

public class EncenderLuzCommand implements IComando {
	private Luz luz;

	public EncenderLuzCommand(Luz luz) {
		this.luz = luz;
	}

	@Override
	public void ejecutar() {
		System.out.println("[Comando] Ejecutando orden: Encender luz.");
		luz.encender();
	}
}