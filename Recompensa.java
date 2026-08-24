/*
    * Clase que representa una entidad de Recompensa (premio) en el sistema.
    * Define el modelo de datos para los articulos disponibles en la tienda.
*/
public class Recompensa {

    private String nombre;
    private int costoXP;
    
    /*
        * Constructor para instanciar una nueva recompensa personalizada.
     */
    public Recompensa(String nombre, int costoXP){
        this.nombre = nombre;
        this.costoXP = costoXP;
    }
    /*
        * Serializa el objeto a formato de texto plano para su almacenamiento.
        * @return Cadena formateada separada por puntos y comas (nombre;costo)
     */
    // Convierte el premio a texto para el archivo
    public String toFileString(){
        return nombre + ";" + costoXP;
    }

    // metodos Getters
    public String getNombre() {
        return nombre;
    }

    public int getCostoXP() {
        return costoXP;
    }
    
}
