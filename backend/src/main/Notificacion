public class Notificacion {
    private String mensaje;
    private String fecha;
    
    public Notificacion(String mensaje, String fecha){
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public Notificacion() {
    }

    public void ponerMensaje(String dispositivo, float limiteConsumo, float consumoActual, String fech ){
        this.mensaje= "El dispositivo " + dispositivo + "\n" +
                        "ha sobrepasado el limite de " + limiteConsumo +" con un consumo registrado " + consumoActual;
        
        this.fecha = fech;

    }



    public void enviar(){
        System.out.print("----------------------------------------------------------------------------------"+"\n"+
                        "       Notificacion de Consumo Excesivo" + "\n"+
                        mensaje + "\n" + 
                        "fecha y hora" +"\n"+
                        fecha);
    }
}
