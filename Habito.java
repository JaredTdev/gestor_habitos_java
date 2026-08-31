import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/*  
    * Clase entidad que representa un habito y encapsula las reglas de negocio
    * relacionadas con el tiempo, calculo de rachas y generacion de puntos XP.
 */
public class Habito {
    
    private String nombre;
    private int rachaActual;
    private int puntosXP; // Registro historico de XP que este habito ha aportado.
    private LocalDate ultimaFechaCumplida; // Control de itempo real para evitar fraudes.

    /*
        * Constructor para la creacion de un habito nuevo desde la interfaz.
    */
    public Habito(String nombre) {
        this.nombre = nombre;
        this.rachaActual = 0;
        this.puntosXP = 0;
        this.ultimaFechaCumplida = null;
    }

     /*
        * Constructor de sobrecarga utilizado por el gestor para reconstrui objetos 
        * basados en el historial del archivo plpano (carga de datos).
     */
    public Habito(String nombre, int rachaActual, int puntosXP, LocalDate ultimaFechaCumplida) {
        this.nombre = nombre;
        this.rachaActual = rachaActual;
        this.puntosXP = puntosXP;
        this.ultimaFechaCumplida = ultimaFechaCumplida;
    }

    /*
        * Logica de negocio: Registra el cumplimiento del habito en el dia actual.
        * @return int Cantidad de XP ganados en esta accion. Retoma 0 si ya fue completado hoy. 
        * Devuelve la XP generada en este registro para que el gestor la sume al jugador.
    */
    public int registrarProgreso(){
        LocalDate hoy = LocalDate.now();

        // Regla de validacion: Bloquear registros duplicados el mismo dia.
        if (ultimaFechaCumplida != null && ultimaFechaCumplida.equals(hoy)) {
            System.out.println("!!!  Ya registraste este hábito hoy. ¡Vuelve mañana!");
            return 0;
        }

        verificarRacha(); // Valida si la racha se ropmpio antes de sumar

        // Regla de racha: Si es primera vez o fue hecho ayer, la racha incrementa.
        if (ultimaFechaCumplida == null || ultimaFechaCumplida.equals(hoy.minusDays(1))) {
            rachaActual++;
        }
        else {
            rachaActual = 1; // Primera vez o racha rota, pasaron 2 o mas dias sin registrar
        }
        
        // Formula RPG: 10 XP base + bono multiplicador lineal por constancia
        int xpGanada = 10 + (rachaActual *2);
        puntosXP += xpGanada;
        ultimaFechaCumplida = hoy;
        System.out.println("¡Progreso registrado! *! Racha: " + rachaActual + " | + " + xpGanada + " XP");
        return xpGanada;

        //puntosXP += 10 + (rachaActual * 2); // 10 XP base + bono por racha
        //ultimaFechaCumplida = hoy;
        //System.out.println("¡Progreso registrado! *! Racha actual: " + rachaActual + " | "
        //     + (10 + rachaActual * 2) + " XP");
    }
    
    /*
        * Logica de negocio: Evalua de forma pasiva si el jugador dejo pasar mas de un dia.
        * De ser asi, resetea la racha inmediatamente.
    */
    public void verificarRacha(){
        if (ultimaFechaCumplida == null) 
            return;

        // Calcula la diferencia exacta en dias entre el ultimo registro y hoy
        long diasDesdeUltimo = ChronoUnit.DAYS.between(ultimaFechaCumplida, LocalDate.now());
        if (diasDesdeUltimo > 1) {
            rachaActual = 0; // se rompio la racha por pasar mas de un dia
        }
    }

    /*
        * Convierte el estado actual del habito en una linea almacenable.
    */
    public String toFileString(){
        String fechaStr = (ultimaFechaCumplida == null) ? "null" : ultimaFechaCumplida.toString();
        return nombre + ";" + rachaActual + ";" + puntosXP + ";" + fechaStr;
    }


    // Metodos Getters para mostrar la información
    public String getNombre() {
        return nombre;
    }

    public int getRachaActual() {
        return rachaActual;
    }

    public int getPuntosXP() {
        return puntosXP;
    }
    
}
