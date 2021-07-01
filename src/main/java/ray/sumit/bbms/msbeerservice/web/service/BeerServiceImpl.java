package ray.sumit.bbms.msbeerservice.web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ray.sumit.bbms.msbeerservice.domain.Beer;
import ray.sumit.bbms.msbeerservice.exception.BeerNotSavedException;
import ray.sumit.bbms.msbeerservice.repository.BeerRepository;
import ray.sumit.bbms.msbeerservice.web.mapper.BeerMapper;
import ray.sumit.bbms.msbeerservice.web.model.BeerDto;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper mapper;

    @Autowired
    public BeerServiceImpl(BeerRepository beerRepository, BeerMapper mapper) {
        this.beerRepository = beerRepository;
        this.mapper = mapper;
    }

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return mapper.mapBeerToBeerDto(beerRepository.findById(beerId).get());
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        final Beer beer = mapper.mapBeerDtoToBeer(beerDto);
        if (beer == null) {
            throw new BeerNotSavedException("The beer with name = " + beer.getBeerName() + "is not created");
        }
        return mapper.mapBeerToBeerDto(beerRepository.save(beer));
    }

    @Override
    public void saveExistingBeer(UUID beerId, BeerDto beerDto) {
        beerRepository.findById(beerId).ifPresentOrElse(
                (beer) -> {
                    beer.setBeerName(beerDto.getBeerName());
                    beer.setBeerStyle(beerDto.getBeerStyle().name());
                    beer.setPrice(beerDto.getPrice());
                    beer.setUpc(beerDto.getUpc());
                    beerRepository.save(beer);
                },
                () -> {
                    throw new BeerNotSavedException("The beer with name = " + beerDto.getBeerName() + "is not updated");
                }
        );
    }
}
