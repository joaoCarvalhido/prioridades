package br.com.prioridades.service.impl;

import br.com.prioridades.DTO.UsuarioDTO;
import br.com.prioridades.domain.Usuario;
import br.com.prioridades.repository.UsuarioRepository;
import br.com.prioridades.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO buscarPorEmail(String email) {
        Usuario usuario = this.usuarioRepository.findByEmail(email);
        return new UsuarioDTO(usuario);
    }

    @Override
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        Usuario usuarioCadastrado = this.usuarioRepository.save(usuarioDTO.converteParaUsuario());
        return new UsuarioDTO(usuarioCadastrado);
    }
}
