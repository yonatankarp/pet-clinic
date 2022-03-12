package com.yonatankarp.petclinic.controllers;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Map;
import com.yonatankarp.petclinic.model.Visit;
import com.yonatankarp.petclinic.services.PetService;
import com.yonatankarp.petclinic.services.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    @InitBinder
    public void dataBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");

        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text));
            }
        });
    }

    /**
     * Called before each and every @RequestMapping annotated method.
     * 2 goals:
     * - Make sure we always have fresh data
     * - Since we do not use the session scope, make sure that Pet object always has an id
     * (Even though id is not part of the form fields)
     */
    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable final Long petId, final Map<String, Object> model) {
        final var pet = petService.findById(petId);
        model.put("pet", pet);

        Visit visit = Visit.builder().pet(pet).build();
        pet.getVisits().add(visit);

        return visit;
    }

    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
    @GetMapping("/new")
    @SuppressWarnings("MVCPathVariableInspection")
    public String initNewVisitForm() {
        return "pets/create_or_update_visit_form";
    }

    // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
    @PostMapping("/new")
    @SuppressWarnings("MVCPathVariableInspection")
    public String processNewVisitForm(@Valid final Visit visit, final BindingResult result) {
        if (result.hasErrors()) {
            return "pets/create_or_update_visit_form";
        }

        visitService.save(visit);
        return "redirect:/owners/{ownerId}";
    }
}
