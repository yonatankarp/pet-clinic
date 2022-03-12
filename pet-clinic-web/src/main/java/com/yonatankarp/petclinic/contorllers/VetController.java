package com.yonatankarp.petclinic.contorllers;

import com.yonatankarp.petclinic.services.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class VetController {

    private static final String VIEWS_VETS_LIST_FORM = "vets/index";

    private final VetService vetService;

    @RequestMapping({ "/vets", "/vets/index", "/vets/index.html", "/vets.html"})
    public String listVets(final Model model) {
        model.addAttribute("vets", vetService.findAll());
        return VIEWS_VETS_LIST_FORM;
    }
}
