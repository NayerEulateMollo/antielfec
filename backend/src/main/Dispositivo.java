import java.util.concurrent.ThreadLocalRandom;

public class Dispositivo {

    private int id;
    private String nombre;
    private float consumoActual;
    private float limiteConsumo;

    public Dispositivo(int id,String nombre, float limiteConsumo){
        this.id = id;
        this. nombre = nombre;
        this.limiteConsumo = limiteConsumo;
    }

    public float getConsumoActual(){
        float consumoMin = 50.0f;  
        float consumoMax = 200.0f;

        consumoActual = ThreadLocalRandom.current().nextFloat() * (consumoMax - consumoMin) + consumoMin;
        return consumoActual;
    }


    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public void setConsumoActual(float consumoActual) {
        this.consumoActual = consumoActual;
    }
    
    public float getLimiteConsumo() {
        return limiteConsumo;
    }
    
    public void setLimiteConsumo(float limiteConsumo) {
        this.limiteConsumo = limiteConsumo;
    }
    
    }