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
