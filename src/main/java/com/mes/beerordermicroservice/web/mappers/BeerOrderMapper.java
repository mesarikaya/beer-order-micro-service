package com.mes.beerordermicroservice.web.mappers;

import com.mes.beerordermicroservice.domain.BeerOrder;
import com.mes.beerordermicroservice.web.domain.BeerOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by mesar on 2/14/2020
 */
@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {

    @Mapping(target="customerId", source = "customer.id")
    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);

    BeerOrder dtoToBeerOrder(BeerOrderDto dto);
}