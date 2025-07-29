package AntiElfec;

public class Dispositivo {
    private String id;
    private String nombre;
    private boolean encendido;
    private boolean conectado;

    public Dispositivo(String id, String nombre, boolean conectado) {
        this.id = id;
        this.nombre = nombre;
        this.conectado = conectado;
        this.encendido = false;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean estaEncendido() {
        return encendido;
    }

    public boolean estaConectado() {
        return conectado;
    }

    public void encender() {
        if (!conectado) {
            System.out.println("Conexión perdida con el dispositivo.");
            return;
        }
        encendido = true;
        System.out.println("Dispositivo " + nombre + " Encendido.");
    }

    public void apagar() {
        if (!conectado) {
            System.out.println("Conexión perdida");
            return;
        }
        encendido = false;
        System.out.println("Dispositivo: " + nombre + ": Apagado.");
    }

    public void reconectar() {
        conectado = true;
        System.out.println("Dispositivo: " + nombre + ": Reconectado.");
    }

    public void desconectar() {
        conectado = false;
        System.out.println("Dispositivo: " + nombre + ": Desconectado.");
    }

    public void mostrarEstado() {
        String estado = encendido ? "Encendido" : "Apagado";
        String conexion = conectado ? "Conectado" : "Desconectado";
        System.out.println( nombre + "\n   Estado: " + estado + " \n  " + conexion);
    }
}
