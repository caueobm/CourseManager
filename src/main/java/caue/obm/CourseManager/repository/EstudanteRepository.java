package caue.obm.CourseManager.repository;

import caue.obm.CourseManager.entity.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudanteRepository extends JpaRepository<Estudante, Long> {
}
