package caue.obm.CourseManager.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Especializacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCurso;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
}
