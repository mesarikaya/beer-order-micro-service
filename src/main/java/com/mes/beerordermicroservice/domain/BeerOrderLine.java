package com.mes.beerordermicroservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by mesar on 2/14/2020
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class BeerOrderLine extends BaseEntity{

    @ManyToOne
    private BeerOrder beerOrder;

    private UUID beerId;
    private int orderQuantity = 0;
    private int quantityAllocated = 0;

    @Builder
    public BeerOrderLine(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
                         BeerOrder beerOrder, UUID beerId, int orderQuantity, int quantityAllocated) {
        super(id, version, createdDate, lastModifiedDate);
        this.beerOrder = beerOrder;
        this.beerId = beerId;
        this.orderQuantity = orderQuantity;
        this.quantityAllocated = quantityAllocated;
    }
}
