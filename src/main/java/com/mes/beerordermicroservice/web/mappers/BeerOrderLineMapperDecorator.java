package com.mes.beerordermicroservice.web.mappers;

import com.mes.beerordermicroservice.domain.BeerOrderLine;
import com.mes.beerordermicroservice.services.beer.BeerService;
import com.mes.beerordermicroservice.web.domain.BeerDto;
import com.mes.beerordermicroservice.web.domain.BeerOrderLineDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

/**
 * Created by mesar on 2/19/2020
 */
@Slf4j
public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper{

    private BeerService beerService;
    private BeerOrderLineMapper beerOrderLineMapper;

    @Autowired
    public void setBeerService(BeerService beerService){
        this.beerService=beerService;
    }

    @Autowired
    @Qualifier("delegate")
    public void etBeerOrderLineMapper(BeerOrderLineMapper beerOrderLineMapper){
        this.beerOrderLineMapper=beerOrderLineMapper;
    }

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        log.debug("Created Beer Order Line: " + line.toString());
        log.debug("Line Details-> id: " + line.getBeerId() + " upc: " + line.getUpc());
        BeerOrderLineDto orderLineDto = beerOrderLineMapper.beerOrderLineToDto(line);
        Optional<BeerDto> beerDtoOptional = beerService.getBeerByUpc(String.valueOf(line.getUpc()));

        beerDtoOptional.ifPresent(beerDto -> {
            orderLineDto.setBeerName(beerDto.getBeerName());
            orderLineDto.setBeerStyle(beerDto.getBeerStyle());
            orderLineDto.setPrice(beerDto.getPrice());
            orderLineDto.setBeerId(beerDto.getId());
        });

        return orderLineDto;
    }
}
