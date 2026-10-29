# PDS_Actividad03_Grupo02

Actividad 03 de Patrones de Diseño de Software (Maestría en Software, UPS): patrones de comportamiento Mediator y Command, implementados en Java.

Grupo 02: Espinoza Adrian, Jara Pablo, Lara Miguel, Torres Carina, Uyaguari Sebastián.

## Estructura

| Ruta | Contenido |
| --- | --- |
| `codigo/ejercicios/src/mediator` | Mediator: torre de control que coordina el aterrizaje de varias aeronaves en una pista |
| `codigo/ejercicios/src/command` | Command: control remoto que enciende y apaga una luz |
| `Diagramas/Mediator-Command.drawio` | Diagramas UML: original (sin patrón) y aplicado de cada patrón |
| `documentacion/` | Informe y presentación |

## Compilar y ejecutar

Requiere Java 8 o superior. Desde `codigo/ejercicios`:

```bash
javac -encoding UTF-8 -d bin src/mediator/*.java src/command/*.java
java -cp bin mediator.MainMediatorTorreControl
java -cp bin command.MainCommandControlLuces
```

También se puede importar `codigo/ejercicios` como proyecto de Eclipse.
