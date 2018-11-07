package com.zl.example.springboot.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author zlCalma
 * @date 2018/11/4 14:32.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
   private GirlProperties girlProperties;

    //@RequestMapping(value = {"/say"},method = RequestMethod.GET)
    @GetMapping(value="/say")
    public String say(@RequestParam(value = "id",required = false,defaultValue = "0") Integer myId){
      //return girlProperties.getCupSize();
        return "id:" + myId;
    }
}
