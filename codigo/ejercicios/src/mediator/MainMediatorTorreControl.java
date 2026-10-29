package mediator;

public class MainMediatorTorreControl {
	public static void main(String[] args) {

		ITorreControl torre = new TorreControlConcreta();

		Aeronave avion1 = new AvionComercial("VUELO-LA402", torre);
		Aeronave avion2 = new AvionComercial("VUELO-IB612", torre);
		Aeronave avion3 = new AvionComercial("VUELO-AA990", torre);

		System.out.println("=== SIMULACIÓN DE TRÁFICO AÉREO (PATRÓN MEDIATOR) ===");

		avion1.reportarPosicion();
		avion2.reportarPosicion();
		avion3.reportarPosicion();

		avion1.solicitarAutorizacion(); 
		avion2.solicitarAutorizacion(); 
		avion3.solicitarAutorizacion();

		System.out.println();
		avion1.aterrizarYLiberar();

		System.out.println();
		avion2.aterrizarYLiberar();

		System.out.println();
		avion3.aterrizarYLiberar();
	}
}
