package br.com.prioridades.domain.exception;

public class ListaTopPrioridadeCompleto extends Exception {

    public ListaTopPrioridadeCompleto() {
        super("Limite de 3 top prioridades atingido!");
    }
}
