# 🏢 Sistema de Gestión de Recursos Humanos

Sistema web para la administración de recursos humanos desarrollado con Jakarta EE 11, Java 17 y MySQL. Permite gestionar empleados, departamentos, cargos y contrataciones de una organización.

## 📋 Tabla de Contenidos

- [Características](#características)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Requisitos Previos](#requisitos-previos)
- [Instalación y Configuración](#instalación-y-configuración)
  - [1. Configuración de la Base de Datos](#1-configuración-de-la-base-de-datos)
  - [2. Configuración del Proyecto](#2-configuración-del-proyecto)
  - [3. Compilación y Despliegue](#3-compilación-y-despliegue)
- [Estructura de la Base de Datos](#estructura-de-la-base-de-datos)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Uso](#uso)

## 🌟 Características

- ✅ Gestión de empleados (registro, consulta, actualización)
- ✅ Administración de departamentos
- ✅ Control de cargos y posiciones
- ✅ Gestión de tipos de contratación
- ✅ Registro de contrataciones con información completa
- ✅ Interfaz web responsiva con JSP
- ✅ Arquitectura MVC (Modelo-Vista-Controlador)

## 🛠 Tecnologías Utilizadas

- **Backend:**
  - Java 17
  - Jakarta EE 11.0.0-M1
  - Servlets
  - JDBC para acceso a datos
  
- **Base de Datos:**
  - MySQL 8.x
  - MySQL Connector/J 8.3.0
  
- **Frontend:**
  - JSP (JavaServer Pages)
  - HTML5 / CSS3
  
- **Herramientas:**
  - Maven 3.x
  - Apache Tomcat 10.x / GlassFish 7.x (o cualquier servidor compatible con Jakarta EE 11)

## 📦 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

1. **JDK 17 o superior**
   ```bash
   java -version
   ```

2. **Apache Maven 3.6+**
   ```bash
   mvn -version
   ```

3. **MySQL Server 8.x**
   - Puede ser instalación local o XAMPP/WAMP

4. **Servidor de Aplicaciones**
   - Apache Tomcat 10.x o superior
   - GlassFish 7.x
   - Payara 6.x
   - O cualquier servidor compatible con Jakarta EE 11

## 🚀 Instalación y Configuración

### 1. Configuración de la Base de Datos

#### Paso 1: Iniciar MySQL

Si usas **XAMPP**:
- Abre el Panel de Control de XAMPP
- Inicia el servicio **MySQL**

Si usas **MySQL directo**:
```bash
# Windows
net start MySQL80

# Linux/Mac
sudo systemctl start mysql
```

#### Paso 2: Crear la Base de Datos

Ejecuta el siguiente script SQL para crear la base de datos y sus tablas:

```sql
-- 1. Creación de la base de datos
CREATE DATABASE IF NOT EXISTS db_recursos_humanos 
CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE db_recursos_humanos;

-- TABLAS MAESTRAS (Sin dependencias)

CREATE TABLE departamento (
    id_departamento INT AUTO_INCREMENT PRIMARY KEY,
    nombre_departamento VARCHAR(100) NOT NULL,
    descripcion_departamento TEXT
) ENGINE=InnoDB;

CREATE TABLE cargo (
    id_cargo INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cargo VARCHAR(100) NOT NULL,
    descripcion_cargo TEXT,
    jefatura TINYINT(1) DEFAULT 0
) ENGINE=InnoDB;

CREATE TABLE tipo_contratacion (
    id_tipo_contratacion INT AUTO_INCREMENT PRIMARY KEY,
    tipo_contratacion VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

-- 3. TABLA DE ENTIDADES

CREATE TABLE empleado (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    dui_empleado VARCHAR(9) NOT NULL UNIQUE,
    nombre_empleado VARCHAR(100) NOT NULL,
    usuario_empleado VARCHAR(50) NOT NULL UNIQUE,
    telefono_empleado VARCHAR(20),
    correo_empleado VARCHAR(100) UNIQUE,
    fecha_nacimiento_empleado DATE NOT NULL
) ENGINE=InnoDB;

-- 4. TABLA RELACIONAL (Con llaves foráneas)

CREATE TABLE contratacion (
    id_contratacion INT AUTO_INCREMENT PRIMARY KEY,
    id_departamento INT NOT NULL,
    id_empleado INT NOT NULL,
    id_cargo INT NOT NULL,
    id_tipo_contratacion INT NOT NULL,
    fecha_contratacion DATE NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    estado TINYINT(1) DEFAULT 1,

    -- Restricciones de Integridad Referencial
    CONSTRAINT fk_contratacion_depto 
        FOREIGN KEY (id_departamento) REFERENCES departamento(id_departamento)
        ON DELETE RESTRICT ON UPDATE CASCADE,
        
    CONSTRAINT fk_contratacion_empleado 
        FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado)
        ON DELETE RESTRICT ON UPDATE CASCADE,
        
    CONSTRAINT fk_contratacion_cargo 
        FOREIGN KEY (id_cargo) REFERENCES cargo(id_cargo)
        ON DELETE RESTRICT ON UPDATE CASCADE,
        
    CONSTRAINT fk_contratacion_tipo 
        FOREIGN KEY (id_tipo_contratacion) REFERENCES tipo_contratacion(id_tipo_contratacion)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;
```

**Formas de ejecutar el script:**

**Opción A: Usando MySQL Command Line**
```bash
mysql -u root -p < script.sql
```

**Opción B: Usando phpMyAdmin (XAMPP)**
1. Abre http://localhost/phpmyadmin
2. Ve a la pestaña "SQL"
3. Copia y pega el script completo
4. Haz clic en "Continuar"

**Opción C: Usando MySQL Workbench**
1. Abre MySQL Workbench
2. Conecta a tu servidor local
3. File → Open SQL Script → Selecciona el archivo
4. Ejecuta el script (⚡ icono de rayo)

### 2. Configuración del Proyecto

#### Paso 1: Clonar o Descargar el Proyecto

```bash
git clone <url-del-repositorio>
cd GestionRecursosHumanos-UDB
```

#### Paso 2: Configurar Credenciales de Base de Datos

Abre el archivo `src/main/java/conexion/Conexion.java` y verifica/modifica las credenciales:

```java
private final String URL = "jdbc:mysql://localhost:3306/db_recursos_humanos?useUnicode=true&characterEncoding=UTF-8";
private final String USER = "root";
private final String PASS = ""; // Cambia si tu MySQL tiene contraseña
```

#### Paso 3: Verificar Configuración de Maven

Asegúrate de que el archivo `pom.xml` esté correctamente configurado. El proyecto ya incluye todas las dependencias necesarias.

### 3. Compilación y Despliegue

#### Opción A: Usando Maven (Recomendado)

1. **Compilar el proyecto:**
   ```bash
   mvn clean compile
   ```

2. **Empaquetar la aplicación WAR:**
   ```bash
   mvn clean package
   ```
   
   Esto generará el archivo `target/GestionRRHH-1.0-SNAPSHOT.war`

3. **Desplegar en el servidor:**

   **Para Tomcat:**
   - Copia el archivo WAR a la carpeta `webapps` de Tomcat:
     ```bash
     copy target\GestionRRHH-1.0-SNAPSHOT.war C:\ruta\a\tomcat\webapps\
     ```
   - Inicia Tomcat:
     ```bash
     # Windows
     C:\ruta\a\tomcat\bin\startup.bat
     
     # Linux/Mac
     /ruta/a/tomcat/bin/startup.sh
     ```

   **Para GlassFish/Payara:**
   - Usa la consola de administración (generalmente http://localhost:4848)
   - Ve a "Applications" → "Deploy"
   - Selecciona el archivo WAR y despliega

4. **Acceder a la aplicación:**
   ```
   http://localhost:8080/GestionRRHH-1.0-SNAPSHOT/
   ```

#### Opción B: Usando IDE (NetBeans/Eclipse/IntelliJ)

**NetBeans:**
1. Abre el proyecto (File → Open Project)
2. Haz clic derecho en el proyecto → Properties
3. Configura el servidor en "Run" (ej: Apache Tomcat)
4. Haz clic derecho → Run (o presiona F6)

**Eclipse:**
1. Import → Existing Maven Projects
2. Selecciona la carpeta del proyecto
3. Haz clic derecho → Run As → Run on Server
4. Selecciona tu servidor configurado

**IntelliJ IDEA:**
1. Open → Selecciona la carpeta del proyecto
2. Configura un Application Server (Run → Edit Configurations)
3. Añade una configuración Tomcat/GlassFish
4. Run (Shift+F10)

## 🗄 Estructura de la Base de Datos

El sistema utiliza un modelo relacional con las siguientes tablas:

### Tablas Maestras (Catálogos)

- **`departamento`**: Almacena los departamentos de la organización
  - `id_departamento` (PK)
  - `nombre_departamento`
  - `descripcion_departamento`

- **`cargo`**: Define los diferentes cargos o posiciones
  - `id_cargo` (PK)
  - `nombre_cargo`
  - `descripcion_cargo`
  - `jefatura` (indica si es posición de liderazgo)

- **`tipo_contratacion`**: Tipos de contrato (permanente, temporal, etc.)
  - `id_tipo_contratacion` (PK)
  - `tipo_contratacion`

### Tabla de Entidades

- **`empleado`**: Información de los empleados
  - `id_empleado` (PK)
  - `dui_empleado` (UNIQUE)
  - `nombre_empleado`
  - `usuario_empleado` (UNIQUE)
  - `telefono_empleado`
  - `correo_empleado` (UNIQUE)
  - `fecha_nacimiento_empleado`

### Tabla Relacional

- **`contratacion`**: Relaciona empleados con departamentos, cargos y tipos de contrato
  - `id_contratacion` (PK)
  - `id_departamento` (FK → departamento)
  - `id_empleado` (FK → empleado)
  - `id_cargo` (FK → cargo)
  - `id_tipo_contratacion` (FK → tipo_contratacion)
  - `fecha_contratacion`
  - `salario`
  - `estado` (activo/inactivo)

### Diagrama de Relaciones

```
┌──────────────────────┐                           ┌──────────────────────┐
│      empleado        │                           │    departamento      │
├──────────────────────┤                           ├──────────────────────┤
│ id_empleado (PK)     │                           │ id_departamento (PK) │
│ dui_empleado         │                           │ nombre_departamento  │
│ nombre_empleado      │                           │ descripcion          │
│ usuario_empleado     │                           └──────────┬───────────┘
│ telefono_empleado    │                                      │
│ correo_empleado      │                                      │
│ fecha_nacimiento     │                                      │ 1
└──────────┬───────────┘                                      │
           │                                                  │
           │ 1                                                │
           │                                                  │
           │              ┌───────────────────────┐           │
           │              │    contratacion       │           │
           │              ├───────────────────────┤           │
           └─────────────►│ id_contratacion (PK)  │◄──────────┘
                       N  │ id_departamento (FK)  │ N
                          │ id_empleado (FK)      │
                          │ id_cargo (FK)         │
                          │ id_tipo_contrat. (FK) │
                          │ fecha_contratacion    │
           ┌──────────────│ salario               │──────────────┐
           │              │ estado                │              │
           │              └───────────────────────┘              │
           │                                                     │
           │ 1                                                   │ 1
           │                                                     │
┌──────────┴───────────┐                           ┌────────────┴──────────┐
│       cargo          │                           │  tipo_contratacion    │
├──────────────────────┤                           ├───────────────────────┤
│ id_cargo (PK)        │                           │ id_tipo_contrat. (PK) │
│ nombre_cargo         │                           │ tipo_contratacion     │
│ descripcion_cargo    │                           └───────────────────────┘
│ jefatura             │
└──────────────────────┘
```

**Relaciones (Cardinalidad):**
- Un **empleado** puede tener muchas **contrataciones** → 1:N
- Un **departamento** puede tener muchas **contrataciones** → 1:N
- Un **cargo** puede estar en muchas **contrataciones** → 1:N
- Un **tipo_contratacion** puede estar en muchas **contrataciones** → 1:N
- **Contratacion** es la tabla intermedia que relaciona empleados con departamentos, cargos y tipos de contrato

## 📁 Estructura del Proyecto

```
GestionRecursosHumanos-UDB/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── mycompany/gestionrrhh/
│   │   │   │   │   └── JakartaRestConfiguration.java
│   │   │   │   └── udb/modelo/
│   │   │   │       ├── Cargo.java
│   │   │   │       ├── Contratacion.java
│   │   │   │       ├── Departamento.java
│   │   │   │       ├── Empleado.java
│   │   │   │       └── TipoContratacion.java
│   │   │   ├── conexion/
│   │   │   │   └── Conexion.java              # Configuración BD
│   │   │   ├── Controlador/
│   │   │   │   ├── DepartamentoServlet.java   # Controlador Departamentos
│   │   │   │   └── EmpleadoServlet.java       # Controlador Empleados
│   │   │   └── DataAccess/
│   │   │       ├── CargoDAO.java
│   │   │       ├── ContratacionDAO.java
│   │   │       ├── DepartamentoDAO.java
│   │   │       ├── EmpleadoDAO.java
│   │   │       └── TipoContratacionDAO.java
│   │   │
│   │   ├── resources/
│   │   │   └── META-INF/
│   │   │       └── persistence.xml
│   │   │
│   │   └── webapp/
│   │       ├── index.html                     # Página principal
│   │       ├── departamentos.jsp
│   │       ├── listarEmpleados.jsp
│   │       ├── listarCargos.jsp
│   │       ├── registrarEmpleado.jsp
│   │       ├── css/
│   │       │   └── estilos.css
│   │       └── WEB-INF/
│   │           ├── web.xml                    # Configuración web
│   │           └── beans.xml
│   │
│   └── test/
│       └── java/
│
├── target/                                     # Salida de compilación
├── pom.xml                                     # Configuración Maven
├── nb-configuration.xml                        # Config NetBeans
└── README.md                                   # Este archivo
```

## 🎯 Uso

Una vez desplegada la aplicación, puedes acceder a las siguientes funcionalidades:

### Módulos Disponibles

1. **Gestión de Empleados**
   - Registrar nuevos empleados
   - Listar empleados existentes
   - Actualizar información de empleados

2. **Gestión de Departamentos**
   - Crear departamentos
   - Visualizar departamentos

3. **Gestión de Cargos**
   - Administrar cargos y posiciones
   - Definir si son cargos de jefatura

4. **Registro de Contrataciones**
   - Asociar empleados con departamentos
   - Asignar cargos
   - Definir tipo de contratación
   - Registrar salario y fecha de contratación

### Páginas Principales

- **`/index.html`** - Página de inicio
- **`/listarEmpleados.jsp`** - Ver todos los empleados
- **`/registrarEmpleado.jsp`** - Formulario de registro
- **`/departamentos.jsp`** - Gestión de departamentos
- **`/listarCargos.jsp`** - Listado de cargos

## 🔧 Solución de Problemas

### Error: "No se puede conectar a la base de datos"
- Verifica que MySQL esté ejecutándose
- Confirma las credenciales en `Conexion.java`
- Asegúrate de que la base de datos `db_recursos_humanos` existe

### Error: "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
- Verifica que el archivo `pom.xml` incluya la dependencia MySQL
- Ejecuta `mvn clean install` para descargar dependencias

### El servidor no inicia
- Verifica que el puerto 8080 no esté ocupado
- Confirma que el JDK 17 esté configurado correctamente
- Revisa los logs del servidor para más detalles

### La aplicación no se despliega
- Asegúrate de que el WAR se generó correctamente en `target/`
- Verifica que el servidor sea compatible con Jakarta EE 11
- Revisa que no haya errores de compilación

## 📝 Licencia

Este proyecto es desarrollado con fines educativos para la Universidad Don Bosco (UDB).

## 👥 Autores

Proyecto desarrollado por estudiantes de Ingeniería en Sistemas de la UDB.

## 📧 Soporte

Para preguntas o problemas, contacta a tu instructor o crea un issue en el repositorio.

---

**¡Gracias por usar el Sistema de Gestión de Recursos Humanos!** 🎉
