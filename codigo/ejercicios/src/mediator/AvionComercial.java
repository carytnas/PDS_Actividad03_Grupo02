package mediator;

public class AvionComercial extends Aeronave {

	public AvionComercial(String identificador, ITorreControl torreControl) {
		super(identificador, torreControl);
	}

	@Override
	public boolean esPrioritaria() {
		return false;
	}
}
