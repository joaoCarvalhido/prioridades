package br.com.prioridades.service;

import br.com.prioridades.DTO.UsuarioDTO;

public interface UsuarioService {
    UsuarioDTO buscarPorEmail(String email);
    UsuarioDTO salvar(UsuarioDTO usuarioDTO);
}
