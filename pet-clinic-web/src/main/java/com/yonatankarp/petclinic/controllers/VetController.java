package com.yonatankarp.petclinic.controllers;

import java.util.Set;
import com.yonatankarp.petclinic.model.Vet;
import com.yonatankarp.petclinic.services.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    @GetMapping({ "/vets", "/vets/index", "/vets/index.html", "/vets.html"})
    public String listVets(final Model model) {
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }

    @GetMapping("/api/vets")
    public @ResponseBody Set<Vet> getVetJson() {
        return vetService.findAll();
    }
}
