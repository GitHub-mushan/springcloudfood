package com.mushan.controller;
import com.mushan.entity.Order;
import com.mushan.entity.OrderVO;
import com.mushan.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/save")
    public void save(@RequestBody Order order){
        order.setDate(new Date());
        orderRepository.save(order);
    }

    @GetMapping("/findAllByUid/{index}/{limit}/{uid}")
    public OrderVO findAllByUid(@PathVariable("index") int index, @PathVariable("limit") int limit, @PathVariable("uid") long uid){
        OrderVO orderVO = new OrderVO();
        orderVO.setCode(0);
        orderVO.setMsg("");
        orderVO.setCount(orderRepository.countByUid(uid));
        orderVO.setData(orderRepository.findAllByUid(index, limit, uid));
        return orderVO;
    }

    @GetMapping("/countByUid/{uid}")
    public int countByUid(@PathVariable("uid") int uid){
        return orderRepository.countByUid(uid);
    }

    @GetMapping("/findAll/{index}/{limit}")
    public OrderVO findAll(@PathVariable("index") int index,@PathVariable("limit") int limit){
        OrderVO orderVO = new OrderVO();
        orderVO.setCode(0);
        orderVO.setMsg("");
        orderVO.setCount(orderRepository.count());
        orderVO.setData(orderRepository.findAll(index, limit));
        return orderVO;
    }

    @GetMapping("/updateState/{id}")
    public void updateState(@PathVariable("id") long id){
        orderRepository.updateState(id);
    }

}
