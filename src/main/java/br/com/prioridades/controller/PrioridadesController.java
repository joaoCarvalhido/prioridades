package br.com.prioridades.controller;

import br.com.prioridades.DTO.ListasPrioridadesDTO;
import br.com.prioridades.DTO.PrioridadeDTO;
import br.com.prioridades.service.PrioridadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        mv.addObject("prioridadeDTO", new PrioridadeDTO());
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
        return new ModelAndView("redirect:/prioridades");
    }

    @GetMapping("/{idPrioridade}")
    public ModelAndView viewEditar(@PathVariable Long idPrioridade, @ModelAttribute PrioridadeDTO prioridadeDTO) {
        ModelAndView mv = new ModelAndView("prioridades/home");
        mv.addObject("cadastroPrioridadeDTO", new PrioridadeDTO());
        return mv;
    }

    @PutMapping("/{idPrioridade}")
    public ModelAndView editar(@ModelAttribute PrioridadeDTO prioridadeDTO) {
        //this.prioridadeService.editar(prioridadeDTO);
        return new ModelAndView("redirect:/prioridades/home");
    }

    @GetMapping("/exemplo")
    public ModelAndView viewHome() {
        ModelAndView mv = new ModelAndView("prioridades/exemplo");
        mv.addObject("cadastroPrioridadeDTO", new PrioridadeDTO());
        return mv;
    }
}
