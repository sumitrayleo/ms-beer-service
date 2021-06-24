package ray.sumit.bbms.msbeerservice.web.mapper;

import org.mapstruct.Mapper;
import ray.sumit.bbms.msbeerservice.domain.Beer;
import ray.sumit.bbms.msbeerservice.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    BeerDto mapBeerToBeerDto(Beer beer);

    Beer mapBeerDtoToBeer(BeerDto beerDto);
}
