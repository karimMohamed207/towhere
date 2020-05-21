package com.karim.controller;

import com.karim.model.Order;
import com.karim.model.User;
import com.karim.services.OrderService;
import com.karim.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService ;
    @Autowired
    private UserServices userServices ;

//-------------------------- getAllOrders -------------------------------
    @GetMapping("orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<Order>>(orders , HttpStatus.OK);
    }

//--------------------------------- findOrderByCode ---------------------------
    @GetMapping("/orders/{email}/{code}")
    public ResponseEntity<Order> getOrder(@PathVariable long code , @PathVariable String email){
        Order order = orderService.findByCode(code);
        User user = userServices.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        if (order == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<Order>(order , HttpStatus.OK);
    }

//---------------------------------- get Orders For User -----------------------
    @GetMapping("orders/{email}")
    public ResponseEntity<List<Order>> getOrder(@PathVariable String email) {
        User user = userServices.findByEmail(email);
        List<Order> orders = orderService.findByUser(user);
        if (orders.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<Order>>(orders , HttpStatus.OK);
    }

//----------------------------- add new Order ---------------------------------
    @PostMapping("/orders/{email}")
    public ResponseEntity<Order> getOrder(@RequestBody Order order , @PathVariable String email){
        User user = userServices.findByEmail(email);
        if(order == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        order.setUser(user);
        orderService.SaveOrder(order);
        return new ResponseEntity<Order>(order , HttpStatus.OK);
    }


}
