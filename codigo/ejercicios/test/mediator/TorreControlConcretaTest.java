package mediator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TorreControlConcretaTest {

	private TorreControlConcreta torre;

	@BeforeEach
	void crearTorre() {
		torre = new TorreControlConcreta();
	}

	private Aeronave comercial(String id) {
		Aeronave aeronave = new AvionComercial(id, torre);
		torre.registrarAeronave(aeronave);
		return aeronave;
	}

	@Test
	void autorizaALaPrimeraAeronaveCuandoLaPistaEstaLibre() {
		Aeronave a1 = comercial("A1");
		a1.solicitarAutorizacion();

		assertSame(a1, torre.getAeronaveEnPista());
		assertTrue(a1.isAutorizada());
	}

	@Test
	void respetaElOrdenDeLlegadaEntreVuelosRegulares() {
		Aeronave a1 = comercial("A1");
		Aeronave a2 = comercial("A2");
		Aeronave a3 = comercial("A3");
		a1.solicitarAutorizacion();
		a2.solicitarAutorizacion();
		a3.solicitarAutorizacion();

		a1.aterrizarYLiberar();
		assertSame(a2, torre.getAeronaveEnPista());
		a2.aterrizarYLiberar();
		assertSame(a3, torre.getAeronaveEnPista());
	}

	@Test
	void unVueloSanitarioAdelantaALosVuelosRegularesEnEspera() {
		Aeronave a1 = comercial("A1");
		Aeronave a2 = comercial("A2");
		Aeronave sanitario = new AvionSanitario("S1", torre);
		torre.registrarAeronave(sanitario);
		a1.solicitarAutorizacion();
		a2.solicitarAutorizacion();
		sanitario.solicitarAutorizacion();

		a1.aterrizarYLiberar();
		assertSame(sanitario, torre.getAeronaveEnPista());
		assertFalse(a2.isAutorizada());
	}

	@Test
	void noPermiteAterrizarSinAutorizacion() {
		Aeronave a1 = comercial("A1");
		Aeronave a2 = comercial("A2");
		a1.solicitarAutorizacion();
		a2.solicitarAutorizacion();

		a2.aterrizarYLiberar();
		assertSame(a1, torre.getAeronaveEnPista());
		assertEquals(1, torre.getAeronavesEnEspera());
	}

	@Test
	void ignoraSolicitudesDuplicadasYAeronavesNoRegistradas() {
		Aeronave a1 = comercial("A1");
		Aeronave a2 = comercial("A2");
		Aeronave intrusa = new AvionComercial("X1", torre);
		a1.solicitarAutorizacion();
		a2.solicitarAutorizacion();
		a2.solicitarAutorizacion();
		intrusa.solicitarAutorizacion();

		assertEquals(1, torre.getAeronavesEnEspera());
	}

	@Test
	void laPistaQuedaLibreCuandoNoHayNadieEnEspera() {
		Aeronave a1 = comercial("A1");
		a1.solicitarAutorizacion();
		a1.aterrizarYLiberar();

		assertNull(torre.getAeronaveEnPista());
	}
}
