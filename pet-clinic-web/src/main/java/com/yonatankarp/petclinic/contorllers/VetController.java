package com.yonatankarp.petclinic.contorllers;

import com.yonatankarp.petclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({ "/vets", "/vets/index", "/vets/index.html", "/vets.html"})
    public String listVets(final Model model) {
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }
}
