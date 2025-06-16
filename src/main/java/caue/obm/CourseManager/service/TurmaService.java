package caue.obm.CourseManager.service;

import caue.obm.CourseManager.entity.Turma;
import caue.obm.CourseManager.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public List<Turma> listarTodos() {
        return turmaRepository.findAll();
    }

    public Optional<Turma> buscarPorId(Long id) {
        return turmaRepository.findById(id);
    }

    public Turma salvar(Turma turma) {
        return turmaRepository.save(turma);
    }

    public void deletar(Long id) {
        turmaRepository.deleteById(id);
    }

    public Turma atualizar(Long id, Turma nova) {
        return turmaRepository.findById(id).map(t -> {
            t.setCodigo(nova.getCodigo());
            t.setDisciplina(nova.getDisciplina());
            t.setEstudantes(nova.getEstudantes());
            t.setProfessores(nova.getProfessores());
            return turmaRepository.save(t);
        }).orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    }
}
