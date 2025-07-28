# Gestor de Consumo Energético Inteligente (Anti Elfec)

Este repositorio contiene el proyecto "Gestor de Consumo Energético Inteligente (Anti Elfec)", desarrollado como parte de la materia Sistemas de Información I en la Universidad Mayor de San Simón (UMSS), Cochabamba, Bolivia.

## Integrantes del Grupo INFO DEV

* Andres Montaño Frias
* Jhonatan Alvarado Mamani
* Marcelo Vallejos Tinta
* Nayer Eulate Mollo
* Nol Fernando Kantuta Mencia

**Docente:** Ing. Marcelo Antezana Camacho
**Auxiliar:** Marcos Velasquez Vela

## Descripción del Proyecto

El "Gestor de Consumo Energético Inteligente (Anti Elfec)" es un sistema que permite a los usuarios:

* Medir el consumo eléctrico en tiempo real.
* Controlar dispositivos de forma remota (encender o apagar).
* Visualizar el consumo a través de una aplicación móvil.
* Recibir alertas relacionadas con el consumo.

## Módulo: Gestión de Viviendas

Este módulo es una parte fundamental del proyecto **Anti-Elfec**, diseñado para permitir a los usuarios registrados administrar eficientemente las diferentes ubicaciones (viviendas) donde desean monitorear y controlar sus dispositivos inteligentes. La gestión de viviendas es esencial para organizar y contextualizar el consumo energético en múltiples propiedades o espacios dentro de una única cuenta de usuario.

### Propósito

El objetivo principal de este módulo es proporcionar una interfaz intuitiva para que los usuarios puedan:
* Registrar nuevas viviendas asociadas a su cuenta.
* Actualizar la información de las viviendas existentes (nombre, dirección).
* Eliminar viviendas que ya no necesiten gestionar.
* Visualizar un listado de todas sus viviendas registradas.

### Funcionalidades Clave

Actualmente, el módulo de **Gestión de Viviendas** implementa las siguientes operaciones CRUD (Crear, Leer, Actualizar, Eliminar) básicas:

* **Añadir Vivienda:** Permite al usuario registrar una nueva propiedad en el sistema, asignándole un nombre único y una dirección. El sistema valida que no existan nombres de vivienda duplicados para el mismo usuario.
* **Ver Viviendas:** Muestra una lista detallada de todas las viviendas que el usuario tiene registradas en su cuenta, incluyendo su ID, nombre y dirección.
* **Modificar Vivienda:** Permite al usuario seleccionar una vivienda existente por su ID y actualizar su nombre y/o dirección. Se incluye validación para prevenir nombres duplicados después de la modificación.
* **Eliminar Vivienda:** Facilita la eliminación de una vivienda del registro del usuario, previa confirmación para evitar borrados accidentales.

### Implementación Actual (Prototipo de Consola)

La versión actual de este módulo ha sido desarrollada como un **prototipo de aplicación de consola en Java**. Esto permite demostrar la lógica de negocio central y la interacción de las clases (`Usuario`, `Vivienda`, `ViviendaService`) de manera funcional, sin la complejidad de una interfaz gráfica completa o una base de datos persistente real. Los datos de las viviendas se manejan en memoria (`ArrayList`), lo que significa que se reinician cada vez que la aplicación se ejecuta.

### Próximos Pasos y Expansión

Este prototipo sienta las bases para futuras integraciones, incluyendo:
* **Persistencia de Datos:** Conexión a una base de datos (ej. MySQL/PostgreSQL) para almacenar las viviendas de forma permanente.
* **Integración con Dispositivos:** Asociación de dispositivos inteligentes a viviendas específicas.
* **Interfaz de Usuario Gráfica:** Desarrollo de una interfaz de usuario completa (móvil o web) para una experiencia de usuario más rica.
* **Validaciones y Errores:** Implementación de un manejo de errores y validaciones más robusto.

## Arquitectura del Sistema (Nodos y Componentes)

El sistema está compuesto por los siguientes nodos y componentes principales:

1.  **Dispositivo Inteligente**
    * **Función:** Mide el consumo eléctrico en tiempo real y ejecuta órdenes remotas (encender o apagar).
    * **Componentes:** Sensor de consumo, módulo Wi-Fi.
    * **Comunicación:** Envía datos al `Servidor Aplicación` mediante MQTT o HTTP, ideal para dispositivos IoT.

2.  **Smartphone del Usuario**
    * **Función:** Ejecuta la aplicación `AntiElfec` para visualizar el consumo, controlar dispositivos y recibir alertas.
    * **Componentes:** Aplicación móvil (Android), Interfaz de Usuario.
    * **Comunicación:** Se conecta al `Servidor Aplicación` por HTTPS (API REST).

3.  **Servidor Aplicación**
    * **Función:** Gestiona la lógica del sistema, procesa solicitudes de la app móvil y controla los dispositivos.
    * **Componentes:** Carpeta `backend`.
    * **Comunicación:** Conecta con el `Smartphone del Usuario` por HTTPS, con la `Base de Datos` por JDBC y con el `Dispositivo Inteligente` por MQTT.

4.  **Base de Datos**
    * **Función:** Almacena información sobre usuarios, dispositivos, historial de consumo y alertas generadas.
    * **Componentes:** MySQL o PostgreSQL.
    * **Comunicación:** Conexión directa y segura con el `Servidor Aplicación` (JDBC).

5.  **Router (Red Wifi)**
    * **Función:** Proporciona conectividad de red entre los dispositivos y el `Servidor Aplicación`.
    * **Componentes:** Red WiFi.
    * **Comunicación:** Canal de comunicación para mensajes MQTT y HTTPS.

## Diagrama de Despliegue

A continuación, se presenta el diagrama de despliegue que ilustra cómo se distribuyen los elementos del sistema:

```mermaid
graph LR
    subgraph Devices
        Smartphone["<<device>> Smartphone"] -- HTTPS --> ServidorAplicacion
        DispositivoInteligente["<<device>> Dispositivo Inteligente"] -- MQTT --> Router
    end

    subgraph Backend
        ServidorAplicacion["<<executionEnviroment>> ServidorAplicacion"]
        BaseDeDatos["<<device>> BaseDeDatos"] -- JDBC --> ServidorAplicacion
    end

    Router["<<device>> Router"] -- HTTPS --> ServidorAplicacion

    Smartphone -- "antiElfec.apk" --> Smartphone
    DispositivoInteligente -- "controlDispositivo" --> DispositivoInteligente
    BaseDeDatos -- "base.sql" --> BaseDeDatos
    ServidorAplicacion -- "carpeta backend" --> ServidorAplicacion
    Router -- "red Wifi" --> Router
