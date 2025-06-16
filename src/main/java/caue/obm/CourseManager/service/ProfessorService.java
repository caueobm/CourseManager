package caue.obm.CourseManager.service;

import caue.obm.CourseManager.entity.Professor;
import caue.obm.CourseManager.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarPorId(Long id) {
        return professorRepository.findById(id);
    }

    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    }

    public void deletar(Long id) {
        professorRepository.deleteById(id);
    }

    public Professor atualizar(Long id, Professor novo) {
        return professorRepository.findById(id).map(p -> {
            p.setNome(novo.getNome());
            p.setTurmas(novo.getTurmas());
            p.setEspecializacoes(novo.getEspecializacoes());
            return professorRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }
}
