package entity;

import javax.persistence.*;
import javax.swing.text.Caret;
import java.time.LocalDate;

@Entity
public class EstudianteCarrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_estudiante", nullable = false)
    private Integer idEstudiante;

    @Column(name = "id_carrera", nullable = false)
    private Integer idCarrera;

    @Column(name = "anio_inscripcion", nullable = false)
    private Integer anioInscripcion;

    @Column(name = "graduado", nullable = false)
    private boolean graduado;

    public EstudianteCarrera(){

    }

    public EstudianteCarrera(Integer id, Integer idEstudiante, Integer idCarrera, Integer anioInscripcion, boolean graduado){
        this.id = id;
        this.idEstudiante = idEstudiante;
        this.idCarrera = idCarrera;
        this.anioInscripcion = anioInscripcion;
        this.graduado = graduado;
    }

    public EstudianteCarrera(Integer idEstudiante, Integer idCarrera){
        this.idEstudiante = idEstudiante;
        this.idCarrera = idCarrera;
        this.anioInscripcion = LocalDate.now().getYear();
        this.graduado = false;
    }

    public Integer getId() {
        return id;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public void setIdCarrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
    }

    public void setAnioInscripcion(Integer anioInscripcion) {
        this.anioInscripcion = anioInscripcion;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public Integer getIdCarrera() {
        return idCarrera;
    }

    public Integer getAnioInscripcion() {
        return anioInscripcion;
    }

    public boolean isGraduado() {
        return graduado;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGraduado(boolean graduado) {
        this.graduado = graduado;
    }

    @Override
    public String toString(){
        return "EstudianteCarrera{" +
                "id=" + id +
                ", idEstudiante='" + idEstudiante + '\'' +
                ", idCarrera='" + idCarrera + '\'' +
                ", año de inscripcion='" + anioInscripcion + '\'' +
                ", graduado='" + graduado + '\'' +
                '}';
    }
}
