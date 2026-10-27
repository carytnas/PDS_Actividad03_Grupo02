package mediator;

public abstract class Aeronave {

	protected String identificador;
	protected ITorreControl torreControl;

	public Aeronave(String identificador, ITorreControl torreControl) {
		this.identificador = identificador;
		this.torreControl = torreControl;		
		torreControl.registrarAeronave(this); 
	}

	public String getIdentificador() {
		return identificador;
	}

	public void solicitarAutorizacion() {
		torreControl.solicitarAterrizaje(this);
	}

	public void reportarPosicion() {
		System.out.println("[" + identificador + "] Reportando posición actual en el espacio aéreo.");
	}

	public abstract void aterrizarYLiberar();
}