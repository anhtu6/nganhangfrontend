package com.kits.frontend2.controller;

import  com.kits.frontend2.model.Khachhang;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/khachhang")

public class KhachhangController {
    private RestTemplate rest = new RestTemplate();
    @GetMapping("/list")
    public String showKhachhang(Model model){
        List<Khachhang> khachhangs =
        Arrays.asList( rest.getForObject("http://localhost:8080/khachhang/list",Khachhang[].class));

        model.addAttribute("khachhangs",khachhangs);
        return "showKhachhang";
    }

}
