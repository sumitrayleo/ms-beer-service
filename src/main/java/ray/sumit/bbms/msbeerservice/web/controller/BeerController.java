package ray.sumit.bbms.msbeerservice.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ray.sumit.bbms.msbeerservice.exception.BeerNotSavedException;
import ray.sumit.bbms.msbeerservice.web.model.BeerDto;
import ray.sumit.bbms.msbeerservice.web.service.BeerService;

import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {
    private final BeerService beerService;

    @Autowired
    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDto beer) throws BeerNotSavedException {
        beerService.saveNewBeer(beer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @Validated @RequestBody BeerDto beer) {
        beerService.saveExistingBeer(beerId, beer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
