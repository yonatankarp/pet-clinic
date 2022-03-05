package com.yonatankarp.petclinic.bootstrap;

import java.time.LocalDate;
import com.yonatankarp.petclinic.model.Owner;
import com.yonatankarp.petclinic.model.Pet;
import com.yonatankarp.petclinic.model.PetType;
import com.yonatankarp.petclinic.model.Specialty;
import com.yonatankarp.petclinic.model.Vet;
import com.yonatankarp.petclinic.services.OwnerService;
import com.yonatankarp.petclinic.services.PetService;
import com.yonatankarp.petclinic.services.PetTypeService;
import com.yonatankarp.petclinic.services.SpecialtyService;
import com.yonatankarp.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService,
                      VetService vetService,
                      PetService petService,
                      PetTypeService petTypeService,
                      SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

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

        final var mikesPet = storePet(dogType, "Rasco");
        final var mike = storeOwner("Michael", "Weston", "123 Brickell", "Miami", "305-555-0113", mikesPet);

        System.out.println("Loaded Pets...");

        final var fionasCat = storePet(catType, "Oliver");
        final var fiona = storeOwner("Fiona", "Glenanne", "123 Brickell", "Miami", "305-555-0113", fionasCat);

        System.out.println("Loaded Owners...");

        final var sam = storeVet("Sam", "Axe", radiology);
        final var jessie = storeVet("Jessie", "Porter", surgery);

        System.out.println("Loaded Vets...");
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
                             final String phone,
                             final Pet pet) {
        final var owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setAddress(address);
        owner.setCity(city);
        owner.setPhone(phone);
        owner.getPets().add(pet);
        pet.setOwner(owner);
        return ownerService.save(owner);
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
}
