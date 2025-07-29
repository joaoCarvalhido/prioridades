package br.com.prioridades.service;

import br.com.prioridades.DTO.ListasPrioridadesDTO;
import br.com.prioridades.DTO.PrioridadeDTO;
import br.com.prioridades.domain.exception.ListaTopPrioridadeCompleto;

import java.math.BigDecimal;
import java.util.List;

public interface PrioridadeService {
    ListasPrioridadesDTO buscarListas();
    ListasPrioridadesDTO buscarListasUsuarioExemplo();

    PrioridadeDTO buscar(Long idPrioridade);

    void salvar(PrioridadeDTO prioridadeDTO) throws ListaTopPrioridadeCompleto;
    void editar(PrioridadeDTO prioridadeDTO) throws ListaTopPrioridadeCompleto;
    void deletar(Long idPrioridade);
    BigDecimal calcularValorPendente(List<PrioridadeDTO> topPrioridades);
}
