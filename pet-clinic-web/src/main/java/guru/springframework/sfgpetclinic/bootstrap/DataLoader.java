package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
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
    private final PetService petService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;


    public DataLoader(OwnerService ownerService,
                      VetService vetService,
                      PetTypeService petTypeService,
                      PetService petService, SpecialtyService specialtyService,
                      VisitService visitService) {

        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
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

        petService.save(fionasCat);

        Specialty radiology = new Specialty();
        radiology.setDescription("radiology description");

        Specialty surgerey = new Specialty();
        surgerey.setDescription("surgerey description");

        Specialty dentistry = new Specialty();
        dentistry.setDescription("dentistry description");

        Specialty radiologySaved = specialtyService.save(radiology);
        Specialty surgereySaved = specialtyService.save(surgerey);
        Specialty dentistrySaved = specialtyService.save(dentistry);

        Visit catVisit = new Visit();
        catVisit.setPet(fionasCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("sneezy kitty");

        visitService.save(catVisit);

        List<Vet> vetList = createVetData();
        System.out.println("loaded vets");

        vetList.get(0).getSpecialities().add(radiologySaved);
        vetList.get(1).getSpecialities().add(surgereySaved);
        vetService.save(vetList.get(0));
        vetService.save(vetList.get(1));

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
