package com.example.demo.services.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Category;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.CategoryService;

public class CategoryImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepo;

    @Override
    public CategoryDto create(String name) {
        if(name == null || name.equals(""))
            return null;
        
        Category category = new Category();
        category.setName(name);
        categoryRepo.save(category);

        return new CategoryDto(
            category.getId(), 
            category.getName()
            );
    }

    @Override
    public ListPageDto<CategoryDto> getAll(String query) {

        List<Category> listCategory;
        if(query == null || query.equals(""))
            listCategory = categoryRepo.findAll();
        else
            listCategory = categoryRepo.findByName(query);
        var newList = new ArrayList<CategoryDto>();

        Integer start = 0;
        Integer end = listCategory.size();
        Integer pages = (int)Math.floor(listCategory.size());

        for(int i=start;i<end;i++){
            var category = listCategory.get(i);
            newList.add(new CategoryDto(
                category.getId(), 
                category.getName()
                ));
        }

        return new ListPageDto<>(pages, newList);
    }

    @Override
    public ResponseDto delete(Long id) {
        try {
            categoryRepo.deleteById(id);
            return new ResponseDto(true, "Categoria deletado com sucesso");
        } catch (Exception e) {
            return new ResponseDto(false, "Erro ao deletar categoria");
        }
    }
}
