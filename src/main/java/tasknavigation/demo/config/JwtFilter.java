package tasknavigation.demo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tasknavigation.demo.domain.Usuario;
import tasknavigation.demo.service.UsuarioService;
import tasknavigation.demo.util.JwtUtil;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final UsuarioService usuarioService;

    public JwtFilter(@org.springframework.context.annotation.Lazy UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        System.out.println("\n🔍 Iniciando filtro JWT para: " + request.getServletPath());

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                String email = JwtUtil.validateToken(token);
                if (email == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou expirado");
                    return;
                }

                Usuario usuario = usuarioService.buscarPorEmail(email).orElse(null);
                if (usuario == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não encontrado");
                    return;
                }

                // Padroniza role e adiciona ROLE_
                String nivel = usuario.getNivelAcesso();
                if (nivel == null || nivel.isBlank()) {
                    nivel = "USUARIO";
                } else {
                    nivel = nivel.trim().toUpperCase();
                    if (!nivel.equals("USUARIO") && !nivel.equals("ADMIN")) nivel = "USUARIO";
                }
                String role = "ROLE_" + nivel;

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                usuario,
                                null,
                                List.of(new SimpleGrantedAuthority(role))
                        );
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("✅ Autenticação configurada com sucesso! Role: " + role);

            } catch (Exception e) {
                System.err.println("🔥 Erro durante autenticação JWT: " + e.getMessage());
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Falha na autenticação");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        String method = request.getMethod();

        // Liberar login, registro, OPTIONS e GET /tarefas
        return path.equals("/usuarios/login")
                || path.equals("/usuarios/registro")
                || "OPTIONS".equalsIgnoreCase(method)
                || (path.equals("/tarefas") && "GET".equalsIgnoreCase(method));
    }
}
