package com.mes.beerordermicroservice.web.mappers;

import brewery.model.BeerOrderDto;
import brewery.model.BeerOrderLineDto;
import com.mes.beerordermicroservice.domain.BeerOrder;
import com.mes.beerordermicroservice.domain.BeerOrderLine;
import com.mes.beerordermicroservice.domain.BeerOrderStatusEnum;
import com.mes.beerordermicroservice.domain.Customer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-02-28T14:21:51+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (AdoptOpenJDK)"
)
@Component
public class BeerOrderMapperImpl implements BeerOrderMapper {

    @Autowired
    private DateMapper dateMapper;
    @Autowired
    private BeerOrderLineMapper beerOrderLineMapper;

    @Override
    public BeerOrderDto beerOrderToDto(BeerOrder beerOrder) {
        if ( beerOrder == null ) {
            return null;
        }

        BeerOrderDto beerOrderDto = new BeerOrderDto();

        beerOrderDto.setCustomerId( beerOrderCustomerId( beerOrder ) );
        beerOrderDto.setId( beerOrder.getId() );
        if ( beerOrder.getVersion() != null ) {
            beerOrderDto.setVersion( beerOrder.getVersion().intValue() );
        }
        beerOrderDto.setCreatedDate( dateMapper.asOffsetDateTime( beerOrder.getCreatedDate() ) );
        beerOrderDto.setLastModifiedDate( dateMapper.asOffsetDateTime( beerOrder.getLastModifiedDate() ) );
        beerOrderDto.setCustomerRef( beerOrder.getCustomerRef() );
        beerOrderDto.setBeerOrderLines( beerOrderLineSetToBeerOrderLineDtoList( beerOrder.getBeerOrderLines() ) );
        if ( beerOrder.getOrderStatus() != null ) {
            beerOrderDto.setOrderStatus( beerOrder.getOrderStatus().name() );
        }
        beerOrderDto.setOrderStatusCallbackUrl( beerOrder.getOrderStatusCallbackUrl() );

        return beerOrderDto;
    }

    @Override
    public BeerOrder dtoToBeerOrder(BeerOrderDto dto) {
        if ( dto == null ) {
            return null;
        }

        BeerOrder beerOrder = new BeerOrder();

        beerOrder.setId( dto.getId() );
        beerOrder.setVersion( (long) dto.getVersion() );
        beerOrder.setCreatedDate( dateMapper.asTimestamp( dto.getCreatedDate() ) );
        beerOrder.setLastModifiedDate( dateMapper.asTimestamp( dto.getLastModifiedDate() ) );
        beerOrder.setCustomerRef( dto.getCustomerRef() );
        beerOrder.setBeerOrderLines( beerOrderLineDtoListToBeerOrderLineSet( dto.getBeerOrderLines() ) );
        if ( dto.getOrderStatus() != null ) {
            beerOrder.setOrderStatus( Enum.valueOf( BeerOrderStatusEnum.class, dto.getOrderStatus() ) );
        }
        beerOrder.setOrderStatusCallbackUrl( dto.getOrderStatusCallbackUrl() );

        return beerOrder;
    }

    private UUID beerOrderCustomerId(BeerOrder beerOrder) {
        if ( beerOrder == null ) {
            return null;
        }
        Customer customer = beerOrder.getCustomer();
        if ( customer == null ) {
            return null;
        }
        UUID id = customer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected List<BeerOrderLineDto> beerOrderLineSetToBeerOrderLineDtoList(Set<BeerOrderLine> set) {
        if ( set == null ) {
            return null;
        }

        List<BeerOrderLineDto> list = new ArrayList<BeerOrderLineDto>( set.size() );
        for ( BeerOrderLine beerOrderLine : set ) {
            list.add( beerOrderLineMapper.beerOrderLineToDto( beerOrderLine ) );
        }

        return list;
    }

    protected Set<BeerOrderLine> beerOrderLineDtoListToBeerOrderLineSet(List<BeerOrderLineDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<BeerOrderLine> set = new HashSet<BeerOrderLine>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( BeerOrderLineDto beerOrderLineDto : list ) {
            set.add( beerOrderLineMapper.dtoToBeerOrderLine( beerOrderLineDto ) );
        }

        return set;
    }
}
