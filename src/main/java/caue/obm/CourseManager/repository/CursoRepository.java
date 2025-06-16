package caue.obm.CourseManager.repository;

import caue.obm.CourseManager.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
