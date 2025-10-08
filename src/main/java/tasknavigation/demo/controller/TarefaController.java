package tasknavigation.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tasknavigation.demo.domain.Projeto;
import tasknavigation.demo.domain.Tarefa;
import tasknavigation.demo.domain.Usuario;
import tasknavigation.demo.dto.TarefaDTO;
import tasknavigation.demo.service.TarefaService;


<<<<<<< HEAD
import java.time.LocalDate;
import java.util.Date;
=======

>>>>>>> 63182cd12d6df87febb98f402d86d8322a31da1c
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*") // Flutter web/mobile
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> listar() {
        return tarefaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaService.buscarPorId(id);
        return tarefa.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
@PostMapping
public ResponseEntity<?> criar(@RequestBody TarefaDTO dto) {

<<<<<<< HEAD
@PostMapping
public ResponseEntity<?> criar(@RequestBody TarefaDTO dto) {

    Usuario usuario = tarefaService.buscarUsuarioPorId(dto.getIdUsuario()); // usuário de teste
    if (usuario == null) {
        return ResponseEntity.badRequest().body("Usuário não encontrado.");
    }

    Tarefa tarefa = new Tarefa();
    tarefa.setTitulo(dto.getTitulo() != null ?dto.getTitulo(): "Título da Tarefa");
    tarefa.setDescricao(dto.getDescricao() != null ? dto.getDescricao(): "Descricao da Tarefa");
    tarefa.setPrazo(dto.getPrazo() != null? dto.getPrazo() : LocalDate.parse("2025-10-15"));
    tarefa.setStatus(dto.getStatus() != null ? dto.getStatus() : "Pendente");
    tarefa.setPrioridade(dto.getPrioridade() != null ? dto.getPrioridade() : "Média");
    tarefa.setUsuario(usuario);

    // projeto opcional
    if (dto.getIdProjeto() != null) {
        Projeto projeto = tarefaService.buscarProjetoPorId(dto.getIdProjeto());
        if (projeto != null) {
            tarefa.setProjeto(projeto);
        }
    }
    return ResponseEntity.ok(tarefaService.salvar(tarefa));
=======
    if (dto.getIdUsuario() == null) {
        return ResponseEntity.badRequest().body("O ID do usuário é obrigatório.");
    }

    Usuario usuario = tarefaService.buscarUsuarioPorId(dto.getIdUsuario());
    if (usuario == null) {
        return ResponseEntity.badRequest().body("Usuário não encontrado.");
    }

    Tarefa tarefa = new Tarefa();
    tarefa.setTitulo(dto.getTitulo());
    tarefa.setDescricao(dto.getDescricao());
    tarefa.setStatus(dto.getStatus() != null ? dto.getStatus() : "Pendente");
    tarefa.setPrioridade(dto.getPrioridade() != null ? dto.getPrioridade() : "Média");
    tarefa.setPrazo(dto.getPrazo());
    tarefa.setUsuario(usuario);

    if (dto.getIdProjeto() != null) {
        Projeto projeto = tarefaService.buscarProjetoPorId(dto.getIdProjeto());
        tarefa.setProjeto(projeto); // mesmo se null, tá ok
    }

    Tarefa salva = tarefaService.salvar(tarefa);
    return ResponseEntity.ok(salva);
>>>>>>> 63182cd12d6df87febb98f402d86d8322a31da1c
}







    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        if (!tarefaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tarefa.setIdTarefa(id);
        return ResponseEntity.ok(tarefaService.salvar(tarefa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!tarefaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
