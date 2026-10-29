package mediator;

public abstract class Aeronave {

	protected String identificador;
	protected ITorreControl torreControl;
	protected boolean autorizada = false;

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

	// Llamado por la torre (Mediator) cuando concede la pista a esta aeronave.
	public void recibirAutorizacion() {
		autorizada = true;
		System.out.println("[" + identificador + "] Autorización recibida. Procediendo al aterrizaje.");
	}

	// Llamado por la torre (Mediator) cuando la pista está ocupada.
	public void recibirEspera(int posicion) {
		autorizada = false;
		System.out.println("[" + identificador + "] En patrón de espera (posición " + posicion + " en la cola).");
	}

	public abstract void aterrizarYLiberar();
}
