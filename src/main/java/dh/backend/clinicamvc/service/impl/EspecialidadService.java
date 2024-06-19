package dh.backend.clinicamvc.service.impl;

import dh.backend.clinicamvc.entity.Especialidad;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.repository.IEspecialidadRepository;
import dh.backend.clinicamvc.service.IEspecialidadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EspecialidadService implements IEspecialidadService {
    private IEspecialidadRepository especialidadRepository;

    public EspecialidadService(IEspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    @Override
    public Especialidad agregarEspecialidad(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    @Override
    public Optional<Especialidad> buscarUnaEspecialidad(Integer id) throws ResourceNotFoundException {
        Optional<Especialidad> especialidadOptional = especialidadRepository.findById(id);
        if(especialidadOptional.isEmpty()) throw new ResourceNotFoundException("{\"message\": \"especialidad no encontrada\"}");
        return especialidadOptional;
    }

    @Override
    public List<Especialidad> buscarTodasEspecialidades() {
        return especialidadRepository.findAll();
    }
}
