package it.corso.microservizi.provaspring.controllers;

import it.corso.microservizi.provaspring.constants.Paths;
import it.corso.microservizi.provaspring.data.FormDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class HomeController {

    @Value("${pages.welcomePage}")
    private String welcomePage;

    @Value("${pages.afterPost}")
    private String afterPost;

    @RequestMapping(Paths.HOME)
    public String welcome(Model model) {
        model.addAttribute("now", new Date());
        return welcomePage;
    }

    @RequestMapping("/prova/{nome}")
    public String provaNome(@PathVariable("nome") String nome, Model model) {
        model.addAttribute("now", nome);
        return welcomePage;
    }

    @RequestMapping("/query")
    public String queryString(@RequestParam("name") String nome, Model model) {
        model.addAttribute("now", nome);
        return welcomePage;
    }

    @RequestMapping(value = "/testform", method = RequestMethod.POST)
    public String testForm(FormDTO data, Model model) {
        model.addAttribute("a", data.getA());
        model.addAttribute("b", data.getB());
        return afterPost;
    }

}
