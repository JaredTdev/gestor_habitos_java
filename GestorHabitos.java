import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
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

    // Constantes de asignacion de archivos en el almacenamiento local
    private final String ARCHIVO_HABITOS = "habitos_data.txt";
    private final String ARCHIVO_PREMIOS = "premios_data.txt";
    private final String ARCHIVO_PERFIL = "perfil_data.txt";

    /**
     * Constructor del gestor. Inicializa colecciones y carga el estado previo del juego.
     */
    public GestorHabitos() {
        this.listaHabitos = new ArrayList<>();
        this.tiendaPremios = new ArrayList<>();
        cargarTodo();
    }

    // ==========================================
    // SECCIÓN: LOGICA DE NEGOCIO (HÁBITOS)
    // ==========================================

    public void agregarHabito(String nombre) {
        listaHabitos.add(new Habito(nombre));
        System.out.println("✅ Hábito '" + nombre + "' añadido con éxito.");
        guardarTodo(); // Persistencia inmediata ante cambios de estado
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
        //listaHabitos.get(indice).registrarProgreso();
        // Ejecuta la accion y captura la recompensa en XP
        int xpGanada = listaHabitos.get(indice).registrarProgreso();
        if (xpGanada > 0) {
            this.xpTotalContador += xpGanada;
            this.xpDisponibleMonedas += xpGanada;
            guardarTodo();
        }
    }
    

     /**
     * Serializa y sobrescribe el estado del programa completo en almacenamiento plano.
     */
    private void guardarTodo() {
        try {
            // 1. Guardar estructura de habitos
            PrintWriter writeH = new PrintWriter(new FileWriter(ARCHIVO_HABITOS));
            for (Habito h : listaHabitos) writeH.println(h.toFileString());
            writeH.close();

            // 2. Guardar estructura de la tienda
            PrintWriter writerP = new PrintWriter(new FileWriter(ARCHIVO_PREMIOS));
            for( Recompensa r : tiendaPremios) writerP.println(r.toFileString());
            writerP.close();

            // 3. Guardar atributos de perfil global
            PrintWriter writerPerfil = new PrintWriter(new FileWriter(ARCHIVO_PERFIL));
            writerPerfil.println(xpTotalContador + ";" + xpDisponibleMonedas);
            writerPerfil.close();
        } catch (Exception e) {
            System.out.println("!!! Error fatal al guardar los datos en disco: " + e.getMessage());
        }
    }

    /**
     * Deserializa los datos en texto del disco y reconstruye el estado del software.
     */
    private void cargarTodo() {
        try {
            // 1. Carga y descomposicion del perfil financiero
            File fPerfil = new File(ARCHIVO_PERFIL);
            if (fPerfil.exists()) {
                BufferedReader r = new BufferedReader(new FileReader(fPerfil));
                String[] p = r.readLine().split(";");
                this.xpTotalContador = Integer.parseInt(p[0]);
                this.xpDisponibleMonedas = Integer.parseInt(p[1]);
                r.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
