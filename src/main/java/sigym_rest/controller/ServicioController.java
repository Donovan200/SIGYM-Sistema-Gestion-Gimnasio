package sigym_rest.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sigym_rest.dto.ServicioDto;
import sigym_rest.service.ServicioService;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    public ResponseEntity<List<ServicioDto>> getAll() {
        return ResponseEntity.ok(servicioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(servicioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ServicioDto> create(
            @Valid @RequestBody ServicioDto dto) {

        ServicioDto creado = servicioService.save(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioDto> update(
            @PathVariable Long id,
            @Valid @RequestBody ServicioDto dto) {

        return ResponseEntity.ok(
                servicioService.update(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        servicioService.delete(id);

        return ResponseEntity.noContent().build();
    }
}