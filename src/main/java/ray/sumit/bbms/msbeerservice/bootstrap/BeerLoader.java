package ray.sumit.bbms.msbeerservice.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ray.sumit.bbms.msbeerservice.domain.Beer;
import ray.sumit.bbms.msbeerservice.repository.BeerRepository;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    @Autowired
    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .beerName("Shafley")
                    .beerStyle("pale ale")
                    .quantityToBrew(200)
                    .minOnHand(20)
                    .upc(377665556L)
                    .price(new BigDecimal("11.75"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Kalyani")
                    .beerStyle("black")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(377665557L)
                    .price(new BigDecimal("9.25"))
                    .build());
        }
    }
}
