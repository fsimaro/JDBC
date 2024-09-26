package dto;

/**
 * DTO para representar una carrera con su total de inscriptos
 */
public class CarreraDto {

    private String nombre;
    private Integer totalInscriptos;

    public CarreraDto(String nombre, Integer totalInscriptos) {
        this.nombre = nombre;
        this.totalInscriptos = totalInscriptos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTotalInscriptos() {
        return totalInscriptos;
    }

    public void setTotalInscriptos(Integer totalInscriptos) {
        this.totalInscriptos = totalInscriptos;
    }

    @Override
    public String toString(){
        return "Carrera: " + nombre + ", totalInscriptos: " + totalInscriptos;
    }
}