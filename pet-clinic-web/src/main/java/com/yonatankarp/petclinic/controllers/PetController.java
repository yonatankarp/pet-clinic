package com.yonatankarp.petclinic.controllers;

import javax.validation.Valid;
import java.util.Collection;
import com.yonatankarp.petclinic.model.Owner;
import com.yonatankarp.petclinic.model.Pet;
import com.yonatankarp.petclinic.model.PetType;
import com.yonatankarp.petclinic.services.OwnerService;
import com.yonatankarp.petclinic.services.PetService;
import com.yonatankarp.petclinic.services.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
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
@RequestMapping("/owners/{ownerId}/pets")
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/create_or_update_pet_form";

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    @ModelAttribute("petTypes")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable final Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder
    public void initOwnerBinder(final WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    @SuppressWarnings("MVCPathVariableInspection")
    public String initCreationForm(final Owner owner, final Model model) {
        final var pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    @SuppressWarnings("MVCPathVariableInspection")
    public String processCreationForm(final Owner owner,
                                      @Valid final Pet pet,
                                      final BindingResult result,
                                      final ModelMap model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }

        owner.getPets().add(pet);
        if (result.hasErrors()) {
            model.put("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }

        petService.save(pet);
        return String.format("redirect:/owners/%d", owner.getId());
    }

    @GetMapping("{petId}/edit")
    @SuppressWarnings("MVCPathVariableInspection")
    public String initUpdateForm(@PathVariable final Long petId,
                                 final Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("{petId}/edit")
    @SuppressWarnings("MVCPathVariableInspection")
    public String processUpdateForm(@Valid final Pet pet,
                                    final BindingResult result,
                                    final Owner owner,
                                    final Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }

        owner.getPets().add(pet);
        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
    }
}
