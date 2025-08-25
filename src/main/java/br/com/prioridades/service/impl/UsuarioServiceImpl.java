package br.com.prioridades.service.impl;

import br.com.prioridades.DTO.UsuarioDTO;
import br.com.prioridades.domain.Usuario;
import br.com.prioridades.domain.exception.UsuarioNaoEncontradoException;
import br.com.prioridades.repository.UsuarioRepository;
import br.com.prioridades.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO buscarPorEmail(String email) {
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(email);
        if(usuario.isPresent())
            return new UsuarioDTO(usuario.get());
        throw new UsuarioNaoEncontradoException(email);
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

    @Override
    public UsuarioDTO buscarUsuarioLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails)
            email = ((UserDetails) principal).getUsername();
         else
            email = principal.toString();
         return this.buscarPorEmail(email);
    }

    @Override
    public void autenticaUsuario(UsuarioDTO usuarioDTO) {
        UserDetails userDetails = this.loadUserByUsername(usuarioDTO.getEmail());
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, usuarioDTO.getSenha(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioDTO usuario = this.buscarPorEmail(username);
        return new User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
    }


}
