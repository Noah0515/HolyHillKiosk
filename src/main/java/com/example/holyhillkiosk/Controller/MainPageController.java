package com.example.holyhillkiosk.Controller;

import com.example.holyhillkiosk.DTO.OrderedBeverageDTO;
import com.example.holyhillkiosk.DTO.OrderedFoodDTO;
import com.example.holyhillkiosk.Entity.*;
import com.example.holyhillkiosk.Service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {
    private OrderService orderService;
    @GetMapping("/customer/orderPage")
    public String getCustomerOrder(){
        return "order/customer-order";
    }
    @GetMapping("/customer/order-complete")
    public String showCustomerOrderCompletePage(@RequestParam String foodOrderId, @RequestParam String beverageOrderId, Model model){
        model.addAttribute("foodOrderId", foodOrderId);
        model.addAttribute("beverageOrderId", beverageOrderId);
        return "order/customer-order-complete";
    }
    @GetMapping("/staff/orderPage")
    public String getStaffOrder(){
        return "order/staff-order";
    }
    @GetMapping("/staff/order-complete")
    public String showStaffOrderCompletePage(@RequestParam String foodOrderId, @RequestParam String beverageOrderId, Model model){
        model.addAttribute("foodOrderId", foodOrderId);
        model.addAttribute("beverageOrderId", beverageOrderId);
        return "order/staff-order-complete";
    }

    @GetMapping("/staff/websocket-test")
    public String getWebSocketTest(){
        return "process/websocket-test";
    }

    @GetMapping("/staff/processFood")
    public String getProcessFood(){
        return "process/staff-process-food";
    }
    @GetMapping("/staff/processBeverage")
    public String getProcessBeverage(){
        return "process/staff-process-beverage";
    }

}
