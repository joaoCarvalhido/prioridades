package br.com.prioridades.domain.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String email) {
        super("Usuario " + "[" + email + "]" + " não encontrado");
    }
}
