package tasknavigation.demo.dto;

import java.time.LocalDate;

public class TarefaDTO {
<<<<<<< HEAD
    private String titulo;
    private String descricao;
    private Long idProjeto;
    private String status;
    private String prioridade;
    private Long idUsuario;
    private LocalDate prazo;
=======

    private String titulo;
    private String descricao;
    private String status;
    private String prioridade;
    private LocalDate prazo;
    private Long idUsuario; // obrigatório
    private Long idProjeto; // opcional
>>>>>>> 63182cd12d6df87febb98f402d86d8322a31da1c

    // Getters e setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

<<<<<<< HEAD
    public Long getIdProjeto() { return idProjeto; }
    public void setIdProjeto(Long idProjeto) { this.idProjeto = idProjeto; }

=======
>>>>>>> 63182cd12d6df87febb98f402d86d8322a31da1c
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPrioridade() { return prioridade; }
    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }

<<<<<<< HEAD
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }
=======
    public LocalDate getPrazo() { return prazo; }
    public void setPrazo(LocalDate prazo) { this.prazo = prazo; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public Long getIdProjeto() { return idProjeto; }
    public void setIdProjeto(Long idProjeto) { this.idProjeto = idProjeto; }
>>>>>>> 63182cd12d6df87febb98f402d86d8322a31da1c
}
