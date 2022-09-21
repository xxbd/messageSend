package com.huaao.message;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("web")
public class WebController {


    @RequestMapping("index")
    public String index(Model model, HashMap<String,Object> map){

        model.addAttribute("value1","欢迎欢迎");
        map.put("value2","欢迎进入html页面");
        return "index";

    }
}
