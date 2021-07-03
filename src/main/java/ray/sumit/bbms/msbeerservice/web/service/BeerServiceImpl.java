package ray.sumit.bbms.msbeerservice.web.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ray.sumit.bbms.msbeerservice.domain.Beer;
import ray.sumit.bbms.msbeerservice.exception.BeerNotFoundException;
import ray.sumit.bbms.msbeerservice.repository.BeerRepository;
import ray.sumit.bbms.msbeerservice.web.mapper.BeerMapper;
import ray.sumit.bbms.msbeerservice.web.model.BeerDto;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper mapper;

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return mapper.mapBeerToBeerDto(beerRepository.findById(beerId).orElseThrow(BeerNotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        final Beer beer = mapper.mapBeerDtoToBeer(beerDto);
        return mapper.mapBeerToBeerDto(beerRepository.save(beer));
    }

    @Override
    public BeerDto saveExistingBeer(UUID beerId, BeerDto beerDto) {
        beerRepository.findById(beerId).orElseThrow(BeerNotFoundException::new);
        final Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName(beerDto.getBeerName())
                .beerStyle(beerDto.getBeerStyle().name())
                .price(beerDto.getPrice())
                .upc(beerDto.getUpc())
                .build()
        );
        return mapper.mapBeerToBeerDto(savedBeer);
    }
}
