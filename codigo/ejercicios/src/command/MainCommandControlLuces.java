package command;

import java.util.Arrays;

public class MainCommandControlLuces {
	public static void main(String[] args) {
		Luz luzSala = new Luz("sala de reuniones");
		Ventilador ventiladorSala = new Ventilador("sala de reuniones");

		IComando encenderLuz = new EncenderLuzCommand(luzSala);
		IComando apagarLuz = new ApagarLuzCommand(luzSala);
		IComando ventiladorAlto = new CambiarVelocidadVentiladorCommand(ventiladorSala, 3);
		IComando ventiladorBajo = new CambiarVelocidadVentiladorCommand(ventiladorSala, 1);
		IComando modoPresentacion = new MacroComando("Modo presentación",
				Arrays.asList(new ApagarLuzCommand(luzSala), new CambiarVelocidadVentiladorCommand(ventiladorSala, 1)));

		ControlRemoto control = new ControlRemoto(4);
		control.asignarComando(0, encenderLuz);
		control.asignarComando(1, apagarLuz);
		control.asignarComando(2, ventiladorAlto);
		control.asignarComando(3, modoPresentacion);

		System.out.println("=== 1. LLEGADA A LA SALA: LUZ Y VENTILADOR ===");
		control.presionarBoton(0);
		control.presionarBoton(2);

		System.out.println("\n=== 2. ESCENA \"MODO PRESENTACIÓN\" ===");
		control.presionarBoton(3);

		System.out.println("\n=== 3. DESHACER LA ESCENA ===");
		control.presionarDeshacer();

		System.out.println("\n=== 4. REASIGNAR EL BOTÓN 2 EN EJECUCIÓN ===");
		control.asignarComando(2, ventiladorBajo);
		control.presionarBoton(2);

		System.out.println("\n=== 5. DESHACER HASTA VACIAR EL HISTORIAL ===");
		control.presionarDeshacer();
		control.presionarDeshacer();
		control.presionarDeshacer();
		control.presionarDeshacer();
	}
}
