package br.com.prioridades.controller;

import br.com.prioridades.DTO.CadastroPrioridadeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/prioridades")
public class PrioridadesController {

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("prioridades/home");
        mv.addObject("cadastroPrioridadeDTO", new CadastroPrioridadeDTO());
        return mv;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(@ModelAttribute CadastroPrioridadeDTO cadastroPrioridadeDTO) {
        System.out.println(cadastroPrioridadeDTO);
        return new ModelAndView("redirect:/prioridades/home");
    }
}
