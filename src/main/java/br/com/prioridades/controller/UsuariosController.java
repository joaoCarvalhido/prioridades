package br.com.prioridades.controller;

import br.com.prioridades.DTO.UsuarioDTO;
import br.com.prioridades.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuariosController {

    private final UsuarioService usuarioService;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("usuarios/login");
        mv.addObject("usuarioDTO", new UsuarioDTO());
        return mv;
    }

    /*
    @PostMapping("/processa-login")
    public ModelAndView processaLogin(@ModelAttribute UsuarioDTO usuarioDTO) throws IllegalAccessException {
        UsuarioDTO usuarioLogado = this.usuarioService.buscarPorEmail(usuarioDTO.getEmail());
        this.usuarioService.validaSenha(usuarioLogado, usuarioDTO);
        return new ModelAndView("redirect:/prioridades/home");
    }
    */

    @GetMapping("/cadastro")
    public ModelAndView cadastro() {
        ModelAndView mv = new ModelAndView("usuarios/cadastro");
        mv.addObject("usuarioDTO", new UsuarioDTO());
        return mv;
    }

    @PostMapping("/cadastro")
    public ModelAndView salvarUsuario(@ModelAttribute UsuarioDTO usuarioDTO) {
        this.usuarioService.salvar(usuarioDTO);
        return new ModelAndView("redirect:/usuarios/login");
    }
}
