package command;

public class ControlRemoto {
	private IComando comando;

	public void setComando(IComando comando) {
		this.comando = comando;
	}

	public void presionarBoton() {
		if (comando == null) {
			System.out.println("No hay ningún comando asignado en el control remoto.");
			return;
		}
		comando.ejecutar(); 
	}
}