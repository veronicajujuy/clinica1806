package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.entity.Especialidad;
import dh.backend.clinicamvc.service.IEspecialidadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidad")
public class EspecialidadController {
    private IEspecialidadService especialidadService;

    public EspecialidadController(IEspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @PostMapping
    public ResponseEntity<Especialidad> agregarUnaEspecialidad (@RequestBody Especialidad especialidad){
        return ResponseEntity.ok(especialidadService.agregarEspecialidad(especialidad));
    }

    @GetMapping
    public ResponseEntity<List<Especialidad>> buscarEspecialidades(){
        return ResponseEntity.ok(especialidadService.buscarTodasEspecialidades());
    }
}
