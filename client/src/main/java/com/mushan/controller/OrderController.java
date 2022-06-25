package com.mushan.controller;

import com.mushan.entity.Menu;
import com.mushan.entity.Order;
import com.mushan.entity.OrderVO;
import com.mushan.entity.User;
import com.mushan.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderFeign orderFeign;

    @GetMapping("/save/{mid}")
    public String save(@PathVariable("mid") long mid, HttpSession session){
        User user = (User) session.getAttribute("user");
        Order order = new Order();
        order.setUser(user);
        Menu menu = new Menu();
        menu.setId(mid);
        order.setMenu(menu);
        orderFeign.save(order);
        return "order";
    }

    @GetMapping("/findAllByUid")
    @ResponseBody
    public OrderVO findAllByUid(@RequestParam("page") int page,@RequestParam("limit") int limit,HttpSession session){
        int index = (page-1)*limit;
        User user = (User) session.getAttribute("user");
        return orderFeign.findAllByUid(index,limit,user.getId());
    }

    @GetMapping("/findAll")
    @ResponseBody
    public OrderVO findAll(@RequestParam("page") int page,@RequestParam("limit") int limit){
        int index = (page-1)*limit;
        return orderFeign.findAll(index,limit);
    }

    @GetMapping("/updateState/{id}")
    public String updateState(@PathVariable("id") long id){
        orderFeign.updateState(id);
        return "redirect:/menu/redirect/order_controller";
    }
}
