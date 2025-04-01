package br.com.prioridades.service;

import br.com.prioridades.DTO.ListasPrioridadesDTO;
import br.com.prioridades.DTO.PrioridadeDTO;

public interface PrioridadeService {
    ListasPrioridadesDTO buscarListas();
    PrioridadeDTO buscar(Long idPrioridade);
    void salvar(PrioridadeDTO prioridadeDTO);
    void editar(PrioridadeDTO prioridadeDTO);
    void deletar(Long idPrioridade);
}
