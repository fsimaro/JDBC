package service;

import dto.CarreraDto;
import dto.ReporteCarreraDto;
import repository.CarreraRepository;
import repository.CarreraRepositoryImpl;
import repository.RepositoryFactory;

import java.util.List;

/**
 * Services creados unicamente para seguir una arquitectura por capas
 */
public class CarreraService {

    private final CarreraRepository carreraRepository;

    public CarreraService(String persistenceUnitName){
        this.carreraRepository = RepositoryFactory.getInstance().getRepository(CarreraRepositoryImpl.class, persistenceUnitName);
    }

    public List<CarreraDto> getCarrerasOrdenadasPorInscriptos(){
        System.out.println("Obteniendo resumen de carreras ordenadas por cantidad de inscriptos: ");
        List<CarreraDto> carreras = carreraRepository.getCarrerasOrdenadasPorInscriptos();
        for (CarreraDto carrera: carreras) System.out.println(carrera);
        return carreras;
    };

    public List<ReporteCarreraDto> getReporteCarreras() {
        System.out.println("Obteniendo reporte detallado de carreras:");
        List<ReporteCarreraDto> result = carreraRepository.getReporteCarreras();
        for (ReporteCarreraDto dto : result) {
            System.out.println(dto.getNombre());
            for (ReporteCarreraDto.DetalleCarreraAnual detalleAnual : dto.getDetalleAnual()) {
                System.out.println(detalleAnual.toString());
            }
        }
        return result;
    };

}
