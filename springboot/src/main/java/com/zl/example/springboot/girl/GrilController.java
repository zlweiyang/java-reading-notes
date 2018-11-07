package com.zl.example.springboot.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zlCalma
 * @date 2018/11/4 15:56.
 */
@RestController
public class GrilController {

    @Autowired
    private GrilResponsitory girlReponsitory;



    /**
     * 查询女生列表
     */
    @GetMapping(value = "girls")
    public List<Girl>girlList(){

        return girlReponsitory.findAll();
    }

    /**
     * 添加一个女生
     * @param cupSize
     * @param age
     * @return
     */
    @PostMapping(value = "girls")
    public Girl girlAdd(@RequestParam("cupSize") String cupSize,@RequestParam("age") Integer age){

        Girl girl = new Girl();
        girl.setCupSize(cupSize);
        girl.setAge(age);

        return girlReponsitory.save(girl);
    }
    @GetMapping(value = "girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
        return  girlReponsitory.findOne(id);
    }

    @PutMapping(value = "girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,@RequestParam("cupSize")
            String cupSize,@RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setCupSize(cupSize);
        return girlReponsitory.save(girl);
    }

    @DeleteMapping(value = "girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id){
        girlReponsitory.delete(id);
    }
}
