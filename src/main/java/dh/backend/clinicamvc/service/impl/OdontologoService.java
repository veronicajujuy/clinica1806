package dh.backend.clinicamvc.service.impl;


import dh.backend.clinicamvc.entity.Especialidad;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.repository.IEspecialidadRepository;
import dh.backend.clinicamvc.repository.IOdontologoRepository;
import dh.backend.clinicamvc.service.IEspecialidadService;
import dh.backend.clinicamvc.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private IOdontologoRepository odontologoRepository;
    private IEspecialidadService especialidadService;

    public OdontologoService(IOdontologoRepository odontologoRepository, IEspecialidadService especialidadService) {
        this.odontologoRepository = odontologoRepository;
        this.especialidadService = especialidadService;
    }

    public Odontologo agregarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo> buscarUnOdontologo(Integer id){
        return odontologoRepository.findById(id);
    }
    public List<Odontologo> buscarTodosOdontologos(){
        return odontologoRepository.findAll();
    }

    @Override
    public void modificarOdontologo(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminarOdontologo(Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoOptional = buscarUnOdontologo(id);
        if(odontologoOptional.isPresent())
            odontologoRepository.deleteById(id);
        else throw new ResourceNotFoundException("{\"message\": \"odontologo no encontrado\"}");
    }

    @Override
    public List<Odontologo> buscarPorApellido(String apellido) {
        return odontologoRepository.buscarPorApellido(apellido);
    }

    @Override
    public List<Odontologo> buscarPorNombre(String nombre) {
        return odontologoRepository.findByNombreLike(nombre);
    }

    @Override
    public Odontologo agregarEspecialidad(Integer id_odontologo, Integer id_especialidad) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoOptional = buscarUnOdontologo(id_odontologo);
        if(odontologoOptional.isEmpty()) throw new ResourceNotFoundException("{\"message\": \"odontologo no encontrado\"}");
        Optional<Especialidad> especialidadOptional = especialidadService.buscarUnaEspecialidad(id_especialidad);
        if(especialidadOptional.isEmpty()) throw new ResourceNotFoundException("{\"message\": \"especialidad no encontrada\"}");

        Odontologo odontologo = odontologoOptional.get();
        odontologo.getEspecialidades().add(especialidadOptional.get());
        modificarOdontologo(odontologo);

        return odontologo;
    }


}
