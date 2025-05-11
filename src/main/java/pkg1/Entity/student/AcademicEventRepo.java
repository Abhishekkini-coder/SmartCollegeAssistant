package pkg1.Entity.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicEventRepo extends JpaRepository<AcademicEvent, Long> {
    List<AcademicEvent> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
