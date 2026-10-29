package mediator;

public class AvionComercial extends Aeronave {

	public AvionComercial(String identificador, ITorreControl torreControl) {
		super(identificador, torreControl);
	}

	@Override
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
