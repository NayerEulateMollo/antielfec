# Gestor de Consumo Energético Inteligente (Anti Elfec)

[cite_start]Este repositorio contiene el proyecto "Gestor de Consumo Energético Inteligente (Anti Elfec)", desarrollado como parte de la materia Sistemas de Información I en la Universidad Mayor de San Simón (UMSS), Cochabamba, Bolivia. [cite: 1, 2, 15, 16]

[cite_start]El objetivo principal de este sistema es proporcionar a los usuarios una herramienta para monitorear y gestionar su consumo eléctrico de manera inteligente, ofreciendo control sobre sus dispositivos y alertas en tiempo real. [cite: 4, 18, 25]

## Integrantes del Grupo INFO DEV

* [cite_start]Andres Montaño Frias [cite: 8]
* [cite_start]Jhonatan Alvarado Mamani [cite: 9]
* [cite_start]Marcelo Vallejos Tinta [cite: 10]
* [cite_start]Nayer Eulate Mollo [cite: 11]
* [cite_start]Nol Fernando Kantuta Mencia [cite: 12]

**Docente:** Ing. [cite_start]Marcelo Antezana Camacho [cite: 13]
[cite_start]**Auxiliar:** Marcos Velasquez Vela [cite: 14]

## Descripción del Proyecto

El "Gestor de Consumo Energético Inteligente (Anti Elfec)" es un sistema que permite a los usuarios:

* [cite_start]Medir el consumo eléctrico en tiempo real. [cite: 21]
* [cite_start]Controlar dispositivos de forma remota (encender o apagar). [cite: 21, 25]
* [cite_start]Visualizar el consumo a través de una aplicación móvil. [cite: 25]
* [cite_start]Recibir alertas relacionadas con el consumo. [cite: 25, 33]

## Arquitectura del Sistema (Nodos y Componentes)

[cite_start]El sistema está compuesto por los siguientes nodos y componentes principales: [cite: 19]

1.  [cite_start]**Dispositivo Inteligente** [cite: 20]
    * [cite_start]**Función:** Mide el consumo eléctrico en tiempo real y ejecuta órdenes remotas (encender o apagar). [cite: 21]
    * [cite_start]**Componentes:** Sensor de consumo, módulo Wi-Fi. 
    * [cite_start]**Comunicación:** Envía datos al `Servidor Aplicación` mediante MQTT o HTTP, ideal para dispositivos IoT. [cite: 23]

2.  [cite_start]**Smartphone del Usuario** [cite: 24]
    * [cite_start]**Función:** Ejecuta la aplicación `AntiElfec` para visualizar el consumo, controlar dispositivos y recibir alertas. [cite: 25]
    * [cite_start]**Componentes:** Aplicación móvil (Android), Interfaz de Usuario. 
    * [cite_start]**Comunicación:** Se conecta al `Servidor Aplicación` por HTTPS (API REST). [cite: 27]

3.  [cite_start]**Servidor Aplicación** [cite: 28]
    * [cite_start]**Función:** Gestiona la lógica del sistema, procesa solicitudes de la app móvil y controla los dispositivos. [cite: 29]
    * [cite_start]**Componentes:** Carpeta `backend`. [cite: 30]
    * [cite_start]**Comunicación:** Conecta con el `Smartphone del Usuario` por HTTPS, con la `Base de Datos` por JDBC y con el `Dispositivo Inteligente` por MQTT. [cite: 31]

4.  [cite_start]**Base de Datos** [cite: 32]
    * [cite_start]**Función:** Almacena información sobre usuarios, dispositivos, historial de consumo y alertas generadas. [cite: 33]
    * [cite_start]**Componentes:** MySQL o PostgreSQL. 
    * [cite_start]**Comunicación:** Conexión directa y segura con el `Servidor Aplicación` (JDBC). [cite: 35]

5.  [cite_start]**Router (Red Wifi)** [cite: 36]
    * [cite_start]**Función:** Proporciona conectividad de red entre los dispositivos y el `Servidor Aplicación`. [cite: 37, 38]
    * [cite_start]**Componentes:** Red WiFi. [cite: 39]
    * [cite_start]**Comunicación:** Canal de comunicación para mensajes MQTT y HTTPS. [cite: 40]

## Diagrama de Despliegue

[cite_start]A continuación, se presenta el diagrama de despliegue que ilustra cómo se distribuyen los elementos del sistema: [cite: 43, 44]

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
