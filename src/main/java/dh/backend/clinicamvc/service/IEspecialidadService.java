package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.entity.Especialidad;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IEspecialidadService {
    Especialidad agregarEspecialidad(Especialidad especialidad);

    Optional<Especialidad> buscarUnaEspecialidad(Integer id) throws ResourceNotFoundException;
    List<Especialidad> buscarTodasEspecialidades();

}
