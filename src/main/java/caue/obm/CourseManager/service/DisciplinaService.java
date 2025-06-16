package caue.obm.CourseManager.service;

import caue.obm.CourseManager.entity.Disciplina;
import caue.obm.CourseManager.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<Disciplina> listarTodos() {
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> buscarPorId(Long id) {
        return disciplinaRepository.findById(id);
    }

    public Disciplina salvar(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public void deletar(Long id) {
        disciplinaRepository.deleteById(id);
    }

    public Disciplina atualizar(Long id, Disciplina nova) {
        return disciplinaRepository.findById(id).map(d -> {
            d.setNome(nova.getNome());
            d.setCurso(nova.getCurso());
            return disciplinaRepository.save(d);
        }).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
    }
}
