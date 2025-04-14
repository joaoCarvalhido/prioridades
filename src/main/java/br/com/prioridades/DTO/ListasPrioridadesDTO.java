package br.com.prioridades.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class ListasPrioridadesDTO {
    private List<PrioridadeDTO> topPrioridades;
    private List<PrioridadeDTO> outrasPrioridades;

    public ListasPrioridadesDTO(List<PrioridadeDTO> todasPrioridades) {
        this.topPrioridades = new ArrayList<>();
        this.outrasPrioridades = new ArrayList<>();

        todasPrioridades.forEach(prioridade -> {
            if(prioridade.getOrdem() < 4)
                this.topPrioridades.add(prioridade);
            else
                this.outrasPrioridades.add(prioridade);
        });
    }

    public boolean isTopPrioridadeCompleto() {
        return Objects.nonNull(this.topPrioridades) && topPrioridades.size() >= 3;
    }

    private PrioridadeDTO getUltimaTopPrioridade() {
        int ultimaPosicao = this.getTopPrioridades().size() - 1;
        return this.getTopPrioridades().get(ultimaPosicao);
    }

    private PrioridadeDTO getUltimaOutraPrioridade() {
        int ultimaPosicao = this.getOutrasPrioridades().size() - 1;
        return this.getOutrasPrioridades().get(ultimaPosicao);
    }

    public int getUltimaOrdemTopPrioridade() {
        if(Objects.isNull(this.topPrioridades) || this.topPrioridades.isEmpty())
            return 0;
        return this.getUltimaTopPrioridade().getOrdem();
    }

    public int getUltimaOrdemOutraPrioridade() {
        if(Objects.isNull(this.outrasPrioridades) || this.outrasPrioridades.isEmpty())
            return 3;
        return this.getUltimaOutraPrioridade().getOrdem();
    }
}
