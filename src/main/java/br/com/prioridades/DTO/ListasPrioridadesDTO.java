package br.com.prioridades.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListasPrioridadesDTO {
    private List<PrioridadeDTO> topPrioridades;
    private List<PrioridadeDTO> outrasPrioridades;

    public ListasPrioridadesDTO(List<PrioridadeDTO> todasPrioridades) {
        this.topPrioridades = new ArrayList<>();
        this.outrasPrioridades = new ArrayList<>();

        todasPrioridades.forEach(prioridade -> {
            if(this.topPrioridades.size() < 3)
                this.topPrioridades.add(prioridade);
            else
                this.outrasPrioridades.add(prioridade);
        });
    }
}
