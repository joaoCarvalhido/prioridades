package br.com.prioridades.DTO;

import br.com.prioridades.domain.Prioridade;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
public class PrioridadeDTO {
    private Long idPrioridade;
    private String nome;
    private BigDecimal valorObjetivo;
    private BigDecimal valorInvestido;
    private String icone;
    private int ordem;
    private UsuarioDTO usuarioDTO;
    private boolean topPrioridade;

    public PrioridadeDTO(Prioridade prioridade) {
        this.idPrioridade = prioridade.getIdPrioridade();
        this.nome = prioridade.getNome();
        this.valorObjetivo = prioridade.getValorObjetivo();
        this.valorInvestido = prioridade.getValorInvestido();
        this.icone = prioridade.getIcone();
        this.ordem = prioridade.getOrdem();
        this.usuarioDTO = new UsuarioDTO(prioridade.getUsuario());
    }

    public Prioridade converteParaPrioridade() {
        Prioridade prioridade = new Prioridade();
        prioridade.setIdPrioridade(this.idPrioridade);
        prioridade.setNome(this.nome);
        prioridade.setValorObjetivo(this.valorObjetivo);
        prioridade.setValorInvestido(this.valorInvestido);
        prioridade.setIcone(this.icone);
        prioridade.setOrdem(this.ordem);
        if(Objects.nonNull(this.usuarioDTO))
            prioridade.setUsuario(this.usuarioDTO.converteParaUsuario());
        return prioridade;
    }

}
