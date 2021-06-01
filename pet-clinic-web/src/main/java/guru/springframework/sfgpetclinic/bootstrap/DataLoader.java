package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;


    public DataLoader(OwnerService ownerService,
                      VetService vetService,
                      PetTypeService petTypeService) {

        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<PetType> petTypeList = createPetTypeData();
        System.out.println("loaded pettypes");

        List<Owner> ownerList = createOwnerData();
        System.out.println("loaded owners");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(petTypeList.get(0));
        mikesPet.setOwner(ownerList.get(0));
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rocco");
        ownerList.get(0).getPets().add(mikesPet);

        Pet fionasCat = new Pet();
        fionasCat.setName("Just cat");
        fionasCat.setOwner(ownerList.get(1));
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setPetType(petTypeList.get(1));
        ownerList.get(1).getPets().add(fionasCat);


        List<Vet> vetList = createVetData();
        System.out.println("loaded vets");

    }

    private List<Vet> createVetData() {
        List<Vet> vetList = new ArrayList<>();

        Vet vet1 = new Vet();
        vet1.setFirstName("firstnmame bla");
        vet1.setLastName("lastname bla");

        Vet vet2 = new Vet();
        vet2.setFirstName("firstnmame blub");
        vet2.setLastName("lastname blub");

        vetList.add(vetService.save(vet1));
        vetList.add(vetService.save(vet2));

        return vetList;
    }

    private List<Owner> createOwnerData() {
        List<Owner> ownerList = new ArrayList<>();

        Owner owner1 = new Owner();
        owner1.setFirstName("michael");
        owner1.setLastName("weston");
        owner1.setAddress("adress1");
        owner1.setCity("city1");
        owner1.setTelephon("tel1");

        Owner owner2 = new Owner();
        owner2.setFirstName("blub");
        owner2.setLastName("keks");
        owner2.setAddress("address2");
        owner2.setCity("city2");
        owner2.setTelephon("tel2");

        ownerList.add(ownerService.save(owner1));
        ownerList.add(ownerService.save(owner2));

        return ownerList;
    }

    private List<PetType> createPetTypeData() {
        List<PetType> petTypeList = new ArrayList<>();

        PetType petType1 = new PetType();
        petType1.setName("dog");

        PetType petType2 = new PetType();
        petType2.setName("cat");

        petTypeList.add(petTypeService.save(petType1));
        petTypeList.add(petTypeService.save(petType2));

        return petTypeList;
    }
}
