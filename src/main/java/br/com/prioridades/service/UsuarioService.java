package br.com.prioridades.service;

import br.com.prioridades.DTO.UsuarioDTO;

public interface UsuarioService {
    UsuarioDTO buscarPorEmail(String email) throws IllegalAccessException;
    UsuarioDTO salvar(UsuarioDTO usuarioDTO);
    void validaSenha(UsuarioDTO usuarioLogado, UsuarioDTO usuarioValidado) throws IllegalAccessException;
    void criptografarSenha(UsuarioDTO usuarioDTO);
}
