/*
package co.zw.santech.orderservice.routes;

import co.zw.santech.orderservice.models.Order;
import co.zw.santech.orderservice.routes.DressOrderPayload;
import com.google.gson.Gson;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class MessageProducer extends RouteBuilder {

    @Autowired
    DressOrderPayload orderPayload;

    @Override
    public void configure() throws Exception {

        from("timer:active-mq-timer?period=10000")
        //from("direct:start")
                // .transform().body((o, stringObjectMap) -> dressOrder)
                .bean(orderPayload, "toJson")
            //   .log("${body}")
              //   .transform().constant("Hello from Apache Camel!!!")

                .to("activemq:queue:orders.queue");
    }
}
 */
