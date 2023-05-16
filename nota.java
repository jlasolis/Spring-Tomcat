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
  // ... otros atributos y métodos getter/setter
}

