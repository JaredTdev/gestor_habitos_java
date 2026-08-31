import java.util.Scanner;

/**
 * Controlador de Entrada Principal y Capa de Presentación del usuario.
 */
// Interfaz del usuario
public class Main {
    public static void main(String[] args) {
        // Inicialización de componentes centrales de control
        GestorHabitos gestor = new GestorHabitos();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Hábitos iniciales de prueba
        gestor.agregarHabito("Meditar 10 min");
        gestor.agregarHabito("Programar en Java 10 min");

        // Bucle de interfaz principal
        do {
            System.out.println("\n--- GAMIFIED HABIT TRACKER v3.0---");
            System.out.println("1. Ver estado y perfil");
            System.out.println("2. Registrar progreso de habito");
            System.out.println("3. Crear nuevo habito");
            System.out.println("4. Entrar en la tienda de recompensas");
            System.out.println("5. Salir");
            System.out.println("Elige una opcion: ");

            // Filtro de seguridad: Evita excepcione de entrada si digitan letras en el menu numerico.
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, introduce un número válido: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del teclado.

            switch (opcion) {
                case 1:
                    gestor.mostrarEstadoJugador();
                    break;
                case 2:
                    gestor.mostrarEstadoJugador();
                    System.out.println("Introduce el numero del habito cumplido: ");
                    int numHabito = scanner.nextInt() - 1;
                    gestor.marcarHabito(numHabito);
                    break;
                case 3:
                    System.out.print("Nombre del nuevo hábito: ");
                    String nombreH = scanner.nextLine();
                    gestor.agregarHabito(nombreH);
                    break;
                case 4:
                    // Desvio del flujo de la interfaz al submeni aislado de la tienda
                    break;
                case 5:
                    System.out.println("¡Sigue manteniendo tus rachas altas! Adiós.");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }
        } 
        while (opcion != 5); // Liberacion del recurso del teclado de sistema

        scanner.close();
    }

    /**
     * Subcontrolador del menú secundario dedicado a la tienda de canjes.
     */
    private static void manejarTienda(GestorHabitos gestorHabitos, Scanner scanner){
        int opTienda;
        do {
            gestorHabitos.mostrarTienda();
            System.out.println("\n--- MENÚ DE LA TIENDA ---");
            System.out.println("1. Canjear / Comprar un premio");
            System.out.println("2. Agregar un nuevo premio personalizado");
            System.out.println("3. Regresar al menú principal");
            System.out.print("Elige una opción: ");
            
            while (!scanner.hasNextInt()) {
                System.out.println("Numero invalido: ");
                scanner.next();
            }
            opTienda = scanner.nextInt();
            scanner.nextLine();
            if (opTienda == 1) {
                System.out.println("Introduce el número del premio que quieres comprar: ");
                int numPremio = scanner.nextInt() - 1;
                gestorHabitos.comprarPremio(numPremio);
            } else if (opTienda == 2) {
                System.out.println("Nombre de la recompensa (ej: Jugar 1hr): ");
                String nomPremio = scanner.nextLine();
                System.out.println("Costo en XP: ");
                int costo = scanner.nextInt();
                gestorHabitos.agregarPremio(nomPremio, costo);
            }
        } while (opTienda != 3); // Lazo de permanencia en el submenu
    }
    
}
