package com.sheryians.major.controller;

import com.sheryians.major.global.GlobalData;
import com.sheryians.major.model.Product;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController
{
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @GetMapping({"/","/home"})
    public String home(Model model){

        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());

        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.getAllProduct());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());

        return "shop";
    }
    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("products",productService.getProductByCategory(id));
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable int id){
        model.addAttribute("product",productService.getProductById(id).get());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());

        return "viewProduct";
    }

}
