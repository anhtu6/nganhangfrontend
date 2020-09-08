package com.kits.frontend2.controller;

import  com.kits.frontend2.model.Khachhang;
import com.sun.org.apache.xpath.internal.operations.Mod;
import jdk.nashorn.internal.parser.JSONParser;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/create-khachhang")
    public String taoKhachhang(Model model){
        Khachhang khachhang = rest.getForObject("http://localhost:8080/khachhang/create-khachhang",Khachhang.class);
        model.addAttribute("khachhang",khachhang);
        return "taoTaikhoanKhach";

    }
    @PostMapping("create-khachhang")
    public String hoanthanhTao(Khachhang khachhang,Model model){

        rest.postForObject("http://localhost:8080/khachhang/create-khachhang", khachhang, Khachhang.class);
        List<Khachhang> khachhangs =
                Arrays.asList( rest.getForObject("http://localhost:8080/khachhang/list",Khachhang[].class));
        //testing


        model.addAttribute("khachhangs",khachhangs);
        return "showKhachhang";

    }
    @GetMapping("/edit-khachhang/{id}")
    public String suaKhachhang(@PathVariable("id") String id, Model model){


        id = id.replace(".json","");


        Khachhang khachhang = rest.getForObject(  "http://localhost:8080/khachhang/edit-khachhang/"+id,Khachhang.class);
        model.addAttribute("khachhang",khachhang);
        return "suaKhachhang";

    }
    @PostMapping("/edit-khachhang")
    public String hoanthanhEdit(Khachhang khachhang,Model model){

        rest.postForObject("http://localhost:8080/khachhang/edit-khachhang", khachhang, Khachhang.class);
        List<Khachhang> khachhangs =
                Arrays.asList( rest.getForObject("http://localhost:8080/khachhang/list",Khachhang[].class));

        model.addAttribute("khachhangs",khachhangs);
        return "showKhachhang";

    }
    @GetMapping("/xoa-khachhang/{id}")
    public String xoaKhachhang(@PathVariable("id") String id, Model model){


        id = id.replace(".json","");


        List<Khachhang> khachhangs = Arrays.asList(rest.getForObject(  "http://localhost:8080/khachhang/xoa-khachhang/"+id,Khachhang[].class));
        model.addAttribute("khachhangs",khachhangs); //dat ten phai chuan, khachhang thieu S ko nhan
        return "showKhachhang";

    }

    @PostMapping("search-khachhang")
    public String showSearchResultPost(@RequestParam("searchKhachhang") String searchKhachhang, Model model, Khachhang khachhang){
        System.out.println("logging "+searchKhachhang);

        Khachhang[] khachhangs = rest.getForObject("http://localhost:8080/khachhang/search-khachhang/"+searchKhachhang,Khachhang[].class);


        //tam thoi chua chay duoc theo post
       // Khachhang[] khachhangs= rest.postForObject("http://localhost:8080/khachhang/search-khachhang/",searchKhachhang,Khachhang[].class);

        model.addAttribute("khachhangs",Arrays.asList(khachhangs));


        System.out.println("end logic");
        return "showKhachhang";

    }

}
