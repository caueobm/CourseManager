package caue.obm.CourseManager.service;

import caue.obm.CourseManager.entity.Curso;
import caue.obm.CourseManager.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void deletar(Long id) {
        cursoRepository.deleteById(id);
    }

    public Curso atualizar(Long id, Curso cursoAtualizado) {
        return cursoRepository.findById(id).map(curso -> {
            curso.setNome(cursoAtualizado.getNome());
            return cursoRepository.save(curso);
        }).orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + id));
    }
}
