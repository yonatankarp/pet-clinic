package com.yonatankarp.petclinic.bootstrap;

import java.time.LocalDate;
import com.yonatankarp.petclinic.model.Owner;
import com.yonatankarp.petclinic.model.Pet;
import com.yonatankarp.petclinic.model.PetType;
import com.yonatankarp.petclinic.model.Specialty;
import com.yonatankarp.petclinic.model.Vet;
import com.yonatankarp.petclinic.model.Visit;
import com.yonatankarp.petclinic.services.OwnerService;
import com.yonatankarp.petclinic.services.PetService;
import com.yonatankarp.petclinic.services.PetTypeService;
import com.yonatankarp.petclinic.services.SpecialtyService;
import com.yonatankarp.petclinic.services.VetService;
import com.yonatankarp.petclinic.services.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetService petService;
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

        System.out.println("Loaded Pet Type...");

        final var radiology = storeSpecialty("Radiology");
        final var surgery = storeSpecialty("Surgery");
        final var dentistry = storeSpecialty("Dentistry");

        System.out.println("Loaded Specialties...");

        final var rasco = storePet(dogType, "Rasco");
        final var oliver = storePet(catType, "Oliver");

        System.out.println("Loaded Pets...");

        final var mike = storeOwner("Michael", "Weston", "123 Brickell", "Miami", "305-555-0113");
        final var fiona = storeOwner("Fiona", "Glenanne", "123 Brickell", "Miami", "305-555-0113");

        System.out.println("Loaded Owners...");

        connectPetToOwner(rasco, mike);
        connectPetToOwner(oliver, fiona);

        System.out.println("Connect owners and pets...");

        final var sam = storeVet("Sam", "Axe", radiology);
        final var jessie = storeVet("Jessie", "Porter", surgery);

        System.out.println("Loaded Vets...");

        final var dogVisit = storeVisit(rasco, "Tried Dog");
        final var catVisit = storeVisit(oliver, "Sneezy Kitten");

        System.out.println("Loaded Visits...");
    }

    private PetType storePetType(final String type) {
        final var dog = new PetType();
        dog.setName(type);
        return petTypeService.save(dog);
    }

    private Specialty storeSpecialty(final String description) {
        final Specialty radiology = new Specialty();
        radiology.setDescription(description);
        return specialtyService.save(radiology);
    }

    private Owner storeOwner(final String firstName,
                             final String lastName,
                             final String address,
                             final String city,
                             final String phone) {
        final var owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setAddress(address);
        owner.setCity(city);
        owner.setPhone(phone);
        return ownerService.save(owner);
    }

    private void connectPetToOwner(final Pet pet, final Owner owner) {
        pet.setOwner(owner);
        petService.save(pet);
        owner.getPets().add(pet);
        ownerService.save(owner);
    }

    private Pet storePet(final PetType petType, final String name) {
        final var pet = new Pet();
        pet.setPetType(petType);
        pet.setBirthDate(LocalDate.now());
        pet.setName(name);
        return petService.save(pet);
    }

    private Vet storeVet(final String firstName, final String lastName, final Specialty specialty) {
        final Vet vet1 = new Vet();
        vet1.setFirstName(firstName);
        vet1.setLastName(lastName);
        vet1.getSpecialties().add(specialty);
        return vetService.save(vet1);
    }

    private Visit storeVisit(final Pet pet, final String description) {
        final var visit = new Visit();
        visit.setPet(pet);
        visit.setDate(LocalDate.now());
        visit.setDescription(description);
        return visitService.save(visit);
    }
}
