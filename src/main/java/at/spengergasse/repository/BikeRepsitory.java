package at.spengergasse.repository;

import at.spengergasse.domain.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepsitory extends JpaRepository<Bike,Long> {
}
