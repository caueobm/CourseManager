package caue.obm.CourseManager.service;

import caue.obm.CourseManager.entity.Estudante;
import caue.obm.CourseManager.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepository estudanteRepository;

    public List<Estudante> listarTodos() {
        return estudanteRepository.findAll();
    }

    public Optional<Estudante> buscarPorId(Long id) {
        return estudanteRepository.findById(id);
    }

    public Estudante salvar(Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

    public void deletar(Long id) {
        estudanteRepository.deleteById(id);
    }

    public Estudante atualizar(Long id, Estudante novo) {
        return estudanteRepository.findById(id).map(e -> {
            e.setNome(novo.getNome());
            e.setTurmas(novo.getTurmas());
            return estudanteRepository.save(e);
        }).orElseThrow(() -> new RuntimeException("Estudante não encontrado"));
    }
}
