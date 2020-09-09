package com.kits.frontend2.controller;

import com.kits.frontend2.model.Nhanvien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/nhanvien")
public class NhanvienController {
    private RestTemplate rest = new RestTemplate();
    @GetMapping("/read")
    public String showAllNhanvien(Model model){
        List<Nhanvien> nhanviens =
                Arrays.asList( rest.getForObject("http://localhost:8080/nhanvien/read",Nhanvien[].class));
        model.addAttribute("nhanviens",nhanviens);
        return "showNhanvien";
    }
    @GetMapping("/create")
    public String taoNhanvien(Model model){
        Nhanvien nhanvien =  rest.getForObject("http://localhost:8080/nhanvien/create",Nhanvien.class);
        model.addAttribute("nhanvien",nhanvien);
        return "taoNhanvien";
    }
    @PostMapping("/create")
    public RedirectView showNhanvienAfterCreated(Nhanvien nhanvien){
        rest.postForObject("http://localhost:8080/nhanvien/create",nhanvien,Nhanvien.class);
        List<Nhanvien> nhanviens = Arrays.asList(rest.getForObject("http://localhost:8080/nhanvien/read",Nhanvien[].class));
        ModelAndView modelAndView = new ModelAndView("showNhanvien");
        RedirectView rv = new RedirectView();
        rv.setContextRelative(true);
        rv.setUrl("/nhanvien/read");
        return rv;
    }


    @GetMapping("/put/{id}")
    public String showEditNhanvienForm(@PathVariable String id,Model model){
        id = id.replace(".json","");
        Nhanvien nhanvien = rest.getForObject( "http://localhost:8080/nhanvien/put/"+id,Nhanvien.class);
        model.addAttribute("nhanvien",nhanvien);
        return "suaNhanvien";
    }
    @PostMapping("/put")
    public RedirectView showNhanvienAfterEdited(Nhanvien nhanvien,Model model){
        rest.postForObject("http://localhost:8080/nhanvien/put",nhanvien,Nhanvien.class);
        List<Nhanvien> nhanviens =
                Arrays.asList( rest.getForObject("http://localhost:8080/nhanvien/read",Nhanvien[].class));
        ModelAndView modelAndView = new ModelAndView("showNhanvien");
        RedirectView rv = new RedirectView();
        rv.setContextRelative(true);
        rv.setUrl("/nhanvien/read");
        return rv;
    }
    @GetMapping("/delete/{id}")
    public RedirectView deleteNhanvien(@PathVariable String id){
        id = id.replace(".json","");
        List<Nhanvien> nhanviens = Arrays.asList(rest.getForObject(  "http://localhost:8080/nhanvien/delete/"+id,Nhanvien[].class));
        ModelAndView modelAndView = new ModelAndView("showNhanvien");
        modelAndView.addObject("nhanviens",nhanviens);
        RedirectView rv = new RedirectView();
        rv.setContextRelative(true);
        rv.setUrl("/nhanvien/read");
        return rv;
    }
    @PostMapping("/search")
    public String showSearchResult(@RequestParam String searchName,Model model){
        List<Nhanvien> nhanviens = Arrays.asList(rest.getForObject("http://localhost:8080/nhanvien/search/"+searchName,Nhanvien[].class));
        model.addAttribute("nhanviens",nhanviens);
        return "showNhanvien";
    }

}
