# CloudCombat

CloudCombat es un juego inspirado en el clásico "Hundir la Flota" y "Buscaminas". En este juego, los jugadores se adentran en el mundo de la aviación, donde el objetivo es derribar los aviones enemigos mientras protegen sus propias fuerzas aéreas.

![](C:\Users\david\AppData\Roaming\marktext\images\2024-03-04-21-29-55-image.png)

## Requerimientos

- Maven
- JDK 11
- Inno Setup

## Para los desarrolladores

Instrucciones de construcción del proyecto:

```bash
mvn clean package 
```

## Guía de Usuario

Bienvenido a la guía de usuario de nuestro juego. Aquí encontrarás una descripción detallada de las funciones disponibles y cómo disfrutar al máximo de la experiencia de juego.

#### Menú Principal

Al abrir el juego, te recibirán las siguientes opciones en el menú principal:

- **Start Game**: Comienza una nueva partida.

- **Settings**: Personaliza el volumen, tipo de música, fondo, entre otros ajustes.

- **Exit**: Cierra la aplicación.

#### Iniciar una Partida

Al seleccionar "Start Game", accederás al selector de dificultad. Actualmente, solo está disponible el modo fácil, donde enfrentarás a una IA que dispara puntos aleatoriamente.

Una vez en la pantalla del juego, podrás iniciar la partida. Doce aviones serán colocados aleatoriamente en tu tablero y otros doce en el tablero de la IA.

Tu objetivo es destruir los doce aviones enemigos antes de que encuentren los tuyos.

Cada vez que hagas clic en una zona del tablero, la IA realizará su jugada.

#### Puntuaciones

Después de ganar, podrás consultar tus mejores puntuaciones en una tabla clasificatoria (de momento no disponible en la versión ejecutable). 

#### Reiniciar el Juego

Para jugar de nuevo, simplemente presiona el botón "Reset Game" y luego "Start Game".

## Futuras Versiones

En esta versión beta, el juego aún no cuenta con mecánicas estratégicas, como los mencionados al principio. Sin embargo, estamos trabajando en implementar una función que te permitirá conocer el número de casillas cercanas a los aviones enemigos, similar al buscaminas pero al contrario.

Además, planeamos corregir errores de jugabilidad, introducir nuevas tipografías, mejorar la interfaz y añadir más funcionalidades para mejorar la experiencia de juego.
