package br.com.prioridades.DTO;

import br.com.prioridades.domain.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {
    private long idUsuario;
    private String email;
    private String senha;

    public UsuarioDTO(Usuario usuario) {
        this.idUsuario = getIdUsuario();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }

    public Usuario converteParaUsuario() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(this.idUsuario);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        return usuario;
    }
}
