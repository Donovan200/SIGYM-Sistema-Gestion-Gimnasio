package sigym_rest.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sigym_rest.dto.ProspectoDto;
import sigym_rest.service.ProspectoService;

import java.util.List;

@RestController
@RequestMapping("/api/prospectos")
public class ProspectoController {

    private final ProspectoService prospectoService;

    public ProspectoController(ProspectoService prospectoService) {
        this.prospectoService = prospectoService;
    }

    @GetMapping
    public ResponseEntity<List<ProspectoDto>> getAll() {
        return ResponseEntity.ok(prospectoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProspectoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(prospectoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProspectoDto> create(
            @Valid @RequestBody ProspectoDto dto) {

        ProspectoDto creado = prospectoService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProspectoDto> update(
            @PathVariable Long id,
            @Valid @RequestBody ProspectoDto dto) {

        return ResponseEntity.ok(
                prospectoService.update(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        prospectoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
