package com.yonatankarp.petclinic.contorllers;

import com.yonatankarp.petclinic.model.Owner;
import com.yonatankarp.petclinic.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

    private static final String VIEWS_FIND_OWNER_FORM = "owners/find_owners";

    private final OwnerService ownerService;

    @InitBinder
    public void setAllowedFilter(final WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public String processFindForm(final Owner owner,
                                  final BindingResult result,
                                  final Model model) {
        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            // empty string signifies broadest possible search
            owner.setLastName("");
        }

        final var owners = ownerService.findAllByLastNameLike(owner.getLastName());

        if (owners.isEmpty()) {
            result.rejectValue("lastName", HttpStatus.NOT_FOUND.name(), "not found");
            return "owners/find_owners";
        } else if (owners.size() == 1) {
            final var savedOwner = owners.iterator().next();
            return "redirect:/owners/" + savedOwner.getId();
        } else {
            // multiple owners found
            model.addAttribute("owners", owners);
            return "owners/owners_list";
        }
    }

    @GetMapping("/find")
    public String findOwners(final Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_FIND_OWNER_FORM;
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwners(@PathVariable final Long ownerId) {
        final var modelAndView = new ModelAndView("owners/owner_details");
        modelAndView.addObject(ownerService.findById(ownerId));
        return modelAndView;
    }
}
