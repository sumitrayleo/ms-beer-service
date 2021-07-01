package ray.sumit.bbms.msbeerservice.web.service;

import ray.sumit.bbms.msbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    void saveExistingBeer(UUID beerId, BeerDto beerDto);
}
