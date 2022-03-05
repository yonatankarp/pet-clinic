package com.yonatankarp.petclinic.bootstrap;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import com.yonatankarp.petclinic.model.Owner;
import com.yonatankarp.petclinic.model.Pet;
import com.yonatankarp.petclinic.model.PetType;
import com.yonatankarp.petclinic.model.Specialty;
import com.yonatankarp.petclinic.model.Vet;
import com.yonatankarp.petclinic.model.Visit;
import com.yonatankarp.petclinic.services.OwnerService;
import com.yonatankarp.petclinic.services.PetTypeService;
import com.yonatankarp.petclinic.services.SpecialtyService;
import com.yonatankarp.petclinic.services.VetService;
import com.yonatankarp.petclinic.services.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    @Override
    public void run(String... args) {
        // We assume that if there's pet type in our db than there's no need
        // To load our data again
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        final var dogType = storePetType("Dog");
        final var catType = storePetType("Cat");

        log.debug("Loaded Pet Type...");

        final var radiology = storeSpecialty("Radiology");
        final var surgery = storeSpecialty("Surgery");
        final var dentistry = storeSpecialty("Dentistry");

        log.debug("Loaded Specialties...");

        final var rasco = createPet(dogType, "Rasco");
        final var mike = storeOwnerAndPet("Michael", "Weston", "123 Brickell", "Miami", "305-555-0113", rasco);

        final var oliver = createPet(catType, "Oliver");
        final var fiona = storeOwnerAndPet("Fiona", "Glenanne", "123 Brickell", "Miami", "305-555-0113", oliver);

        log.debug("Loaded Owners and Pets...");

        final var sam = storeVet("Sam", "Axe", radiology);
        final var jessie = storeVet("Jessie", "Porter", surgery);

        log.debug("Loaded Vets...");

        final var dogVisit = storeVisit(rasco, "Tried Dog");
        final var catVisit = storeVisit(oliver, "Sneezy Kitten");

        log.debug("Loaded Visits...");
    }

    private PetType storePetType(final String name) {
        final var petType = PetType.builder()
                .name(name)
                .build();
        return petTypeService.save(petType);
    }

    private Specialty storeSpecialty(final String description) {
        final var specialty = Specialty.builder()
                .description(description)
                .build();
        return specialtyService.save(specialty);
    }

    private Pet createPet(final PetType petType, final String name) {
        return Pet.builder()
                .petType(petType)
                .birthDate(LocalDate.now())
                .name(name)
                .build();
    }

    private Owner storeOwnerAndPet(final String firstName,
                                   final String lastName,
                                   final String address,
                                   final String city,
                                   final String phone,
                                   final Pet pet) {
        final var owner = Owner.builder()
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .city(city)
                .phone(phone)
                .pets(new HashSet<>(List.of(pet)))
                .build();
        pet.setOwner(owner);
        return ownerService.save(owner);
    }

    private Vet storeVet(final String firstName, final String lastName, final Specialty specialty) {
        final var vet = Vet.builder()
                .firstName(firstName)
                .lastName(lastName)
                .specialties(new HashSet<>(List.of(specialty)))
                .build();
        return vetService.save(vet);
    }

    private Visit storeVisit(final Pet pet, final String description) {
        final var visit = Visit.builder()
                .pet(pet)
                .date(LocalDate.now())
                .description(description)
                .build();
        return visitService.save(visit);
    }
}
