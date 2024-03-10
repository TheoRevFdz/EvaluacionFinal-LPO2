package com.hrevfdz.EvaluacionFinalLPO2.controllers;

import com.hrevfdz.EvaluacionFinalLPO2.enums.GeneroEnum;
import com.hrevfdz.EvaluacionFinalLPO2.enums.LibroEnum;
import com.hrevfdz.EvaluacionFinalLPO2.models.Genero;
import com.hrevfdz.EvaluacionFinalLPO2.models.Libro;
import com.hrevfdz.EvaluacionFinalLPO2.services.IGeneroService;
import com.hrevfdz.EvaluacionFinalLPO2.services.ILibroService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private ILibroService service;
    @Autowired
    private IGeneroService generoService;

    @GetMapping
    public String findAllGeneros(Model model) {
        List<Libro> libros = service.findAll();
        model.addAttribute(LibroEnum.LIBROS.nameVar, libros);
        model.addAttribute(LibroEnum.LIBROS.nameVar.concat("Title"), LibroEnum.LIBROS_TITLE.nameVar);
        final String pagePath = LibroEnum.LIBROS.nameVar.concat("/").concat(LibroEnum.LIBROS_LIST.nameVar);
        return pagePath;
    }

    @GetMapping("/register")
    public String showGeneroRegister(Model model) {
        final List<Genero> generos = generoService.findAll();
        model.addAttribute(GeneroEnum.GENEROS.nameVar, generos);
        model.addAttribute("libro", Libro.builder().build());
        final String pagePath = LibroEnum.LIBROS.nameVar.concat("/").concat(LibroEnum.LIBROS_REGISTER.nameVar);
        return pagePath;
    }

    @PostMapping("/register")
    public String createPelicula(@ModelAttribute(name = "genero") Libro libro) {
        libro.setId(null);
        service.create(libro);
        return "redirect:/".concat(LibroEnum.LIBROS.nameVar);
    }

    @GetMapping("/edit/{id}")
    public String editPelicula(@PathVariable(name = "id") Long id, Model model) {
        Optional<Libro> optionalLibro = service.findById(id);
        if (optionalLibro.isPresent()) {
            final List<Genero> generos = generoService.findAll();
            model.addAttribute("libro", optionalLibro.get());
            model.addAttribute(GeneroEnum.GENEROS.nameVar, generos);
        }
        final String pagePath = LibroEnum.LIBROS.nameVar.concat("/").concat(LibroEnum.LIBROS_EDIT.nameVar);
        return pagePath;
    }

    @PostMapping("/edit")
    public String updateLibro(@ModelAttribute(name = "libro") Libro libro) {
        service.update(libro);
        return "redirect:/".concat(LibroEnum.LIBROS.nameVar);
    }

    @GetMapping("/delete/{id}")
    public String deleteLibro(@PathVariable(name = "id") Long id, Model model) {
        service.delete(id);
        List<Libro> libros = service.findAll();
        model.addAttribute(LibroEnum.LIBROS.nameVar, libros);
        final String pagePath = LibroEnum.LIBROS.nameVar.concat("/").concat(LibroEnum.LIBROS_LIST.nameVar);
        return pagePath;
    }

    @GetMapping("/reporte")
    public void exportarPDF(HttpServletResponse response)throws JRException, IOException {
        response.addHeader("Content-disposition", "inline: filename" + "libros.pdf");
        response.setContentType("application/pdf");
        ServletOutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(service.exportReport("classpath:report_libros.jrxml"), outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
