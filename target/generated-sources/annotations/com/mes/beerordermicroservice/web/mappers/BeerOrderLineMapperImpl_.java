package com.mes.beerordermicroservice.web.mappers;

import brewery.model.BeerOrderLineDto;
import com.mes.beerordermicroservice.domain.BeerOrderLine;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-02-28T14:21:50+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (AdoptOpenJDK)"
)
@Component
@Qualifier("delegate")
public class BeerOrderLineMapperImpl_ implements BeerOrderLineMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        if ( line == null ) {
            return null;
        }

        BeerOrderLineDto beerOrderLineDto = new BeerOrderLineDto();

        beerOrderLineDto.setId( line.getId() );
        if ( line.getVersion() != null ) {
            beerOrderLineDto.setVersion( line.getVersion().intValue() );
        }
        beerOrderLineDto.setCreatedDate( dateMapper.asOffsetDateTime( line.getCreatedDate() ) );
        beerOrderLineDto.setLastModifiedDate( dateMapper.asOffsetDateTime( line.getLastModifiedDate() ) );
        beerOrderLineDto.setUpc( String.valueOf( line.getUpc() ) );
        beerOrderLineDto.setBeerId( line.getBeerId() );
        beerOrderLineDto.setOrderQuantity( line.getOrderQuantity() );
        beerOrderLineDto.setQuantityAllocated( line.getQuantityAllocated() );

        return beerOrderLineDto;
    }

    @Override
    public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
        if ( dto == null ) {
            return null;
        }

        BeerOrderLine beerOrderLine = new BeerOrderLine();

        beerOrderLine.setId( dto.getId() );
        if ( dto.getVersion() != null ) {
            beerOrderLine.setVersion( dto.getVersion().longValue() );
        }
        beerOrderLine.setCreatedDate( dateMapper.asTimestamp( dto.getCreatedDate() ) );
        beerOrderLine.setLastModifiedDate( dateMapper.asTimestamp( dto.getLastModifiedDate() ) );
        beerOrderLine.setBeerId( dto.getBeerId() );
        if ( dto.getOrderQuantity() != null ) {
            beerOrderLine.setOrderQuantity( dto.getOrderQuantity() );
        }
        if ( dto.getQuantityAllocated() != null ) {
            beerOrderLine.setQuantityAllocated( dto.getQuantityAllocated() );
        }
        if ( dto.getUpc() != null ) {
            beerOrderLine.setUpc( Long.parseLong( dto.getUpc() ) );
        }

        return beerOrderLine;
    }
}
