package br.com.prioridades.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CadastroPrioridadeDTO {
    private String nome;
    private BigDecimal valorObjetivo;
    private BigDecimal valorInvestido;
    private String idIcone;
}
