package com.mes.beerordermicroservice.repositories;

import com.mes.beerordermicroservice.domain.BeerOrderLine;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * Created by mesar on 2/14/2020
 */
public interface BeerOrderLineRepository extends PagingAndSortingRepository<BeerOrderLine, UUID> {
}