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
