import java.util.ArrayList;
import java.util.List;

/*
    * Componente de Lógica de Negocio (Service Layer) y Persistencia (Data Access).
    * Centraliza las colecciones de datos, el perfil del jugador y el manejo de archivos.
 */
public class GestorHabitos {
    // Colecciones en memoria RAM (Estructuras de datos dinámicas)
    private List<Habito> listaHabitos;
    private List<Recompensa> tiendaPremios;

    // Estado Global del Jugador de Rol (RPG)
    private int xpTotalContador = 0; // XP histórica acumulada (Define el nivel, nunca disminuye)
    private int xpDisponibleMonedas = 0; // Billetera virtual (Disminuye al comprar en la tienda)

    public GestorHabitos() {
        this.listaHabitos = new ArrayList<>();
    }

    public void agregarHabito(String nombre) {
        listaHabitos.add(new Habito(nombre));
        System.out.println("✅ Hábito '" + nombre + "' añadido con éxito.");
    }

    public void listaHabitos() {
        if (listaHabitos.isEmpty()) {
            System.out.println("📬 No hay hábitos registrados aún.");
            return;
        }
        System.out.println("\n=== MIS HÁBITOS ===");
        for (int i = 0; i < listaHabitos.size(); i++){
            Habito h = listaHabitos.get(i);
            System.out.println((i + 1) + ". " + h.getNombre() + " | 🔥 Racha: " + 
            h.getRachaActual() + " dias | ⭐ XP: " + h.getPuntosXP());
        }
    }

    public void marcarHabito(int indice) {
        if (indice < 0 || indice >= listaHabitos.size()) {
            System.out.println("❌ Número de hábito inválido.");
            return;
        }
        listaHabitos.get(indice).registrarProgreso();
    }
    
}
