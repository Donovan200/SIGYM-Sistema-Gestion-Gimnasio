package sigym_rest.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sigym_rest.dto.PagoDto;
import sigym_rest.service.PagoService;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public ResponseEntity<List<PagoDto>> getAll() {
        return ResponseEntity.ok(pagoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PagoDto> create(
            @Valid @RequestBody PagoDto dto) {

        PagoDto creado = pagoService.save(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDto> update(
            @PathVariable Long id,
            @Valid @RequestBody PagoDto dto) {

        return ResponseEntity.ok(
                pagoService.update(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        pagoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
