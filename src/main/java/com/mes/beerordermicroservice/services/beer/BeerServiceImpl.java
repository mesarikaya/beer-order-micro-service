package com.mes.beerordermicroservice.services.beer;

import com.mes.beerordermicroservice.web.domain.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by mesar on 2/19/2020
 */
@Slf4j
@ConfigurationProperties(prefix = "com.mes", ignoreUnknownFields = false)
@Service
public class BeerServiceImpl implements BeerService{

    public static final String BEER_PATH_V1 = "api/v1/beer/";
    public static final String BEER_UPC_PATH_V1 = "api/v1/beerUpc/";
    private final RestTemplate restTemplate;

    private String beerServiceHost;

    public BeerServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID uuid) {
        return Optional.of(
                restTemplate
                        .getForObject(beerServiceHost + BEER_PATH_V1 + uuid.toString(), BeerDto.class));
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(String upc) {
        log.debug("Asking beer with beer upc: " + upc);
        return Optional.of(
                restTemplate
                        .getForObject(beerServiceHost + BEER_UPC_PATH_V1 + upc, BeerDto.class));
    }

    public void setBeerServiceHost(String beerServiceHost) {
        this.beerServiceHost = beerServiceHost;
    }
}
