package br.com.prioridades.service;

import br.com.prioridades.DTO.ListasPrioridadesDTO;
import br.com.prioridades.DTO.PrioridadeDTO;

public interface PrioridadeService {
    ListasPrioridadesDTO buscarListas();
    PrioridadeDTO buscar(Long idPrioridade);
    PrioridadeDTO salvar(PrioridadeDTO prioridadeDTO);
}
