# Spring-Tomcat

Esta es una visión general de los pasos necesarios para crear una API RESTful con Java Spring, Hibernate, MySQL, y Tomcat desde Eclipse con código de un ejemplo básico.

Nota: este es un ejemplo simplificado y no abarca todos los detalles que podrían estar presentes en una aplicación real. Asegúrate de investigar más a fondo y seguir las mejores prácticas al desarrollar tu propia aplicación.

Pasos generales

1. Configura tu entorno de desarrollo de Eclipse para el desarrollo de Spring Boot.
2. Crea un nuevo proyecto Spring Starter Project.
3. Agrega las dependencias necesarias: Spring Web, Spring Data JPA, MySQL Driver, Hibernate.
4. Configura tu archivo application.properties para conectar a tu base de datos MySQL.
5. Crea las entidades JPA (Alumno, Nota, Asignatura, Curso).
6. Crea los repositorios JPA para manejar las operaciones de base de datos.
7. Crea los servicios para manejar la lógica empresarial.
8. Crea los controladores para manejar las solicitudes HTTP y enviar respuestas.
9. Despliega tu aplicación en Tomcat.


## Código de ejemplo

Supondré que ya has configurado tu entorno de desarrollo y creado un proyecto Spring Boot. Aquí hay fragmentos de código para las entidades, los repositorios y el controlador.

### Entidades

---

Alumno.java
```java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Alumno {
  
  @Id
  @GeneratedValue
  private Long id;
  private String nombre;
  // ... otros atributos y métodos getter/setter
}
```

---

Nota.java
```java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Nota {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  private Alumno alumno;

  @ManyToOne
  private Asignatura asignatura;

  private int nota;
  // ... otros atributos y métodos getter/setter
}
```

---

### Repositorios

AlumnoRepository.java
```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}
```

---

### Controladores

AlumnoController.java

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlumnoController {

  @Autowired
  private AlumnoService alumnoService;

  @PostMapping("/alumnos")
  public Alumno crearAlumno(@RequestBody Alumno alumno) {
    return alumnoService.save(alumno);
  }

  // ... otros métodos para GET, PUT, DELETE
}
```

---

Asegúrate de crear también los archivos para las otras entidades, repositorios y controladores. Este es solo un ejemplo básico y no cubre todas las relaciones entre las entidades. En una aplicación real, tendrías que manejar las relaciones de muchos a muchos y quizás crear tablas de unión adicionales.

--- 

## Agregar las Dependencias
Para agregar las dependencias necesarias a tu proyecto Spring Boot, debes editar tu archivo `pom.xml` e incluir las dependencias correspondientes. Aquí te muestro cómo :

```xml
<dependencies>
    <!-- Dependencia para Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Dependencia para Spring Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Dependencia para MySQL Driver -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>

    <!-- Dependencia para Hibernate (incluida en la dependencia de spring-boot-starter-data-jpa) -->
    <!-- No necesitas agregarla explícitamente, pero si quieres una versión específica de Hibernate puedes hacerlo -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.4.27.Final</version>
    </dependency>
</dependencies>
```

Después de editar tu archivo pom.xml , deberás actualizar tu proyecto para que las nuevas dependencias se descarguen e incluyan en tu proyecto.

>Nota: Las versiones específicas de las dependencias dependerán de las versiones de las bibliotecas que estés utilizando en tu proyecto. Estos son solo ejemplos y pueden no ser las últimas versiones disponibles. Te sugeriría que verifiques las últimas versiones de las dependencias en Maven Central o en los repositorios oficiales.

---

## Conexion a Mysql
Para conectar tu aplicación Spring Boot a una base de datos MySQL, debes especificar las propiedades de conexión en el archivo application.properties de tu proyecto. Aquí tienes un ejemplo de cómo podrías configurar este archivo:

```
spring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_tu_base_de_datos?useSSL=false&serverTimezone=UTC
spring.datasource.username=nombre_de_usuario
spring.datasource.password=contraseña

# Hibernate properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto=update
```

En este archivo:

`spring.datasource.url` es la URL de tu base de datos MySQL. Cambia `nombre_de_tu_base_de_datos` al nombre de tu base de datos.
`spring.datasource.username` y `spring.datasource.password` son el nombre de usuario y la contraseña para conectarse a la base de datos, respectivamente.
`spring.jpa.properties.hibernate.dialect` es la propiedad que especifica el dialecto de Hibernate que se utilizará. En este caso, se está utilizando el dialecto de MySQL.
`spring.jpa.hibernate.ddl-auto` es una propiedad de Hibernate que controla la generación automática de esquemas de base de datos. En este caso, update significa que Hibernate actualizará el esquema de la base de datos automáticamente cuando sea necesario.
Ten en cuenta que en un entorno de producción, deberías considerar otras prácticas para el manejo de credenciales de base de datos y la generación de esquemas.

---

## Creación de Entidades

Crearemos las entidades JPA para `Alumno`, `Nota`, `Asignatura`, y `Curso` utilizando la anotación `@Entity` de Hibernate. Por favor ten en cuenta que este es un ejemplo simplificado y podrías necesitar adaptarlo a tus necesidades específicas.

### Alumno.java
``` java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    // getters y setters
}
```

### Asignatura.java
``` java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    // getters y setters
}

``` 

### Curso.java
``` java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.Set;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    @ManyToMany
    private Set<Asignatura> asignaturas;

    // getters y setters
}

``` 

### Nota.java

``` java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Alumno alumno;

    @ManyToOne
    private Asignatura asignatura;

    private int nota;

    // getters y setters
}

``` 

Lo que hemos hecho en los códigos anteriores:

La anotación `@Entity` indica que la clase es una entidad JPA.
La anotación `@Id` indica el campo que será la clave primaria de la entidad.
La anotación `@GeneratedValue` indica que el proveedor de persistencia debe generar un valor para el campo.
La anotación `@ManyToOne` indica una relación de muchos a uno entre las entidades. En este caso, muchas notas pueden pertenecer a un alumno o a una asignatura.
La anotación `@ManyToMan`y indica una relación de muchos a muchos. En este caso, un curso puede tener muchas asignaturas y una asignatura puede estar en muchos cursos.

Ten en cuenta que este es un ejemplo simplificado y podrías necesitar adaptarlo a tus necesidades específicas, incluyendo el manejo de las relaciones entre las entidades.

--- 

