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
