package sigym_rest.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sigym_rest.dto.MembresiaDto;
import sigym_rest.dto.ServicioDto;
import sigym_rest.model.Servicio;
import sigym_rest.service.MembresiaService;
import sigym_rest.dto.PagoDto;

import java.util.List;

@RestController
@RequestMapping("/api/membresias")
public class MembresiaController {

    private final MembresiaService membresiaService;

    public MembresiaController(MembresiaService membresiaService) {
        this.membresiaService = membresiaService;
    }

    @GetMapping
    public ResponseEntity<List<MembresiaDto>> getAll() {
        return ResponseEntity.ok(membresiaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembresiaDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(membresiaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MembresiaDto> create(
            @Valid @RequestBody MembresiaDto dto) {

        MembresiaDto creada = membresiaService.save(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembresiaDto> update(
            @PathVariable Long id,
            @Valid @RequestBody MembresiaDto dto) {

        return ResponseEntity.ok(
                membresiaService.update(id, dto)
        );
    }

    @GetMapping("/{id}/pagos")
    public ResponseEntity<List<PagoDto>> getPagos(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                membresiaService.findPagosByMembresia(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        membresiaService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idMembresia}/pagos")
    public ResponseEntity<PagoDto> addPago(
            @PathVariable Long idMembresia,
            @Valid @RequestBody PagoDto dto) {

        PagoDto creado = membresiaService
                .addPagoToMembresia(idMembresia, dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    @DeleteMapping("/{idMembresia}/pagos/{idPago}")
    public ResponseEntity<Void> deletePago(
            @PathVariable Long idMembresia,
            @PathVariable Long idPago) {

        membresiaService.deletePagoFromMembresia(idMembresia, idPago);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idMembresia}/servicios/{idServicio}")
    public ResponseEntity<MembresiaDto> addServicio(
            @PathVariable Long idMembresia,
            @PathVariable Long idServicio) {

        MembresiaDto actualizada =
                membresiaService.addServicioToMembresia(
                        idMembresia,
                        idServicio
                );

        return ResponseEntity.ok(actualizada);
    }

    @GetMapping("/{idMembresia}/servicios")
    public ResponseEntity<List<ServicioDto>> getServicios(
            @PathVariable Long idMembresia) {

        return ResponseEntity.ok(
                membresiaService.findServiciosByMembresia(idMembresia)
        );
    }

    @DeleteMapping("/{idMembresia}/servicios/{idServicio}")
    public ResponseEntity<Void> deleteServicio(
            @PathVariable Long idMembresia,
            @PathVariable Long idServicio) {

        membresiaService.deleteServicioFromMembresia(
                idMembresia,
                idServicio
        );

        return ResponseEntity.noContent().build();
    }
}
