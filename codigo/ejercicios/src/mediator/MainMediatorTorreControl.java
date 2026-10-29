package mediator;

public class MainMediatorTorreControl {
	public static void main(String[] args) {

		ITorreControl torre = new TorreControlConcreta();

		Aeronave avion1 = new AvionComercial("VUELO-LA402", torre);
		Aeronave avion2 = new AvionComercial("VUELO-IB612", torre);
		Aeronave avion3 = new AvionComercial("VUELO-AA990", torre);
		Aeronave sanitario = new AvionSanitario("SANITARIO-HC01", torre);

		torre.registrarAeronave(avion1);
		torre.registrarAeronave(avion2);
		torre.registrarAeronave(avion3);
		torre.registrarAeronave(sanitario);

		System.out.println("=== SIMULACIÓN DE TRÁFICO AÉREO (PATRÓN MEDIATOR) ===");

		avion1.solicitarAutorizacion();
		avion2.solicitarAutorizacion();
		avion3.solicitarAutorizacion();
		sanitario.solicitarAutorizacion();

		System.out.println();
		avion3.aterrizarYLiberar();

		System.out.println();
		avion1.aterrizarYLiberar();

		System.out.println();
		sanitario.aterrizarYLiberar();

		System.out.println();
		avion2.aterrizarYLiberar();

		System.out.println();
		avion3.aterrizarYLiberar();
	}
}
