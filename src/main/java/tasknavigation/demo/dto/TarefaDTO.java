package tasknavigation.demo.dto;

import java.time.LocalDate;

public class TarefaDTO {

    private String titulo;
    private String descricao;
    private String status;
    private String prioridade;
    private LocalDate prazo;
    private Long idUsuario; // obrigatório
    private Long idProjeto; // opcional

    // Getters e setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPrioridade() { return prioridade; }
    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }

    public LocalDate getPrazo() { return prazo; }
    public void setPrazo(LocalDate prazo) { this.prazo = prazo; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public Long getIdProjeto() { return idProjeto; }
    public void setIdProjeto(Long idProjeto) { this.idProjeto = idProjeto; }
}
