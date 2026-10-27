package command;

public class MainCommandControlLuces {
    public static void main(String[] args) {
        Luz luzSala = new Luz();

        IComando encenderLuz = new EncenderLuzCommand(luzSala);
        IComando apagarLuz = new ApagarLuzCommand(luzSala);

        ControlRemoto control = new ControlRemoto();

        System.out.println("=== ACCIÓN 1: ENCENDER LA LUZ ===");
        control.setComando(encenderLuz);
        control.presionarBoton();

        System.out.println("\n=== ACCIÓN 2: APAGAR LA LUZ ===");
        control.setComando(apagarLuz);
        control.presionarBoton();
    }
}