// src/main/java/tasknavigation/demo/dto/RedefinirSenhaRequest.java
package tasknavigation.demo.dto;

public class RedefinirSenhaRequest {
    private String email;
    private String codigo;
    private String novaSenha;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNovaSenha() { return novaSenha; }
    public void setNovaSenha(String novaSenha) { this.novaSenha = novaSenha; }
}
