package mediator;

import java.util.ArrayList;
import java.util.List;

public class TorreControlConcreta implements ITorreControl {
	
	private List<Aeronave> listaAeronaves = new ArrayList<Aeronave>();
	private boolean pistaOcupada = false;
	private String avionEnPista = null;

	@Override
	public void registrarAeronave(Aeronave aeronave) {
		listaAeronaves.add(aeronave);
	}

	@Override
	public void solicitarAterrizaje(Aeronave aeronave) {
		System.out.println("\n[TORRE] Solicitud recibida de la aeronave: " + aeronave.getIdentificador());

		if (!pistaOcupada) {
			pistaOcupada = true;
			avionEnPista = aeronave.getIdentificador();
			System.out.println("[TORRE] Autorizado el aterrizaje para: " + avionEnPista + ". Pista ocupada.");
		} else {
			System.out.println("[TORRE] Pista ocupada actualmente por [" + avionEnPista + "]. "
					+ aeronave.getIdentificador() + ", manténgase en patrón de espera.");
		}
	}

	@Override
	public void liberarPista(Aeronave aeronave) {
		if (avionEnPista != null && avionEnPista.equals(aeronave.getIdentificador())) {
			System.out.println("[TORRE] La aeronave " + aeronave.getIdentificador() + " ha liberado la pista.");
			pistaOcupada = false;
			avionEnPista = null;
		} else {
			System.out.println("[TORRE AVISO] La aeronave " + aeronave.getIdentificador()
					+ " intentó liberar la pista pero no estaba asignada a ella.");
		}
	}
}