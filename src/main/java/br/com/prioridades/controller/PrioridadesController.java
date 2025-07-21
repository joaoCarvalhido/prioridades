package br.com.prioridades.controller;

import br.com.prioridades.DTO.ListasPrioridadesDTO;
import br.com.prioridades.DTO.PrioridadeDTO;
import br.com.prioridades.domain.exception.ListaTopPrioridadeCompleto;
import br.com.prioridades.service.PrioridadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/prioridades")
@RequiredArgsConstructor
public class PrioridadesController {

    private final PrioridadeService prioridadeService;

    @GetMapping
    public ModelAndView viewPrioridades(Model model) {
        ListasPrioridadesDTO listasPrioridades = this.prioridadeService.buscarListas();

        ModelAndView mv = new ModelAndView("prioridades/prioridades");
        mv.addObject("listasPrioridades", listasPrioridades);
        mv.addObject("cadastroPrioridadeDTO", new PrioridadeDTO());

        tratamentoErro(model, mv);
        return mv;
    }

    @PostMapping
    public ModelAndView cadastrar(@ModelAttribute PrioridadeDTO prioridadeDTO, RedirectAttributes redirectAttributes) {
        try {
            this.prioridadeService.salvar(prioridadeDTO);
        } catch (ListaTopPrioridadeCompleto ex) {
            redirectAttributes.addFlashAttribute("erro", ex.getMessage());
            redirectAttributes.addFlashAttribute("tipoErro", "limite_top_prioridade");
        }
        return new ModelAndView("redirect:/prioridades");
    }

    @PostMapping("/delecao/{idPrioridade}")
    public ModelAndView deletar(@PathVariable long idPrioridade) {
        this.prioridadeService.deletar(idPrioridade);
        return new ModelAndView("redirect:/prioridades");
    }

    @PostMapping("/edicao/{idPrioridade}")
    public ModelAndView editar(@PathVariable Long idPrioridade, @ModelAttribute PrioridadeDTO prioridadeDTO) throws ListaTopPrioridadeCompleto {
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

    private void tratamentoErro(Model model, ModelAndView mv) {
        if (model.containsAttribute("erro")) {
            mv.addObject("erro", model.asMap().get("erro"));
            mv.addObject("tipoErro", model.asMap().get("tipoErro"));
        }
    }
}
