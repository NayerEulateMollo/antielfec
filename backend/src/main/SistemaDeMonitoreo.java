import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class SistemaDeMonitoreo{
    
    private List<Dispositivo> dispositivos;
    private Notificacion notificacion;


        public SistemaDeMonitoreo(Dispositivo[] dispositivosArray) {
        this.dispositivos = new ArrayList<>();
        Collections.addAll(this.dispositivos, dispositivosArray);
        this.notificacion = new Notificacion();
    }

    public void monitorearConsumo(){
        for (Dispositivo dispositivo : dispositivos) {
             float consumoActual = dispositivo.getConsumoActual();
            if( consumoActual > dispositivo.getLimiteConsumo()){
                
                LocalTime horaAux = LocalTime.now();
                LocalDate fechaDate = LocalDate.now();

                String hora = horaAux.truncatedTo(ChronoUnit.SECONDS).toString();
                String fecha = fechaDate + "\n" + hora  + "\n";

                notificacion.ponerMensaje(dispositivo.getNombre(), dispositivo.getLimiteConsumo(), consumoActual, fecha);
                notificacion.enviar();
            }
        }
    }
}