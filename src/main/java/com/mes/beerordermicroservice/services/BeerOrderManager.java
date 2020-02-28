package com.mes.beerordermicroservice.services;

import com.mes.beerordermicroservice.domain.BeerOrder;
import brewery.model.BeerOrderDto;

import java.util.UUID;

/**
 * Created by mesar on 2/27/2020
 */
public interface BeerOrderManager {

    BeerOrder newBeerOrder(BeerOrder beerOrder);

    void processValidationResult(UUID beerOrderId, Boolean isValid);

    void beerOrderAllocationPassed(BeerOrderDto beerOrderDto);

    void beerOrderAllocationPendingInventory(BeerOrderDto beerOrderDto);

    void beerOrderAllocationFailed(BeerOrderDto beerOrderDto);

    void beerOrderPickedUp(UUID id);
}
