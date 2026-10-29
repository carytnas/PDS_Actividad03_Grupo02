package mediator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TorreControlConcreta implements ITorreControl {
	
	private List<Aeronave> listaAeronaves = new ArrayList<Aeronave>();
	private Queue<Aeronave> colaEspera = new LinkedList<Aeronave>();
	private Aeronave aeronaveEnPista = null;

	@Override
	public void registrarAeronave(Aeronave aeronave) {
		listaAeronaves.add(aeronave);
	}

	@Override
	public void solicitarAterrizaje(Aeronave aeronave) {
		System.out.println("\n[TORRE] Solicitud recibida de la aeronave: " + aeronave.getIdentificador());

		if (!listaAeronaves.contains(aeronave)) {
			System.out.println("[TORRE AVISO] La aeronave " + aeronave.getIdentificador() + " no está registrada.");
			return;
		}
		if (aeronave == aeronaveEnPista || colaEspera.contains(aeronave)) {
			System.out.println("[TORRE AVISO] La aeronave " + aeronave.getIdentificador()
					+ " ya tiene la pista asignada o está en la cola de espera.");
			return;
		}

		if (aeronaveEnPista == null) {
			autorizar(aeronave);
		} else {
			colaEspera.add(aeronave);
			System.out.println("[TORRE] Pista ocupada actualmente por [" + aeronaveEnPista.getIdentificador() + "]. "
					+ aeronave.getIdentificador() + ", mantenga el patrón de espera.");
			aeronave.recibirEspera(colaEspera.size());
		}
	}

	@Override
	public void liberarPista(Aeronave aeronave) {
		if (aeronaveEnPista == aeronave) {
			System.out.println("[TORRE] La aeronave " + aeronave.getIdentificador() + " ha liberado la pista.");
			aeronaveEnPista = null;

			Aeronave siguiente = colaEspera.poll();
			if (siguiente != null) {
				autorizar(siguiente);
			}
		} else {
			System.out.println("[TORRE AVISO] La aeronave " + aeronave.getIdentificador()
					+ " intentó liberar la pista pero no estaba asignada a ella.");
		}
	}

	private void autorizar(Aeronave aeronave) {
		aeronaveEnPista = aeronave;
		System.out.println("[TORRE] Autorizado el aterrizaje para: " + aeronave.getIdentificador() + ". Pista ocupada.");
		aeronave.recibirAutorizacion();
	}
}
