package br.com.prioridades.service;

import br.com.prioridades.DTO.UsuarioDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {
    UsuarioDTO buscarPorEmail(String email) throws IllegalAccessException;
    UsuarioDTO salvar(UsuarioDTO usuarioDTO);
    void validaSenha(UsuarioDTO usuarioLogado, UsuarioDTO usuarioValidado) throws IllegalAccessException;
    void criptografarSenha(UsuarioDTO usuarioDTO);
    UsuarioDTO buscarUsuarioLogado();
}
