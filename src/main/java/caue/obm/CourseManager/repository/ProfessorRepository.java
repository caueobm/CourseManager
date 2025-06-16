package caue.obm.CourseManager.repository;

import caue.obm.CourseManager.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
