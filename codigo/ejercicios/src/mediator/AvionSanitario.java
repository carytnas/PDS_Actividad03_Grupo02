package mediator;

public class AvionSanitario extends Aeronave {

	public AvionSanitario(String identificador, ITorreControl torreControl) {
		super(identificador, torreControl);
	}

	// Un traslado médico tiene prioridad sobre los vuelos regulares en espera.
	@Override
	public boolean esPrioritaria() {
		return true;
	}
}
