package co.zw.santech.orderservice.routes;

import co.zw.santech.orderservice.models.Order;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderMessageProducer {

    private final
    ProducerTemplate producerTemplate;

    private final
    Gson gson = new Gson();

    @Autowired
    OrderMessageProducer(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    public void dressOrderPayload(Order order) {
        try {
            producerTemplate.sendBody("activemq:queue:orders.queue", gson.toJson(order));
            log.info("Message sent to queue successfully {}", order);
        } catch (Exception e) {
            log.error("Could not send Payload to Queue! {}", e.getMessage());
        }
    }
}
