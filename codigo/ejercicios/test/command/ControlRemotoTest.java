package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControlRemotoTest {

	private Luz luz;
	private Ventilador ventilador;
	private ControlRemoto control;

	@BeforeEach
	void prepararSala() {
		luz = new Luz("prueba");
		ventilador = new Ventilador("prueba");
		control = new ControlRemoto(3);
	}

	@Test
	void ejecutaElComandoAsignadoAlBoton() {
		control.asignarComando(0, new EncenderLuzCommand(luz));
		control.presionarBoton(0);

		assertTrue(luz.estaEncendida());
	}

	@Test
	void unBotonPuedeReasignarseEnEjecucion() {
		control.asignarComando(0, new EncenderLuzCommand(luz));
		control.presionarBoton(0);
		control.asignarComando(0, new ApagarLuzCommand(luz));
		control.presionarBoton(0);

		assertFalse(luz.estaEncendida());
	}

	@Test
	void deshacerRestauraLaVelocidadAnteriorDelVentilador() {
		control.asignarComando(0, new CambiarVelocidadVentiladorCommand(ventilador, 2));
		control.asignarComando(1, new CambiarVelocidadVentiladorCommand(ventilador, 3));
		control.presionarBoton(0);
		control.presionarBoton(1);

		control.presionarDeshacer();
		assertEquals(2, ventilador.getVelocidad());
		control.presionarDeshacer();
		assertEquals(Ventilador.APAGADO, ventilador.getVelocidad());
	}

	@Test
	void deshacerFuncionaAunqueElMismoComandoSeEjecuteVariasVeces() {
		IComando encender = new EncenderLuzCommand(luz);
		control.asignarComando(0, encender);
		control.presionarBoton(0); // estado previo: apagada
		control.presionarBoton(0); // estado previo: encendida

		control.presionarDeshacer();
		assertTrue(luz.estaEncendida());
		control.presionarDeshacer();
		assertFalse(luz.estaEncendida());
	}

	@Test
	void laMacroSeDeshaceComoUnaSolaAccion() {
		control.asignarComando(0, new EncenderLuzCommand(luz));
		control.asignarComando(1, new MacroComando("Presentación",
				Arrays.asList(new ApagarLuzCommand(luz), new CambiarVelocidadVentiladorCommand(ventilador, 1))));
		control.presionarBoton(0);
		control.presionarBoton(1);

		control.presionarDeshacer();
		assertTrue(luz.estaEncendida());
		assertEquals(Ventilador.APAGADO, ventilador.getVelocidad());
	}

	@Test
	void unBotonSinComandoNoAfectaElHistorial() {
		control.asignarComando(0, new EncenderLuzCommand(luz));
		control.presionarBoton(0);
		control.presionarBoton(2);

		control.presionarDeshacer();
		assertFalse(luz.estaEncendida());
	}

	@Test
	void rechazaBotonesInexistentes() {
		assertThrows(IllegalArgumentException.class, () -> control.presionarBoton(5));
	}
}
