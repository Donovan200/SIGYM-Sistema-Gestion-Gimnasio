package sigym_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sigym_rest.model.Prospecto;

public interface ProspectoRepository extends JpaRepository<Prospecto, Long> {
}
