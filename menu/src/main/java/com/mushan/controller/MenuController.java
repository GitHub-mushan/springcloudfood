package com.mushan.controller;

import com.mushan.entity.Menu;
import com.mushan.entity.MenuVO;
import com.mushan.entity.Type;
import com.mushan.repository.MenuRepository;
import com.mushan.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public String index(){
        return this.port;
    }

    @GetMapping("/findAll/{index}/{limit}")
    public MenuVO findAll(@PathVariable("index") int index,@PathVariable("limit") int limit){
        List<Menu> menuList= menuRepository.findAll(index,limit);
        return new MenuVO(0,"",menuRepository.count(),menuList);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        menuRepository.deleteById(id);
    }

    @GetMapping("/findTypes")
    public List<Type> findTypes(){
        return typeRepository.findAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody Menu menu){
        menuRepository.save(menu);
    }

    @GetMapping("/findById/{id}")
    public Menu findById(@PathVariable("id") long id){
        return menuRepository.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Menu menu){
        menuRepository.update(menu);
    }

}
