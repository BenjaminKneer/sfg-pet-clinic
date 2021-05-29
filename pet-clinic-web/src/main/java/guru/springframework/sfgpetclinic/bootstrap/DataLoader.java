package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        this.ownerService = new OwnerServiceMap();
        this.vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("michael");
        owner1.setLastName("weston");

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("blub");
        owner2.setLastName("keks");

        ownerService.save(owner1.getId(), owner1);
        ownerService.save(owner2.getId(), owner2);

        System.out.println("loaded owners");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("firstnmame bla");
        vet1.setLastName("lastname bla");

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("firstnmame blub");
        vet2.setLastName("lastname blub");

        vetService.save(vet1.getId(), vet1);
        vetService.save(vet2.getId(), vet2);

        System.out.println("loaded vets");

    }
}
