package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "genero", nullable = false)
    private String genero;

    @Column(name = "libreta_universitaria", unique = true)
    private String libretaUniversitaria;

    @Column(name = "numero_documento", nullable = false)
    private String numeroDocumento;

    @Column(name = "ciudad_origen", nullable = false)
    private String ciudadOrigen;


    /*@ManyToMany
    private Set<Carrera> carreras;*/

    public Estudiante(){

    }

    public Estudiante(Integer id, String nombre, String apellido, LocalDate fechaNacimiento, String genero, String libretaUniversitaria, String numeroDocumento, String ciudadOrigen) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.libretaUniversitaria = libretaUniversitaria;
        this.numeroDocumento = numeroDocumento;
        this.ciudadOrigen = ciudadOrigen;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public String getLibretaUniversitaria() {
        return libretaUniversitaria;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setLibretaUniversitaria(String libretaUniversitaria) {
        this.libretaUniversitaria = libretaUniversitaria;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    @Override
    public String toString(){
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento='" + fechaNacimiento.toString() + '\'' +
                ", genero='" + genero + '\'' +
                ", libretaUniversitaria='" + libretaUniversitaria + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", ciudadOrigen='" + ciudadOrigen + '\'' +
                '}';
    }
}
