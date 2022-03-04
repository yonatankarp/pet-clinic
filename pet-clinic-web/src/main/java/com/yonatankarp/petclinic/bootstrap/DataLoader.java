package com.yonatankarp.petclinic.bootstrap;

import java.time.LocalDate;
import com.yonatankarp.petclinic.model.Owner;
import com.yonatankarp.petclinic.model.Pet;
import com.yonatankarp.petclinic.model.PetType;
import com.yonatankarp.petclinic.model.Vet;
import com.yonatankarp.petclinic.services.OwnerService;
import com.yonatankarp.petclinic.services.PetService;
import com.yonatankarp.petclinic.services.PetTypeService;
import com.yonatankarp.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetService petService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetService petService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) {

        final PetType dog = new PetType();
        dog.setName("Dog");
        final PetType savedDogType = petTypeService.save(dog);

        final PetType cat = new PetType();
        cat.setName("Cat");
        final PetType savedCatType = petTypeService.save(cat);

        System.out.println("Loaded Pet Type...");

        final Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickell");
        owner1.setCity("Miami");
        owner1.setPhone("305-555-0113");

        final Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rasco");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        final Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickell");
        owner2.setCity("Miami");
        owner2.setPhone("305-555-0113");
        ownerService.save(owner2);

        final Pet fionasCat = new Pet();
        fionasCat.setPetType(savedCatType);
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setOwner(owner2);
        fionasCat.setName("Oliver");
        owner2.getPets().add(fionasCat);

        System.out.println("Loaded Owners...");

        final Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        final Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");

        final Pet pet1 = new Pet();
        pet1.setOwner(owner1);
        pet1.setBirthDate(LocalDate.of(2016, 9, 5));
        pet1.setPetType(new PetType());

        petService.save(pet1);

        final Pet pet2 = new Pet();
        pet2.setOwner(owner2);
        pet2.setBirthDate(LocalDate.of(2019, 5, 11));
        pet2.setPetType(new PetType());

        petService.save(pet2);


        System.out.println("Loaded Pets...");
    }
}
