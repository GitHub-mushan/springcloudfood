package com.mushan.repository;

import com.mushan.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository {
    public List<Menu> findAll(int index,int limit);
    public Menu findById(long id);
    public int count();
    public void save(Menu menu);
    public void update(Menu menu);
    public void deleteById(long id);
}
