public class Recompensa {

    private String nombre;
    private int costoXP;

    public Recompensa(String nombre, int costoXP){
        this.nombre = nombre;
        this.costoXP = costoXP;
    }

    // Convierte el premio a texto para el archivo
    public String toFileString(){
        return nombre + ";" + costoXP;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCostoXP() {
        return costoXP;
    }
    
}
