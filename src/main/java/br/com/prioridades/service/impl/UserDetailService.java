package br.com.prioridades.service.impl;

import br.com.prioridades.DTO.UsuarioDTO;
import br.com.prioridades.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    @Autowired
    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioDTO usuario = null;
        try {
            usuario = this.usuarioService.buscarPorEmail(username);
        } catch (IllegalAccessException e) {
            throw new UsernameNotFoundException("Falha ao buscar usuario", e);
        }
        return new User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
    }
}
