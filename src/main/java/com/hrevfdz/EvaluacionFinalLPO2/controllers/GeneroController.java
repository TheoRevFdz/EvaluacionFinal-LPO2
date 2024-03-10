package com.hrevfdz.EvaluacionFinalLPO2.controllers;

import com.hrevfdz.EvaluacionFinalLPO2.enums.GeneroEnum;
import com.hrevfdz.EvaluacionFinalLPO2.models.Genero;
import com.hrevfdz.EvaluacionFinalLPO2.services.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/generos")
public class GeneroController {
    @Autowired
    private IGeneroService service;

    @GetMapping
    public String findAllGeneros(Model model) {
        List<Genero> generos = service.findAll();
        model.addAttribute(GeneroEnum.GENEROS.nameVar, generos);
        model.addAttribute(GeneroEnum.GENEROS.nameVar.concat("Title"), GeneroEnum.GENEROS_TITLE.nameVar);
        final String pagePath = GeneroEnum.GENEROS.nameVar.concat("/").concat(GeneroEnum.GENEROS_LIST.nameVar);
        return pagePath;
    }

    @GetMapping("/register")
    public String showGeneroRegister(Model model) {
        model.addAttribute("genero", Genero.builder().build());
        final String pagePath = GeneroEnum.GENEROS.nameVar.concat("/").concat(GeneroEnum.GENEROS_REGISTER.nameVar);
        return pagePath;
    }

    @PostMapping("/register")
    public String createPelicula(@ModelAttribute(name = "genero") Genero genero) {
        genero.setId(null);
        service.create(genero);
        return "redirect:/".concat(GeneroEnum.GENEROS.nameVar);
    }
}
