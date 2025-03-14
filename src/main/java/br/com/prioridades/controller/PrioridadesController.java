package br.com.prioridades.controller;

import br.com.prioridades.DTO.CadastroPrioridadeDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/prioridades")
public class PrioridadesController {

    @GetMapping("/home")
    public ModelAndView home(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails.getUsername());
        ModelAndView mv = new ModelAndView("prioridades/home");
        mv.addObject("cadastroPrioridadeDTO", new CadastroPrioridadeDTO());
        return mv;
    }

    @PostMapping
    public ModelAndView cadastrar(@ModelAttribute CadastroPrioridadeDTO cadastroPrioridadeDTO) {
        System.out.println(cadastroPrioridadeDTO);
        return new ModelAndView("redirect:/prioridades/home");
    }

    @DeleteMapping("/{idPrioridade}")
    public ModelAndView deletar(@PathVariable long idPrioridade) {
        return new ModelAndView("redirect:/prioridades/home");
    }
}
