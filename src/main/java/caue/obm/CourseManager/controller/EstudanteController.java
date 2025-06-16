package caue.obm.CourseManager.controller;

import caue.obm.CourseManager.entity.Estudante;
import caue.obm.CourseManager.service.EstudanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteService estudanteService;

    @GetMapping
    public List<Estudante> listar() {
        return estudanteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudante> buscar(@PathVariable Long id) {
        return estudanteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estudante criar(@RequestBody Estudante estudante) {
        return estudanteService.salvar(estudante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudante> atualizar(@PathVariable Long id, @RequestBody Estudante estudante) {
        try {
            return ResponseEntity.ok(estudanteService.atualizar(id, estudante));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        estudanteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
