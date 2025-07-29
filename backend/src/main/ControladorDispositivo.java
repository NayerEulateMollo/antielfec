package AntiElfec;

import java.util.ArrayList;
import java.util.Scanner;

public class ControladorDispositivo {
    private ArrayList<Dispositivo> dispositivos;

    public ControladorDispositivo() {
        dispositivos = new ArrayList<>();
    }

    public void agregarDispositivo(Dispositivo d) {
        dispositivos.add(d);
    }

    public void mostrarDispositivos() {
        System.out.println("\n Lista de dispositivos registrados:");
        System.out.println("------------------------------------");
        for (int i = 0; i < dispositivos.size(); i++) {
            System.out.print((i + 1) + ". ");
            dispositivos.get(i).mostrarEstado();
        }
        System.out.println("------------------------------------");
    }

    public void controlarDispositivo() {
        Scanner scanner = new Scanner(System.in);
        mostrarDispositivos();

        System.out.print("\nIngrese el número del dispositivo a controlar: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < dispositivos.size()) {
            Dispositivo d = dispositivos.get(index);
            System.out.println("1. Encender\n2. Apagar");
            System.out.print("Seleccione opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1: d.encender(); break;
                case 2: d.apagar(); break;
                default: System.out.println("Opción no válida.");
            }
        } else {
            System.out.println("Dispositivo no encontrado.");
        }
    }

    public void reconectarDispositivo(int index) {
        if (index >= 0 && index < dispositivos.size()) {
            dispositivos.get(index).reconectar();
        } else {
            System.out.println("Dispositivo no encontrado.");
        }
    }

    public void desconectarDispositivo(int index) {
        if (index >= 0 && index < dispositivos.size()) {
            dispositivos.get(index).desconectar();
        } else {
            System.out.println("Dispositivo no encontrado.");
        }
    }
}
