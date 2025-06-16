package caue.obm.CourseManager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "professores")
    private List<Turma> turmas = new ArrayList<>();

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Especializacao> especializacoes = new ArrayList<>();
}
