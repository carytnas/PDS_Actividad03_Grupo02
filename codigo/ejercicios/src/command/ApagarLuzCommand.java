package command;

public class ApagarLuzCommand implements IComando {
    private Luz luz;

    public ApagarLuzCommand(Luz luz) {
        this.luz = luz;
    }

    @Override
    public void ejecutar() {
        System.out.println("[Comando] Ejecutando orden: Apagar luz.");
        luz.apagar();
    }
}