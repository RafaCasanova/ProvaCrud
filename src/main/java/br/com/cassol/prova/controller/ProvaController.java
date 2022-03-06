package br.com.cassol.prova.controller;

import br.com.cassol.prova.model.Prova;
import br.com.cassol.prova.services.ProvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProvaController {
    @Autowired private ProvaService service;

    @GetMapping("/provas")
    public String ListaDeProvas(Model model){
        List<Prova> provas = service.listAll();
        model.addAttribute("ListaDeProvas",provas);
        return "provas";
    }

    @GetMapping("/resultados")
    public String resultados(Model model){
        model.addAttribute("total",service.getcount());
        model.addAttribute("media",service.media());
        model.addAttribute("MenorNota",service.menorNota());
        model.addAttribute("MaiorNota",service.maiorNota());
        return "resultados";
    }


    @GetMapping("/provas/cadastro")
    public String cadastroDeProva(Model model){
        model.addAttribute("prova",new Prova());
        model.addAttribute("pageTitle", "Cadastrando novo usuário");
        return "cadastro";
    }

    @PostMapping("/prova/save")
    public String GravarProva(Prova prova, RedirectAttributes ra){
        service.save(prova);
        ra.addFlashAttribute("msg","Salvo Com sucesso");
        return "redirect:/provas";
    }
    @GetMapping("/Prova/edit/{id}")
    public String editaProva(@PathVariable("id")Integer id,Model model, RedirectAttributes ra){
        try {
            Prova prova = service.get(id);
            model.addAttribute("prova",prova);
            model.addAttribute("pageTitle", "Editando usuário " + id);
            return "cadastro";
        } catch (Exception e) {
            ra.addFlashAttribute("msg","Salvo Com sucesso");
            return "redirec:/index";
        }
    }
    @GetMapping("/Prova/delete/{id}")
    public String deletaProva(@PathVariable("id")Integer id, RedirectAttributes ra){
        try {
            service.delete(id);
        }catch (Exception e){
            ra.addFlashAttribute("msg","Salvo Com sucesso");
        }return "redirect:/provas";

    }
}
