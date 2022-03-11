package com.yonatankarp.petclinic.contorllers;

import com.yonatankarp.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping({"", "/index", "/index.html"})
    public String listOwners(final Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @GetMapping("/find")
    public String findOwners() {
        return "not_implemented";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwners(@PathVariable final Long ownerId) {
        final var modelAndView = new ModelAndView("owners/owner_details");
        modelAndView.addObject(ownerService.findById(ownerId));
        return modelAndView;
    }
}
