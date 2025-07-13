/*
    * El siguiente codigo permite la conversion de divisas, contando con un amplio catalogo de opciones.
    * 
    * @author Velasco Luis Yahir Fermin y Mateos Luis Angel
    * @version 1.0
*/
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

public class ConversorDivisas {
  

    public  static double origenaDestino(String Base, String Destino){
         double tarifadestino = 0.0;   
         LocalDate fechaActual = LocalDate.now();
            /**
            * Convierte el valor unitario de una moneda a otra
            * @param  origen Codigo de la moneda de origen
            * @param  destino Codigo de la moneda destino
            * @param  cantidad Cantidad a convertir
            * @return El monto convertido en la moneda de destino
            */
        
         try {
            // Endpoint devuelve un JSON 
            
            
            String urlfrankenstein= "https://api.frankfurter.dev/v1/" + fechaActual + "?base="+Base+"&symbols="+Destino; // Se modifica el link de la solicitud a la api en el constructor, la moneda base a la moneda destino
            //String URL2 = urlfrankenstein + Base + "&Symbols=" + Destino;
            
            
            URL url = new URL(urlfrankenstein);    // se crea el objeto "url" que contiene la direccion web           
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abre una conexión HTTP a la URL especificada.
            connection.setRequestMethod("GET"); // Define el tipo de solicitud HTTP que se va a realizar."GET", es el tipo de solicitud que se usa para obtener datos del servidor 
            
            
            // Obtener respuesta
            InputStream responseStream = connection.getInputStream();  // Abre un flujo de datos (InputStream) desde la conexión HTTP. permite leer la respuesta JSON que envía la api
            JsonReader jsonReader = Json.createReader(responseStream); // Crea un lector de JSON
            JsonObject jsonObject = jsonReader.readObject(); // Crea un lector de JSON y lo guarda en un jsonObject. Asi se puede acceder a los valores del Json con los sig metodos

            // Acceder a los datos
            String base = jsonObject.getString("base"); 
           
            
            String date = jsonObject.getString("date");
            JsonObject ratesd = jsonObject.getJsonObject("rates");
            
            tarifadestino  = ratesd.getJsonNumber(Destino).doubleValue();

            // Mostrar los datos
           // System.out.println("Base: " + base);
           // System.out.println("Fecha: " + date);
           
           
            //System.out.println(Base + " = "  +  tarifadestino + " " + Destino);
            //System.out.println(tarifadestino); // Se imprime solo el valor unitario de la moneda basa a la moneda destino
            
            //String tarifadStr = String.valueOf(tarifadestino);
            //System.out.println(tarifadStr);
            
            
            jsonReader.close();
    
            }   catch (Exception e) {
                      e.printStackTrace();
                                }
         return tarifadestino;
         
         
    }
    
    
    
       
    
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
            

            //System.out.println("Respuesta JSON:");
            //System.out.println(contenido.toString());

   
            }   catch (Exception e) {
                e.printStackTrace();
                                }
                                
                   return new String [0];
  
    }
    
    
    
    
    
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
