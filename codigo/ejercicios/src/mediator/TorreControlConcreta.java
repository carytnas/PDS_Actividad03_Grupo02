package mediator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TorreControlConcreta implements ITorreControl {

	private final List<Aeronave> listaAeronaves = new ArrayList<Aeronave>();
	private final Queue<Aeronave> colaPrioritaria = new LinkedList<Aeronave>();
	private final Queue<Aeronave> colaEspera = new LinkedList<Aeronave>();
	private Aeronave aeronaveEnPista = null;

	@Override
	public void registrarAeronave(Aeronave aeronave) {
		if (!listaAeronaves.contains(aeronave)) {
			listaAeronaves.add(aeronave);
		}
	}

	@Override
	public void solicitarAterrizaje(Aeronave aeronave) {
		System.out.println("\n[TORRE] Solicitud recibida de la aeronave: " + aeronave.getIdentificador());

		if (!listaAeronaves.contains(aeronave)) {
			System.out.println("[TORRE AVISO] La aeronave " + aeronave.getIdentificador() + " no está registrada.");
			return;
		}
		if (aeronave == aeronaveEnPista || colaPrioritaria.contains(aeronave) || colaEspera.contains(aeronave)) {
			System.out.println("[TORRE AVISO] La aeronave " + aeronave.getIdentificador()
					+ " ya tiene la pista asignada o está en la cola de espera.");
			return;
		}

		if (aeronaveEnPista == null) {
			autorizar(aeronave);
		} else if (aeronave.esPrioritaria()) {
			colaPrioritaria.add(aeronave);
			System.out.println("[TORRE] Vuelo prioritario " + aeronave.getIdentificador()
					+ ": pasa delante de los vuelos regulares en espera.");
			aeronave.recibirEspera(colaPrioritaria.size());
			// Los vuelos regulares retroceden un puesto; la torre se lo comunica a cada uno.
			notificarPosiciones(colaEspera, colaPrioritaria.size());
		} else {
			colaEspera.add(aeronave);
			System.out.println("[TORRE] Pista ocupada actualmente por [" + aeronaveEnPista.getIdentificador() + "]. "
					+ aeronave.getIdentificador() + ", mantenga el patrón de espera.");
			aeronave.recibirEspera(colaPrioritaria.size() + colaEspera.size());
		}
	}

	@Override
	public void liberarPista(Aeronave aeronave) {
		if (aeronaveEnPista != aeronave) {
			System.out.println("[TORRE AVISO] La aeronave " + aeronave.getIdentificador()
					+ " intentó liberar la pista pero no estaba asignada a ella.");
			return;
		}
		System.out.println("[TORRE] La aeronave " + aeronave.getIdentificador() + " ha liberado la pista.");
		aeronaveEnPista = null;

		Aeronave siguiente = colaPrioritaria.poll();
		if (siguiente == null) {
			siguiente = colaEspera.poll();
		}
		if (siguiente != null) {
			autorizar(siguiente);
		}
	}

	public Aeronave getAeronaveEnPista() {
		return aeronaveEnPista;
	}

	public int getAeronavesEnEspera() {
		return colaPrioritaria.size() + colaEspera.size();
	}

	private void autorizar(Aeronave aeronave) {
		aeronaveEnPista = aeronave;
		System.out.println("[TORRE] Autorizado el aterrizaje para: " + aeronave.getIdentificador() + ". Pista ocupada.");
		aeronave.recibirAutorizacion();
	}

	private void notificarPosiciones(Queue<Aeronave> cola, int desplazamiento) {
		int posicion = desplazamiento;
		for (Aeronave enEspera : cola) {
			posicion++;
			enEspera.recibirEspera(posicion);
		}
	}
}
