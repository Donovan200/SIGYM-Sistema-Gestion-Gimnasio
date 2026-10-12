package sigym_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sigym_rest.model.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
}
