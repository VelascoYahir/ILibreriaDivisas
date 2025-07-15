
package ilibreriadivisas;
import java.io.BufferedReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;




/**
 * Esta clase permite la conversión de divisas en tiempo real utilizando la API de Frankfurter.
 * Proporciona métodos para:
 * <ul>
 *     <li>Convertir entre dos monedas</li>
 *     <li>Obtener el listado de símbolos monetarios disponibles</li>
 *     <li>Consultar el valor actual del USD a MXN</li>
 *     <li>Obtener las tarifas de conversión actuales desde el EUR</li>
 * </ul>
 * 
 * Utiliza peticiones HTTP y analiza las respuestas JSON.
 * 
 * @author Velasco Luis Yahir Fermin
 * @author Luis Ángel Mateos Ortiz
 * @version 1.0
 */
public final class ConversorDivisas {
  
     /**
     * Convierte el valor unitario de una moneda base a una moneda destino.
     *
     * @param Base    Código de la moneda de origen (por ejemplo, "USD", "EUR").
     * @param Destino Código de la moneda destino (por ejemplo, "MXN", "JPY").
     * @return Valor de conversión desde la moneda base a la moneda destino.
     */
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
    
    
    
     /**
     * Obtiene todos los símbolos monetarios disponibles desde la API de Frankfurter.
     *
     * @return Un arreglo de strings con los códigos de monedas disponibles (por ejemplo, "USD", "EUR").
     */
    public static String [] simboloMonetario(){
    
        StringBuilder jsonResponse = new StringBuilder();
       
        
         try {
            // Endpoint devuelve un JSON 
            URL url = new URL("https://api.frankfurter.app/currencies ");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //String jsonResponse = in.readLine();
            String inputLine;
            
            while ((inputLine = in.readLine()) != null) {
            jsonResponse.append(inputLine);
        }
            
            in.close();
            connection.disconnect();
           

             // Procesar el JSON para extraer los símbolos (claves)
            String jsonStr = jsonResponse.toString();
            jsonStr = jsonStr.substring(1, jsonStr.length() - 1);
            String[] arreglojson = jsonStr.split(","); 
            String[] arreglofinalsimbolos = new String[arreglojson.length];
           
        for (int i = 0; i < arreglojson.length; i++) {
            String[] keyValue = arreglojson[i].split(":"); 
            String simbolo = keyValue[0].replace("\"", "").trim(); 
            arreglofinalsimbolos[i] = simbolo; // Ejemplo: "USD", "EUR", etc.
                
            }
                
                return arreglofinalsimbolos;
 

   
            }   catch (Exception e) {
                e.printStackTrace();
                                }
                                
                   return new String [0];
  
    }
    
    
    
    /**
     * Obtiene el valor actual del dólar estadounidense (USD) en pesos mexicanos (MXN).
     *
     * @return Valor del USD en MXN según la fecha actual.
     */
    public static double usdaMXN(){
        
        double usd = 0.0;
        LocalDate fechaActual = LocalDate.now();
        
         try {
            // Endpoint devuelve un JSON 
            URL url = new URL("https://api.frankfurter.dev/v1/" + fechaActual + "?base=USD&symbols=MXN");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            // Obtener respuesta
            InputStream responseStream = connection.getInputStream();
            JsonReader jsonReader = Json.createReader(responseStream);
            JsonObject jsonObject = jsonReader.readObject();

            // Acceder a los datos
            String base = jsonObject.getString("base");
            String date = jsonObject.getString("date");
            JsonObject rates = jsonObject.getJsonObject("rates");
            usd = rates.getJsonNumber("MXN").doubleValue();

            // Mostrar los datos
            //System.out.println(base + " = "  +  usd + " MXN" );

            jsonReader.close();
    
            }   catch (Exception e) {
                      e.printStackTrace();
                                }
         return usd;
    }
    
    
    
    /**
     * Obtiene las tarifas de conversión más recientes desde el euro (EUR) a otras monedas.
     *
     * @return Un arreglo de strings con el formato "CÓDIGO_MONEDA: valor" (por ejemplo, "USD: 1.13").
     */
    public static String[] tarifasActuales(){
        
      
        List<String> listaTarifas = new ArrayList<>();
        
            try {
            // Endpoint devuelve un JSON 
            URL url = new URL("https://api.frankfurter.dev/v1/latest");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            
           
            InputStream is = connection.getInputStream();
            JsonReader reader = Json.createReader(is);
            JsonObject jsonObject = reader.readObject();
            JsonObject rates = jsonObject.getJsonObject("rates");

            for (String moneda : rates.keySet()) {
                double valor = rates.getJsonNumber(moneda).doubleValue();
                listaTarifas.add(moneda + ": " + valor);
                
            }
              
            reader.close();
            connection.disconnect();
              
            
            
            }   catch (Exception e) {
                e.printStackTrace();
                                }
            
           return listaTarifas.toArray(new String[0]);
    
    }
    
    
    
}
