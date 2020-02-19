package com.mes.beerordermicroservice.services.beer;

import com.mes.beerordermicroservice.web.domain.BeerDto;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by mesar on 2/19/2020
 */
public interface BeerService {

    Optional<BeerDto> getBeerById(UUID uuid);

    Optional<BeerDto> getBeerByUpc(String upc);


}
