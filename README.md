# Ejercicio 6 - Polimorfismo vía Interfaces

**Curso:** CC2008 - Programación Orientada a Objetos  
**Universidad:** Universidad del Valle de Guatemala  
**Estudiante:** Roger Yorkaef Méndez García  
**Carné:** 26786

## Descripción

Sistema desarrollado en Java que simula la administración de integraciones con APIs externas de una plataforma de comercio electrónico.

El programa utiliza herencia, interfaces y polimorfismo para gestionar diferentes proveedores mediante una única lista polimórfica, permitiendo ejecutar operaciones según las capacidades de cada integración.

## Funcionalidades

- Listar las integraciones registradas y sus estados de conexión.
- Buscar integraciones mediante su identificador y consultar su configuración.
- Ordenar las integraciones por latencia promedio utilizando `Comparable`.
- Simular cobros y reintegros mediante la interfaz `Transaccional`.
- Generar documentos simulados mediante la interfaz `Documentable`.
- Consultar información de auditoría mediante la interfaz `Auditable`.
- Ignorar operaciones incompatibles sin interrumpir el funcionamiento del programa.
- Cargar automáticamente diez integraciones de diferentes proveedores.

## Estructura del proyecto

```text
├── docs/
│   ├── Analisis y diseno.pdf
│   └── UML.svg
├── src/
│   ├── Transaccional.java
│   ├── Documentable.java
│   ├── Auditable.java
│   ├── IntegracionAPI.java
│   ├── PasarelaTarjeta.java
│   ├── PasarelaTransferencia.java
│   ├── BilleteraDigital.java
│   ├── ProveedorLogistico.java
│   ├── CertificadorFEL.java
│   ├── SistemaIntegraciones.java
│   ├── VistaConsola.java
│   ├── ControladorIntegraciones.java
│   └── Main.java
├── .gitignore
└── README.md
```

## Interfaz gráfica

Se implementó una interfaz gráfica utilizando Java Swing, que permite utilizar las funcionalidades del sistema mediante una ventana con botones, cuadros de diálogo y un área de resultados.

La interfaz reutiliza el controlador y la lógica del modelo, sin modificar las clases originales del sistema.

## Compilación y ejecución

**Compilar:**

```bash
javac -d bin src/*.java
```

**Ejecutar:**

```bash
java -cp bin Main
```

## Diseño

El programa utiliza una clase abstracta IntegracionAPI y las interfaces Transaccional, Documentable y Auditable para representar diferentes capacidades.

SistemaIntegraciones administra los objetos mediante una única colección ArrayList<IntegracionAPI>.

El controlador coordina las operaciones entre el modelo y las vistas, permitiendo utilizar tanto la versión de consola como la interfaz gráfica.

Nota: Todas las conexiones, operaciones financieras y generaciones de documentos son simuladas. No se realizan conexiones reales con APIs externas.

El análisis de clases y el diagrama UML se encuentran en la carpeta `docs/`.