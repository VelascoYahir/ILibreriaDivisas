# ILibreriaDivisas

Una librería en Java para convertir divisas utilizando `javax.json`.

## Documentación
Puedes consultar la documentación Javadoc generada en la carpeta `dist/javadoc/index.html`.

## ¿Cómo usar esta librería?
```java
 public void cargarDivisas() {
        try {
            String[] divisas = ConversorDivisas.simboloMonetario();
            
            this.cmbDivOrigen.removeAllItems();
            this.cmbDivDestino.removeAllItems();
            
            for (String divisa : divisas) {
                this.cmbDivOrigen.addItem(divisa);
                 this.cmbDivDestino.addItem(divisa);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error al cargar las divisas", JOptionPane.ERROR_MESSAGE);
        }
    }


 public double convertir(double monto) throws Exception {
        String origen = this.cmbDivOrigen.getSelectedItem().toString();
        String destino = this.cmbDivOrigen.getSelectedItem().toString();
        return ConversorDivisas.origenaDestino(origen, destino);
    }