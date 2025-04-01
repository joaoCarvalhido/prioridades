package br.com.prioridades.controller;

import br.com.prioridades.DTO.ListasPrioridadesDTO;
import br.com.prioridades.DTO.PrioridadeDTO;
import br.com.prioridades.service.PrioridadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequestMapping("/prioridades")
@RequiredArgsConstructor
public class PrioridadesController {

    private final PrioridadeService prioridadeService;

    @GetMapping
    public ModelAndView viewPrioridades() {
        ListasPrioridadesDTO listasPrioridades = this.prioridadeService.buscarListas();

        ModelAndView mv = new ModelAndView("prioridades/prioridades");
        mv.addObject("listasPrioridades", listasPrioridades);
        mv.addObject("cadastroPrioridadeDTO", new PrioridadeDTO());
        return mv;
    }

    @PostMapping
    public ModelAndView cadastrar(@ModelAttribute PrioridadeDTO prioridadeDTO) {
        ModelAndView mv = new ModelAndView("prioridades");
        this.prioridadeService.salvar(prioridadeDTO);
        return new ModelAndView("redirect:/prioridades");
    }

    @DeleteMapping("/{idPrioridade}")
    public ModelAndView deletar(@PathVariable long idPrioridade) {
        this.prioridadeService.deletar(idPrioridade);
        return new ModelAndView("redirect:/prioridades");
    }

    @PostMapping("/edicao/{idPrioridade}")
    public ModelAndView editar(@PathVariable Long idPrioridade, @ModelAttribute PrioridadeDTO prioridadeDTO) {
        prioridadeDTO.setIdPrioridade(idPrioridade);
        this.prioridadeService.editar(prioridadeDTO);
        return new ModelAndView("redirect:/prioridades");
    }

    @GetMapping("/exemplo")
    public ModelAndView viewHome() {
        ModelAndView mv = new ModelAndView("prioridades/exemplo");
        mv.addObject("cadastroPrioridadeDTO", new PrioridadeDTO());
        return mv;
    }
}
