package br.com.prioridades.service.impl;

import br.com.prioridades.DTO.ListasPrioridadesDTO;
import br.com.prioridades.DTO.PrioridadeDTO;
import br.com.prioridades.DTO.UsuarioDTO;
import br.com.prioridades.domain.Prioridade;
import br.com.prioridades.repository.PrioridadeRepository;
import br.com.prioridades.service.PrioridadeService;
import br.com.prioridades.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrioridadeServiceImpl implements PrioridadeService {

    private final PrioridadeRepository prioridadeRepository;
    private final UsuarioService usuarioService;

    @Override
    public ListasPrioridadesDTO buscarListas() {
        UsuarioDTO usuarioDTO = this.usuarioService.buscarUsuarioLogado();
        List<PrioridadeDTO> todasPrioridades = this.buscarTodas(usuarioDTO);
        return new ListasPrioridadesDTO(todasPrioridades);
    }

    @Override
    public PrioridadeDTO buscar(Long idPrioridade) {
        Optional<Prioridade> prioridadeEncontrada = this.prioridadeRepository.findById(idPrioridade);
        if(prioridadeEncontrada.isPresent())
            return new PrioridadeDTO(prioridadeEncontrada.get());
        throw  new RuntimeException();
    }

    @Override
    public void salvar(PrioridadeDTO prioridadeDTO) {
        UsuarioDTO usuarioLogado = this.usuarioService.buscarUsuarioLogado();
        prioridadeDTO.setUsuarioDTO(usuarioLogado);

        Prioridade prioridade = prioridadeDTO.converteParaPrioridade();
        this.prioridadeRepository.save(prioridade);
    }

    @Override
    public void editar(PrioridadeDTO prioridadeDTO) {
        this.salvar(prioridadeDTO);
    }

    @Override
    public void deletar(Long idPrioridade) {
        this.prioridadeRepository.deleteById(idPrioridade);
    }

    private List<PrioridadeDTO> buscarTodas(UsuarioDTO usuarioDTO) {
        List<PrioridadeDTO> prioridadesDTO = new ArrayList<>();
        List<Prioridade> prioridades = this.prioridadeRepository.findByUsuario(usuarioDTO.converteParaUsuario());
        prioridades.forEach(prioridade -> prioridadesDTO.add(new PrioridadeDTO(prioridade)));
        return prioridadesDTO;
    }
}
