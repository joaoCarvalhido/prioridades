package br.com.prioridades.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_prioridade")
@Data
public class Prioridade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prioridade")
    private Long idPrioridade;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column
    private BigDecimal valorObjetivo;

    @Column
    private BigDecimal valorInvestido;

    @Column(nullable = false, length = 100)
    private String icone;

    @Column
    private boolean ativo = true;

    @Column
    private int ordem;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
