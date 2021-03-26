package com.example.orderingsystem.order;

import com.example.orderingsystem.constants.OrderStatus;
import com.example.orderingsystem.order.model.Order;
import com.example.orderingsystem.order.repository.OrderRepository;
import com.example.orderingsystem.product.ProductService;
import com.example.orderingsystem.product.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    public Order createOrder(String name, String description, Long productId) {
        Assert.hasLength(name, "Order name can't be null or empty");
        Assert.notNull(productId, "Product id can't be null");

        Product product = productService.getProductById(productId);
        Assert.notNull(product, format("Can't find product by id: [%s]", productId));

        Long currentProductStock = product.getStock();
        //todo we can throw some specific exception and handle nicely with ControllerAdvice where we can map exception on some particular http status
        Assert.isTrue(currentProductStock > 0, String.format("Product with id: [%s] is out of stock", productId));

        Order order = new Order();
        order.setName(name);
        order.setDescription(description);
        order.setProductId(productId);
        order.setOrderStatus(OrderStatus.NEW);
        order.setCreationDate(new Date());

        //todo store order in db. Next steps could be placing order in queue (kafka for instance)
        // for further business actions (notify customer about order, notify other business units about order etc)
        return orderRepository.save(order);
    }

    public Order getOrderById(Long orderId) {
        Assert.notNull(orderId, "Order id can't be null");

        return orderRepository.findById(orderId).orElse(null);
    }

    //todo need to discuss if we want to return any value to a system which triggered order status change

    public void updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        Assert.notNull(orderId, "Order id can't be null");
        Assert.notNull(orderStatus, "Order status can't be null");

        Order order = getOrderById(orderId);
        if(Objects.isNull(order)) {
            // todo cancel order/notify service desk ?
            //  discuss with analyst/ PO what to do :)
            log.error("Can't update order since it wasn't found!");
        }
        Assert.notNull(order, format("Can't find order by id: [%s]", orderId));

        if(orderStatus == OrderStatus.DELIVERED) {
            Long productId = order.getProductId();
            productService.withdrawProduct(productId);
        }

        //todo process other order statuses(cancelled, running) accordingly. Need to discuss with analyst/PO

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
    }

    // todo better extract and create ful-fledged spring batch job for better monitoring and control
    @Scheduled(cron = "*/5 * * * *")
    public void processOrder() {
        //todo collect all orders with status == DELIVERED
        // notify user and other business units order was processed
        // set for those orders status PROCESSED
    }

}
