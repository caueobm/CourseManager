package caue.obm.CourseManager.controller;

import caue.obm.CourseManager.entity.Especializacao;
import caue.obm.CourseManager.service.EspecializacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especializacoes")
public class EspecializacaoController {

    @Autowired
    private EspecializacaoService especializacaoService;

    @GetMapping
    public List<Especializacao> listar() {
        return especializacaoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especializacao> buscar(@PathVariable Long id) {
        return especializacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Especializacao criar(@RequestBody Especializacao e) {
        return especializacaoService.salvar(e);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especializacao> atualizar(@PathVariable Long id, @RequestBody Especializacao e) {
        try {
            return ResponseEntity.ok(especializacaoService.atualizar(id, e));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        especializacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
