package com.example.User.Controller;


import com.example.User.Entity.User;
import com.example.User.Repository.UserRepository;
import com.example.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getOrders(Model model) {
        List<User> orders = userService.getAllUser();
        model.addAttribute("orders", orders);
        model.addAttribute("order", new User());
        return "order";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute User order) {
        userService.createUser(order);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/orders";
    }

    @PostMapping("/update")
    public String updateOrder(@ModelAttribute User order) {
        userService.update(order);
        return "redirect:/orders";
    }
}
