package brewery.model.events;

import brewery.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mesar on 2/27/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidatedOrderRequest {

    private BeerOrderDto beerOrder;
}
