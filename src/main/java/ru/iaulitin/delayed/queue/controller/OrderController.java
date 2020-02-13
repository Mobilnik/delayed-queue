package ru.iaulitin.delayed.queue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.iaulitin.delayed.queue.service.order.OrderService;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("create")
    public void create(@RequestParam("delay") long delayMillis) {
        orderService.createOrder(delayMillis);
    }
}
