package com.IDP.Dupphenferb.Controller;


import com.IDP.Dupphenferb.Entity.User;
import com.IDP.Dupphenferb.Repository.UserRepository;
import com.IDP.Dupphenferb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
