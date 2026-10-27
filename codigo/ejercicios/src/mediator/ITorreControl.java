package mediator;

public interface ITorreControl {
	
	void registrarAeronave(Aeronave aeronave);

	void solicitarAterrizaje(Aeronave aeronave);

	void liberarPista(Aeronave aeronave);
}