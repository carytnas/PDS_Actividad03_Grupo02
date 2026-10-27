package mediator;

public class AvionComercial extends Aeronave {

    public AvionComercial(String identificador, ITorreControl torreControl) {
        super(identificador, torreControl);
    }

    @Override
    public void aterrizarYLiberar() {
        System.out.println("[" + identificador + "] Realizando maniobra de aterrizaje...");
        
        torreControl.liberarPista(this);
    }
}