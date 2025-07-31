# Librería Divisas

## Descripción

**Librería Divisas** es una biblioteca Java que permite realizar conversiones de divisas en tiempo real utilizando datos obtenidos desde la API de [Frankfurter](https://www.frankfurter.app/). Es ideal para integrarla en aplicaciones Java de escritorio o backend que necesiten trabajar con tasas de cambio actualizadas.

## Características

- Conversión de divisas en tiempo real.

<img width="1075" height="745" alt="image" src="https://github.com/user-attachments/assets/f203ecc0-bb35-472a-9ff8-6019a4605f81" />


- Fácil integración en proyectos Java con NetBeans u otros IDEs.
- Utiliza una API confiable y gratuita (Frankfurter).

<img width="1035" height="682" alt="image" src="https://github.com/user-attachments/assets/18555d70-26a1-440e-b0be-c3e009dc4655" />


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


Este metodo obtiene la tarifa actual de una moneda a otra y es el que hace que el componente vizual funcione.

public static double origenaDestino(String Base, String Destino){
        
         double tarifadestino = 0.0;   
         LocalDate fechaActual = LocalDate.now();
        

         try {
             
            // Endpoint devuelve un JSON 

            String urlfrankenstein= "https://api.frankfurter.dev/v1/" + fechaActual + "?base="+Base+"&symbols="+Destino; // Se modifica el link de la solicitud a la api en el constructor, la moneda base a la moneda destino
            
            
            URL url = new URL(urlfrankenstein);    // se crea el objeto "url" que contiene la direccion web           
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abre una conexión HTTP a la URL especificada.
            connection.setRequestMethod("GET"); // Define el tipo de solicitud HTTP que se va a realizar."GET", es el tipo de solicitud que se usa para obtener datos del servidor 
            
            
            // Obtener respuesta
            InputStream responseStream = connection.getInputStream();  // Abre un flujo de datos (InputStream) desde la conexión HTTP. permite leer la respuesta JSON que envía la api
            JsonReader jsonReader = Json.createReader(responseStream); // Crea un lector de JSON
            JsonObject jsonObject = jsonReader.readObject(); // Crea un lector de JSON y lo guarda en un jsonObject. Asi se puede acceder a los valores del Json con los sig metodos

            // Acceder a los datos

            JsonObject ratesd = jsonObject.getJsonObject("rates");
            
            tarifadestino  = ratesd.getJsonNumber(Destino).doubleValue();

            
            jsonReader.close();
    
            }   catch (Exception e) {
                      e.printStackTrace();
                                }
         
         return tarifadestino;
         
         
    }

```

## Funcionalidades

- `convertir(String monedaOrigen, String monedaDestino)`: Realiza la conversión entre dos monedas.
- Manejo automático de errores en caso de que la API esté fuera de servicio o las divisas no sean válidas.

<img width="1076" height="746" alt="image" src="https://github.com/user-attachments/assets/d8eee824-f25e-43d9-aafe-fbd410854f4d" />

<img width="1083" height="753" alt="image" src="https://github.com/user-attachments/assets/3732d3ab-63a7-43a4-9a87-72712d58cf7b" />

- Formateo de resultados con dos decimales.

 <img width="1086" height="757" alt="image" src="https://github.com/user-attachments/assets/21634343-2e86-4ce2-8442-928d1f3b9f96" />


## Contribuciones

¡Las contribuciones son bienvenidas! Si deseas mejorar la librería, reportar errores o agregar nuevas funcionalidades:

1. Haz un fork del repositorio.
2. Crea una nueva rama: `git checkout -b mejora/nueva-funcionalidad`
3. Haz tus cambios y haz commit: `git commit -m "Agrega nueva funcionalidad"`
4. Envía un pull request.

## Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más información.
![alt text](image.png)


## Link video de explicacion en YouTube

https://youtu.be/47cZp_ZusNw?si=RTFvOAnn2O2eSQ8y
