package co.zw.santech.orderservice.controllers;

import co.zw.santech.orderservice.dto.OrderDTO;
import co.zw.santech.orderservice.models.Order;
import co.zw.santech.orderservice.response.ErrorMessageResponse;
import co.zw.santech.orderservice.routes.OrderMessageProducer;
import co.zw.santech.orderservice.services.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Slf4j
@RestController
@Api(name = "orders service", description = "Methods for managing orders", visibility = ApiVisibility.PUBLIC, stage = ApiStage.RC, group = "orders")
@ApiVersion(since = "1.0", until = "1.5")
@RequestMapping("/order")
public class OrdersController {

    DateFormat df = new SimpleDateFormat("yyyyMMdd");
    String date = df.format(new Date());

    private final
    OrdersService ordersService;
    private final
    OrderMessageProducer orderPayload;
    private final
    ModelMapper modelMapper;

    @Autowired
    public OrdersController(OrdersService ordersService,
                            OrderMessageProducer orderPayload,
                            ModelMapper modelMapper) {
        this.ordersService = ordersService;
        this.orderPayload = orderPayload;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ApiMethod(description = "Method to Save an order")
    @ApiHeaders(headers = {
            @ApiHeader(name = ACCEPT),
            @ApiHeader(name = CONTENT_TYPE)
    })
    @ResponseStatus(HttpStatus.OK)
    @ApiResponseObject
    public ResponseEntity<?> saveOrder(@RequestBody OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        order.setDate(date);

        if (ordersService.saveOrder(order) == 200) {
            log.info("order created successfully");
            this.orderPayload.dressOrderPayload(order);
            return ResponseEntity.ok(modelMapper.map(order, Order.class));
        } else {
            return new ResponseEntity<>(new ErrorMessageResponse(500, "Order Creation failed!",
                    "Delivery Option Not Supported", "/orders"), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/findOrders", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiMethod(description = "Method to List all orders")
    @ApiHeaders(headers = {
            @ApiHeader(name = ACCEPT),
            @ApiHeader(name = CONTENT_TYPE)
    })
    @ResponseStatus(HttpStatus.OK)
    @ApiResponseObject
    public List<Order> findOrders() {
        return this.ordersService.findOrders();
    }
}
