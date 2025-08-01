package AntiElfec;

import java.util.List;
import java.util.Scanner;

public class AntiElfecApp {

    private static Usuario usuarioLogueado;
    private static ViviendaService viviendaService;
    private static Scanner scanner;
    private static ControladorDispositivo controlador;

    public static void main(String[] args) {
        viviendaService = new ViviendaService();
        scanner = new Scanner(System.in);
        controlador = new ControladorDispositivo();

        usuarioLogueado = new Usuario(1, "UsuarioDemo", "usuario@example.com", "password123");
        System.out.println("¡Bienvenido al sistema Anti-Elfec, " + usuarioLogueado.getNombre() + "!");

        // Simulamos algunos dispositivos
        Dispositivo d1 = new Dispositivo("1", "Aire Acondicionado", true);
        Dispositivo d2 = new Dispositivo("2", "Refrigerador", false);
        controlador.agregarDispositivo(d1);
        controlador.agregarDispositivo(d2);

        mostrarMenuPrincipal();
    }

    private static void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Gestionar Viviendas");
            System.out.println("2. Controlar Dispositivos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    menuGestionarViviendas();
                    break;
                case 2:
                    menuControlarDispositivos();
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
        scanner.close();
    }

    private static void menuControlarDispositivos() {
        int opcion;
        do {
            System.out.println("\n--- CONTROL DE DISPOSITIVOS ---");
            controlador.mostrarDispositivos();
            System.out.println("Seleccione una opción:");
            System.out.println("1. Encender o Apagar dispositivo");
            System.out.println("2. Reconectar dispositivo");
            System.out.println("3. Desconectar dispositivo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    controlador.controlarDispositivo();
                    break;
                case 2:
                    reconectarDispositivo();
                    break;
                case 3:
                    desconectarDispositivo();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void reconectarDispositivo() {
        System.out.print("Ingrese el número del dispositivo a reconectar: ");
        int index = leerEntero() - 1;
        if (index >= 0) {
            controlador.reconectarDispositivo(index);
        } else {
            System.out.println("Número invalido.");
        }
    }

    private static void desconectarDispositivo() {
        System.out.print("Ingrese el número del dispositivo a desconectar: ");
        int index = leerEntero() - 1;
        if (index >= 0) {
            controlador.desconectarDispositivo(index);
        } else {
            System.out.println("Número inválido.");
        }
    }

    private static void menuGestionarViviendas() {
        int opcion;
        do {
            System.out.println("\n--- GESTIONAR VIVIENDAS ---");
            System.out.println("1. Ver mis Viviendas");
            System.out.println("2. Añadir Vivienda");
            System.out.println("3. Modificar Vivienda");
            System.out.println("4. Eliminar Vivienda");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    verMisViviendas();
                    break;
                case 2:
                    añadirVivienda();
                    break;
                case 3:
                    modificarVivienda();
                    break;
                case 4:
                    eliminarVivienda();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void verMisViviendas() {
        System.out.println("\n--- MIS VIVIENDAS ---");
        List<Vivienda> viviendas = viviendaService.obtenerViviendasPorUsuario(usuarioLogueado.getId());
        if (viviendas.isEmpty()) {
            System.out.println("No tienes viviendas registradas.");
        } else {
            viviendas.forEach(System.out::println);
        }
    }

    private static void añadirVivienda() {
        System.out.println("\n--- AÑADIR NUEVA VIVIENDA ---");
        scanner.nextLine();
        System.out.print("Ingrese el nombre de la nueva vivienda: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la dirección de la nueva vivienda: ");
        String direccion = scanner.nextLine();

        viviendaService.añadirVivienda(nombre, direccion, usuarioLogueado.getId());
    }

    private static void modificarVivienda() {
        System.out.println("\n--- MODIFICAR VIVIENDA ---");
        verMisViviendas();

        if (viviendaService.obtenerViviendasPorUsuario(usuarioLogueado.getId()).isEmpty()) {
            System.out.println("No hay viviendas para modificar.");
            return;
        }

        System.out.print("Ingrese el ID de la vivienda a modificar: ");
        int idVivienda = leerEntero();
        scanner.nextLine();

        Vivienda viviendaExistente = viviendaService.obtenerViviendaPorId(idVivienda, usuarioLogueado.getId());
        if (viviendaExistente == null) {
            System.out.println("Vivienda no encontrada o no pertenece a tu cuenta.");
            return;
        }

        System.out.println("Vivienda actual: " + viviendaExistente);
        System.out.print("Ingrese el nuevo nombre (dejar en blanco para mantener el actual): ");
        String nuevoNombre = scanner.nextLine();
        if (nuevoNombre.isEmpty()) nuevoNombre = null;

        System.out.print("Ingrese la nueva dirección (dejar en blanco para mantener la actual): ");
        String nuevaDireccion = scanner.nextLine();
        if (nuevaDireccion.isEmpty()) nuevaDireccion = null;

        viviendaService.modificarVivienda(idVivienda, nuevoNombre, nuevaDireccion, usuarioLogueado.getId());
    }

    private static void eliminarVivienda() {
        System.out.println("\n--- ELIMINAR VIVIENDA ---");
        verMisViviendas();

        if (viviendaService.obtenerViviendasPorUsuario(usuarioLogueado.getId()).isEmpty()) {
            System.out.println("No hay viviendas para eliminar.");
            return;
        }

        System.out.print("Ingrese el ID de la vivienda a eliminar: ");
        int idVivienda = leerEntero();

        System.out.print("¿Está seguro de que desea eliminar la vivienda con ID " + idVivienda + "? (s/n): ");
        scanner.nextLine();
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {
            viviendaService.eliminarVivienda(idVivienda, usuarioLogueado.getId());
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private static int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
