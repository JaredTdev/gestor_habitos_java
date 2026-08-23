import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Modelo de datos
public class Habito {
    
    private String nombre;
    private int rachaActual;
    private int puntosXP;
    private LocalDate ultimaFechaCumplida;

    public Habito(String nombre) {
        this.nombre = nombre;
        this.rachaActual = 0;
        this.puntosXP = 0;
        this.ultimaFechaCumplida = null;
    }

    public void registrarProgreso(){
        LocalDate hoy = LocalDate.now();

        if (ultimaFechaCumplida != null && ultimaFechaCumplida.equals(hoy)) {
            System.out.println("⚠️ Ya registraste este hábito hoy. ¡Vuelve mañana!");
            return;
        }

        verificarRacha(); // Valida si la racha se ropmpio antes de sumar

        if (ultimaFechaCumplida == null || ultimaFechaCumplida.equals(hoy.minusDays(1))) {
            rachaActual++;
        }
        else {
            rachaActual = 1; // Primera vez o racha rota
        }

        puntosXP += 10 + (rachaActual * 2); // 10 XP base + bono por racha
        ultimaFechaCumplida = hoy;
        System.out.println("¡Progreso registrado! 🔥 Racha actual: " + rachaActual + " | "
             + (10 + rachaActual * 2) + " XP");
    }

    public void verificarRacha(){
        if (ultimaFechaCumplida == null) 
            return;

        long diasDesdeUltimo = ChronoUnit.DAYS.between(ultimaFechaCumplida, LocalDate.now());
        if (diasDesdeUltimo > 1) {
            rachaActual = 0; // se rompio la racha por pasar mas de un dia
        }
    }


    // Getters para mostrar la información
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
