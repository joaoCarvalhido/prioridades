package br.com.prioridades.config.initializer;

import br.com.prioridades.domain.Prioridade;
import br.com.prioridades.domain.Usuario;
import br.com.prioridades.repository.PrioridadeRepository;
import br.com.prioridades.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExemploInitializer implements CommandLineRunner {
    private static final List<Long> PRIORIDADE_LIST = List.of(1L,2L,3L,4L,5L,6L,7L,8L,9L);

    private final UsuarioRepository usuarioRepository;

    private final PrioridadeRepository prioridadeRepository;

    @Override
    public void run(String... args) throws Exception {
        Usuario usuarioExemplo = verificarECriarUsuarioExemplo();
        verificarECriarPrioridadeExemplo(usuarioExemplo);
    }

    private Usuario verificarECriarUsuarioExemplo() {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail("exemplo@prioridades.com");

        if (usuarioExistente.isPresent()) {
            return usuarioExistente.get();
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail("exemplo@prioridades.com");
        novoUsuario.setSenha("$2a$10$exemplo");

        return usuarioRepository.save(novoUsuario);
    }

    private void verificarECriarPrioridadeExemplo(Usuario usuario) {
         List<Prioridade> prioridadeList = prioridadeRepository.findByUsuario(usuario);

        if (!prioridadeList.isEmpty()) return;

        PRIORIDADE_LIST.forEach(this::criarPrioridadeExemplo);

    }

    private void criarPrioridadeExemplo(Long idPrioridade) {
        Prioridade novaPrioridade = new Prioridade();
        novaPrioridade.setAtivo(true);
        novaPrioridade.setUsuario(verificarECriarUsuarioExemplo());
        novaPrioridade.setOrdem(idPrioridade.intValue());

        if (idPrioridade == 1) {
            novaPrioridade.setNome("Reserva de Emergência");
            novaPrioridade.setValorObjetivo(new BigDecimal(15_000));
            novaPrioridade.setValorInvestido(new BigDecimal(10_000));
            novaPrioridade.setIcone("money");
        } else if (idPrioridade == 2) {
            novaPrioridade.setNome("Viagem Familia");
            novaPrioridade.setValorObjetivo(new BigDecimal(10_000));
            novaPrioridade.setValorInvestido(new BigDecimal(2_000));
            novaPrioridade.setIcone("home");
        } else if (idPrioridade == 3) {
                novaPrioridade.setNome("Carro Novo");
                novaPrioridade.setValorObjetivo(new BigDecimal(60_000));
                novaPrioridade.setValorInvestido(new BigDecimal(200));
                novaPrioridade.setIcone("money");
        } else if (idPrioridade == 4) {
                novaPrioridade.setNome("Reformar Casa");
                novaPrioridade.setValorObjetivo(new BigDecimal(5_000));
                novaPrioridade.setValorInvestido(new BigDecimal(0));
                novaPrioridade.setIcone("home");
        } else if (idPrioridade == 5) {
                novaPrioridade.setNome("Trocar de TV");
                novaPrioridade.setValorObjetivo(new BigDecimal(5_000));
                novaPrioridade.setValorInvestido(new BigDecimal(0));
                novaPrioridade.setIcone("person");
        } else if (idPrioridade == 6) {
                novaPrioridade.setNome("Central Multimedia");
                novaPrioridade.setValorObjetivo(new BigDecimal(2_000));
                novaPrioridade.setValorInvestido(new BigDecimal(0));
                novaPrioridade.setIcone("money");
        } else {
            throw new RuntimeException("Nao foi possivel criar prioridade de exemplo");
        }

        this.prioridadeRepository.save(novaPrioridade);
    }
}