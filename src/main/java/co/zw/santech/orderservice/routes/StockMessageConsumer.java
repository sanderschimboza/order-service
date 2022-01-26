package co.zw.santech.orderservice.routes;

import co.zw.santech.orderservice.models.Order;
import co.zw.santech.orderservice.services.OrdersService;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class StockMessageConsumer extends RouteBuilder {

    private final
    OrdersService ordersService;

    @Autowired
    public StockMessageConsumer(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Override
    public void configure() {
        from("activemq:queue:stock.queue")
                .unmarshal(new JacksonDataFormat(Order.class))
                .process(exchange -> {
                    Order order = exchange.getIn().getBody(Order.class);
                    log.info("Received Order Update Payload {}", order);
                    if (ordersService.processStockOrder(order) != null) {
                     }
                    exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE,
                            HttpStatus.ACCEPTED);
                });
    }
}
