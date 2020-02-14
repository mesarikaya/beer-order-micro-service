package com.mes.beerordermicroservice.services;

import com.mes.beerordermicroservice.web.domain.BeerOrderDto;
import com.mes.beerordermicroservice.web.domain.BeerOrderPagedList;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Created by mesar on 2/14/2020
 */
public interface BeerOrderService {
    BeerOrderPagedList listOrders(UUID customerId, Pageable pageable);

    BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto);

    BeerOrderDto getOrderById(UUID customerId, UUID orderId);

    void pickupOrder(UUID customerId, UUID orderId);
}
