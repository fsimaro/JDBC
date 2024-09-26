package dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO para respresentar el reporte de inscriptos y egresados por carrera anualmente
 */
public class ReporteCarreraDto {

    public static class DetalleCarreraAnual{
        protected Integer anio;
        protected Long inscriptos;
        protected Integer egresados;

        public DetalleCarreraAnual(Integer anio, Long inscriptos, Integer egresados){
            this.anio = anio;
            this.inscriptos = inscriptos;
            this.egresados = egresados;
        }

        @Override
        public String toString(){
            return "Año: " + anio + ", inscriptos: " + inscriptos + ", egresados: " + egresados;
        }
    }

    private String nombre;
    private List<DetalleCarreraAnual> detalleAnual;

    public ReporteCarreraDto(String nombre) {
        this.nombre = nombre;
        this.detalleAnual = new ArrayList<>();
    }

    public ReporteCarreraDto(String nombre, String totalInscriptos) {
        this.nombre = nombre;
        this.detalleAnual = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<DetalleCarreraAnual> getDetalleAnual() {
        return this.detalleAnual;
    }

    @Override
    public boolean equals(Object o){
        ReporteCarreraDto reporteCarreraDto = (ReporteCarreraDto) o;
        return reporteCarreraDto.getNombre().equals(nombre);
    }

}
