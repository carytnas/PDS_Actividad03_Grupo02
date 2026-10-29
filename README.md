# PDS_Actividad03_Grupo02

Actividad 03 de Patrones de Diseño de Software (Maestría en Software, UPS): patrones de comportamiento Mediator y Command, implementados en Java.

Grupo 02: Espinoza Adrian, Jara Pablo, Lara Miguel, Torres Carina, Uyaguari Sebastián.

## Escenarios

- **Mediator – Torre de control.** Una torre coordina el uso de una única pista entre varias aeronaves. Los vuelos regulares se atienden por orden de llegada y los vuelos sanitarios tienen prioridad. Las aeronaves no se conocen entre sí: solo hablan con la torre.
- **Command – Control remoto domótico.** Un control con varios botones acciona la luz y el ventilador de una sala de reuniones. Cada botón guarda un comando que se puede reasignar en ejecución, el control lleva un historial para deshacer y una escena ("Modo presentación") agrupa varios comandos en uno.

## Estructura

| Ruta | Contenido |
| --- | --- |
| `codigo/ejercicios/src/mediator` | Mediator: torre de control, aeronaves comerciales y sanitarias |
| `codigo/ejercicios/src/command` | Command: control remoto, comandos de luz y ventilador, macrocomando y deshacer |
| `codigo/ejercicios/test` | Pruebas unitarias (JUnit 5) de ambos patrones |
| `Diagramas/Mediator-Command.drawio` | Diagramas UML: original (sin patrón) y aplicado de cada patrón |
| `documentacion/` | Informe y presentación |

## Compilar y ejecutar

Requiere Java 8 o superior. Desde `codigo/ejercicios`:

```bash
javac -encoding UTF-8 -d bin src/mediator/*.java src/command/*.java
java -cp bin mediator.MainMediatorTorreControl
java -cp bin command.MainCommandControlLuces
```

Pruebas unitarias (requiere Maven):

```bash
mvn test
```

También se puede importar `codigo/ejercicios` en Eclipse como proyecto Java (las pruebas usan la biblioteca JUnit 5 incluida en Eclipse) o como proyecto Maven.
