package caue.obm.CourseManager.test;

import caue.obm.CourseManager.entity.*;
import caue.obm.CourseManager.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TesteCrudRunner implements CommandLineRunner {

    private final CursoService cursoService;
    private final DisciplinaService disciplinaService;
    private final ProfessorService professorService;
    private final EspecializacaoService especializacaoService;
    private final TurmaService turmaService;
    private final EstudanteService estudanteService;

    public TesteCrudRunner(CursoService cursoService,
                           DisciplinaService disciplinaService,
                           ProfessorService professorService,
                           EspecializacaoService especializacaoService,
                           TurmaService turmaService,
                           EstudanteService estudanteService) {
        this.cursoService = cursoService;
        this.disciplinaService = disciplinaService;
        this.professorService = professorService;
        this.especializacaoService = especializacaoService;
        this.turmaService = turmaService;
        this.estudanteService = estudanteService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n========================");
        System.out.println("📚 INICIALIZAÇÃO DE DADOS NO BANCO");
        System.out.println("========================");

        // Criar Curso
        Curso curso = new Curso();
        curso.setNome("Análise e Desenvolvimento de Sistemas");
        curso = cursoService.salvar(curso);

        // Criar Disciplinas
        Disciplina d1 = new Disciplina();
        d1.setNome("Programação Orientada a Objetos");
        d1.setCurso(curso);
        d1 = disciplinaService.salvar(d1);

        Disciplina d2 = new Disciplina();
        d2.setNome("Banco de Dados");
        d2.setCurso(curso);
        d2 = disciplinaService.salvar(d2);

        // Adicionar disciplinas ao curso
        curso.setDisciplinas(Arrays.asList(d1, d2));
        curso = cursoService.atualizar(curso.getId(), curso);

        // Criar Professores
        Professor p1 = new Professor();
        p1.setNome("João Silva");
        p1 = professorService.salvar(p1);

        Professor p2 = new Professor();
        p2.setNome("Ana Costa");
        p2 = professorService.salvar(p2);

        // Criar Especializações
        Especializacao e1 = new Especializacao();
        e1.setNomeCurso("Mestrado em Computação");
        e1.setProfessor(p1);
        e1 = especializacaoService.salvar(e1);

        Especializacao e2 = new Especializacao();
        e2.setNomeCurso("Doutorado em Inteligência Artificial");
        e2.setProfessor(p2);
        e2 = especializacaoService.salvar(e2);

        // Adicionar especializações aos professores
        p1.setEspecializacoes(Arrays.asList(e1));
        p1 = professorService.atualizar(p1.getId(), p1);

        p2.setEspecializacoes(Arrays.asList(e2));
        p2 = professorService.atualizar(p2.getId(), p2);

        // Criar Estudantes
        Estudante s1 = new Estudante();
        s1.setNome("Maria Oliveira");
        s1 = estudanteService.salvar(s1);

        Estudante s2 = new Estudante();
        s2.setNome("Carlos Pereira");
        s2 = estudanteService.salvar(s2);

        // Criar Turma
        Turma turma = new Turma();
        turma.setCodigo("TURMA-101");
        turma.setDisciplina(d1);
        turma.setProfessores(Arrays.asList(p1));
        turma.setEstudantes(Arrays.asList(s1, s2));
        turma = turmaService.salvar(turma);

        // Atualizar relações many-to-many bidirecionais (se você gerencia isso no seu código)
        s1.setTurmas(Arrays.asList(turma));
        s2.setTurmas(Arrays.asList(turma));
        estudanteService.atualizar(s1.getId(), s1);
        estudanteService.atualizar(s2.getId(), s2);

        p1.setTurmas(Arrays.asList(turma));
        professorService.atualizar(p1.getId(), p1);

        // Agora imprime os dados
        System.out.println("\n🧱 Curso: " + curso.getNome());
        System.out.println("📘 Disciplinas:");
        curso.getDisciplinas().forEach(d -> System.out.println("  - " + d.getNome()));

        System.out.println("\n👨‍🏫 Professores:");
        System.out.println("  - " + p1.getNome() + " [" + e1.getNomeCurso() + "]");
        System.out.println("  - " + p2.getNome() + " [" + e2.getNomeCurso() + "]");

        System.out.println("\n👨‍🎓 Estudantes:");
        System.out.println("  - " + s1.getNome());
        System.out.println("  - " + s2.getNome());

        System.out.println("\n🏫 Turma criada:");
        System.out.println("  Código: " + turma.getCodigo());
        System.out.println("  Disciplina: " + turma.getDisciplina().getNome());
        System.out.println("  Professores:");
        turma.getProfessores().forEach(p -> System.out.println("    - " + p.getNome()));
        System.out.println("  Estudantes:");
        turma.getEstudantes().forEach(s -> System.out.println("    - " + s.getNome()));

        System.out.println("\n✅ Dados iniciais inseridos com sucesso!");
    }
}
