package mediator;

public abstract class Aeronave {

	protected final String identificador;
	protected final ITorreControl torreControl;
	protected boolean autorizada = false;

	public Aeronave(String identificador, ITorreControl torreControl) {
		this.identificador = identificador;
		this.torreControl = torreControl;
	}

	public String getIdentificador() {
		return identificador;
	}

	public boolean isAutorizada() {
		return autorizada;
	}

	// La torre consulta este valor para decidir en qué cola ubica la solicitud.
	public abstract boolean esPrioritaria();

	public void solicitarAutorizacion() {
		torreControl.solicitarAterrizaje(this);
	}

	// Llamado por la torre (Mediator) cuando concede la pista a esta aeronave.
	public void recibirAutorizacion() {
		autorizada = true;
		System.out.println("[" + identificador + "] Autorización recibida. Procediendo al aterrizaje.");
	}

	// Llamado por la torre (Mediator) cuando la pista está ocupada o cambia el orden de la cola.
	public void recibirEspera(int posicion) {
		autorizada = false;
		System.out.println("[" + identificador + "] En patrón de espera (posición " + posicion + " en la cola).");
	}

	public void aterrizarYLiberar() {
		if (!autorizada) {
			System.out.println("[" + identificador + "] Sin autorización de la torre: no puede aterrizar.");
			return;
		}
		System.out.println("[" + identificador + "] Realizando maniobra de aterrizaje...");
		autorizada = false;
		torreControl.liberarPista(this);
	}
}
