# Librería Divisas

## Descripción

**Librería Divisas** es una biblioteca Java que permite realizar conversiones de divisas en tiempo real utilizando datos obtenidos desde la API de [Frankfurter](https://www.frankfurter.app/). Es ideal para integrarla en aplicaciones Java de escritorio o backend que necesiten trabajar con tasas de cambio actualizadas.

## Características

- Conversión de divisas en tiempo real.
- Fácil integración en proyectos Java con NetBeans u otros IDEs.
- Utiliza una API confiable y gratuita (Frankfurter).
- Métodos simples para seleccionar moneda de origen, destino y realizar conversiones.

## Requisitos

- Java JDK 8 o superior.
- NetBeans IDE u otro entorno compatible.
- Conexión a Internet (para acceder a la API).

## Instalación

1. Descarga el archivo `.jar` de la librería desde el repositorio o desde la sección de releases.
2. Agrega el `.jar` a tu proyecto en NetBeans:
   - Clic derecho sobre el proyecto → **Properties**.
   - Ve a **Libraries** → **Add JAR/Folder**.
   - Selecciona el archivo `.jar` descargado.
3. Aplica los cambios y guarda el proyecto.

## Uso

```java
import ilibreriadivisas.ConversorDivisas;

public class Main {
    public static void main(String[] args) {
        ConversorDivisas conversor = new ConversorDivisas();
        double resultado = conversor.convertir("USD", "EUR");
        System.out.println("Resultado: " + resultado + " EUR");
    }
}
```

## Funcionalidades

- `convertir(String monedaOrigen, String monedaDestino)`: Realiza la conversión entre dos monedas.
- Manejo automático de errores en caso de que la API esté fuera de servicio o las divisas no sean válidas.
- Formateo de resultados con dos decimales.

## Contribuciones

¡Las contribuciones son bienvenidas! Si deseas mejorar la librería, reportar errores o agregar nuevas funcionalidades:

1. Haz un fork del repositorio.
2. Crea una nueva rama: `git checkout -b mejora/nueva-funcionalidad`
3. Haz tus cambios y haz commit: `git commit -m "Agrega nueva funcionalidad"`
4. Envía un pull request.

## Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más información.
![alt text](image.png)
