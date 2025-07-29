public class NotificarConsumoExcesivoMain {
    public static void main(String[] args) throws Exception {

        Dispositivo[] dispositivos = new Dispositivo[10];


        dispositivos[0] = new Dispositivo(1, "Refrigerador", 150.0f);
        dispositivos[1] = new Dispositivo(2, "Televisor", 100.0f);
        dispositivos[2] = new Dispositivo(3, "Lavadora", 200.0f);
        dispositivos[3] = new Dispositivo(4, "Microondas", 120.0f);
        dispositivos[4] = new Dispositivo(5, "Aire Acondicionado", 300.0f);
        dispositivos[5] = new Dispositivo(6, "Licuadora", 80.0f);
        dispositivos[6] = new Dispositivo(7, "Computadora", 110.0f);
        dispositivos[7] = new Dispositivo(8, "Router", 50.0f);
        dispositivos[8] = new Dispositivo(9, "Cafetera", 90.0f);
        dispositivos[9] = new Dispositivo(10, "Ventilador", 60.0f);



        SistemaDeMonitoreo sistemaDeMonitoreo = new SistemaDeMonitoreo(dispositivos);

        sistemaDeMonitoreo.monitorearConsumo();
    }
}
