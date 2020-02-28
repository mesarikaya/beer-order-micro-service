package com.mes.beerordermicroservice.statemachine.config;

import com.mes.beerordermicroservice.domain.BeerOrderEventEnum;
import com.mes.beerordermicroservice.domain.BeerOrderStatusEnum;
import com.mes.beerordermicroservice.statemachine.actions.AllocationFailureAction;
import com.mes.beerordermicroservice.statemachine.actions.AllocateOrderAction;
import com.mes.beerordermicroservice.statemachine.actions.ValidatedOrderAction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigBuilder;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

/**
 * Created by mesar on 2/27/2020
 */
@Slf4j
@RequiredArgsConstructor
@EnableStateMachineFactory
@Configuration
public class BeerOrderStateMachineConfig extends StateMachineConfigurerAdapter<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final ValidatedOrderAction validatedOrderAction;
    private final AllocateOrderAction allocateOrderAction;
    private final AllocationFailureAction allocationFailureAction;

    @Override
    public void configure(StateMachineStateConfigurer<BeerOrderStatusEnum, BeerOrderEventEnum> states) throws Exception {

        states.withStates()
                .initial(BeerOrderStatusEnum.NEW)
                .states(EnumSet.allOf(BeerOrderStatusEnum.class))
                .end(BeerOrderStatusEnum.PICKED_UP)
                .end(BeerOrderStatusEnum.DELIVERED)
                .end(BeerOrderStatusEnum.DELIVERY_EXCEPTION)
                .end(BeerOrderStatusEnum.VALIDATION_EXCEPTION)
                .end(BeerOrderStatusEnum.ALLOCATION_EXCEPTION);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<BeerOrderStatusEnum, BeerOrderEventEnum> transitions) throws Exception {

        transitions.withExternal()
                        .source(BeerOrderStatusEnum.NEW)
                        .target(BeerOrderStatusEnum.VALIDATED_PENDING)
                        .event(BeerOrderEventEnum.VALIDATE_ORDER)
                        .action(validatedOrderAction)
                    .and()
                    .withExternal()
                        .source(BeerOrderStatusEnum.VALIDATED_PENDING)
                        .target(BeerOrderStatusEnum.VALIDATED)
                        .event(BeerOrderEventEnum.VALIDATION_PASSED)
                    .and()
                    .withExternal()
                        .source(BeerOrderStatusEnum.VALIDATED_PENDING)
                        .target(BeerOrderStatusEnum.VALIDATION_EXCEPTION)
                        .event(BeerOrderEventEnum.VALIDATION_FAILED)
                    .and()
                    .withExternal()
                        .source(BeerOrderStatusEnum.VALIDATED)
                        .target(BeerOrderStatusEnum.ALLOCATION_PENDING)
                        .event(BeerOrderEventEnum.ALLOCATE_ORDER)
                        .action(allocateOrderAction)
                    .and()
                    .withExternal()
                        .source(BeerOrderStatusEnum.ALLOCATION_PENDING)
                        .target(BeerOrderStatusEnum.ALLOCATED)
                        .event(BeerOrderEventEnum.ALLOCATION_SUCCESS)
                    .and()
                    .withExternal()
                        .source(BeerOrderStatusEnum.ALLOCATION_PENDING)
                        .target(BeerOrderStatusEnum.ALLOCATION_EXCEPTION)
                        .event(BeerOrderEventEnum.ALLOCATION_FAILED)
                        .action(allocationFailureAction)
                    .and()
                    .withExternal()
                        .source(BeerOrderStatusEnum.ALLOCATION_PENDING)
                        .target(BeerOrderStatusEnum.PENDING_INVENTORY)
                    .   event(BeerOrderEventEnum.ALLOCATION_NO_INVENTORY)
                    .and()
                    .withExternal()
                        .source(BeerOrderStatusEnum.ALLOCATED)
                        .target(BeerOrderStatusEnum.PICKED_UP)
                        .event(BeerOrderEventEnum.BEER_ORDER_PICKED_UP);
    }

    @Override
    public void configure(StateMachineConfigBuilder<BeerOrderStatusEnum, BeerOrderEventEnum> config) throws Exception {

        StateMachineListenerAdapter<BeerOrderStatusEnum, BeerOrderEventEnum> adapter = new StateMachineListenerAdapter<>(){
            @Override
            public void stateChanged(State<BeerOrderStatusEnum, BeerOrderEventEnum> from, State<BeerOrderStatusEnum, BeerOrderEventEnum> to) {
                log.info(String.format("state changed( from: %s, to %s)", from, to));
            }
        };
    }
}
