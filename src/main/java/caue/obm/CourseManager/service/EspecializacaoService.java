package caue.obm.CourseManager.service;

import caue.obm.CourseManager.entity.Especializacao;
import caue.obm.CourseManager.repository.EspecializacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecializacaoService {

    @Autowired
    private EspecializacaoRepository especializacaoRepository;

    public List<Especializacao> listarTodos() {
        return especializacaoRepository.findAll();
    }

    public Optional<Especializacao> buscarPorId(Long id) {
        return especializacaoRepository.findById(id);
    }

    public Especializacao salvar(Especializacao especializacao) {
        return especializacaoRepository.save(especializacao);
    }

    public void deletar(Long id) {
        especializacaoRepository.deleteById(id);
    }

    public Especializacao atualizar(Long id, Especializacao nova) {
        return especializacaoRepository.findById(id).map(e -> {
            e.setNomeCurso(nova.getNomeCurso());
            e.setProfessor(nova.getProfessor());
            return especializacaoRepository.save(e);
        }).orElseThrow(() -> new RuntimeException("Especialização não encontrada"));
    }
}
