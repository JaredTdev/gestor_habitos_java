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

            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, introduce un número válido: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    gestor.listaHabitos();
                    break;
                case 2:
                    gestor.listaHabitos();
                    System.out.print("Introduce el número del hábito cumplido: ");
                    int numHabito = scanner.nextInt() - 1;
                    gestor.marcarHabito(numHabito);
                    break;
                case 3:
                    System.out.print("Nombre del nuevo hábito: ");
                    String nombre = scanner.nextLine();
                    gestor.agregarHabito(nombre);
                    break;
                case 4:
                    System.out.println("¡Sigue manteniendo tus rachas altas! Adiós.");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }
        } 
        while (opcion != 4);

        scanner.close();
    }
    
}
