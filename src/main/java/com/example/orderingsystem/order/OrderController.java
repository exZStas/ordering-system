package com.example.orderingsystem.order;

import com.example.orderingsystem.constants.OrderStatus;
import com.example.orderingsystem.order.dto.OrderDto;
import com.example.orderingsystem.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/orders")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addOrder(@RequestBody OrderDto orderDto) {
        Order order = orderService.createOrder(orderDto.getName(), orderDto.getDescription(), orderDto.getProductId());
        //todo converty to dto
        return new ResponseEntity(order, HttpStatus.OK);
    }

    //todo ideally return 303 HTTP code or use PATCH for partial update
    @PostMapping("/{orderId}/status")
    public ResponseEntity updateOrderStatus(@PathVariable("orderId") Long orderId, @RequestBody OrderStatus orderStatus) {
        orderService.updateOrderStatus(orderId, orderStatus);
        return new ResponseEntity(HttpStatus.OK);
    }

    //todo implement GET method to retrieve status for specific order

    //todo implement GET method to retrieve info regarding order and it's content


}
