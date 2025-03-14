package br.com.prioridades.service.impl;

import br.com.prioridades.DTO.UsuarioDTO;
import br.com.prioridades.domain.Usuario;
import br.com.prioridades.repository.UsuarioRepository;
import br.com.prioridades.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO buscarPorEmail(String email) throws IllegalAccessException {
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(email);
        if(usuario.isPresent())
            return new UsuarioDTO(usuario.get());
        throw new IllegalAccessException("Usuario " + "[" + email + "]" + " não encontrado");
    }

    @Override
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        this.criptografarSenha(usuarioDTO);
        Usuario usuarioCadastrado = this.usuarioRepository.save(usuarioDTO.converteParaUsuario());
        return new UsuarioDTO(usuarioCadastrado);
    }

    @Override
    public void validaSenha(UsuarioDTO usuarioLogado, UsuarioDTO usuarioValidado) throws IllegalAccessException {
        if(!usuarioValidado.getSenha().equals(usuarioLogado.getSenha()))
            throw new IllegalAccessException("Senha incorreta");
    }

    @Override
    public void criptografarSenha(UsuarioDTO usuarioDTO) {
        String senha = usuarioDTO.getSenha();
        usuarioDTO.setSenha(new BCryptPasswordEncoder().encode(senha));
    }
}
