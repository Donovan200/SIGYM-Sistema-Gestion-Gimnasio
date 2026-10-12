package sigym_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sigym_rest.model.Membresia;

public interface MembresiaRepository extends JpaRepository<Membresia, Long> {
}
