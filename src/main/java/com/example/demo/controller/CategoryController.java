package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.Token;
import com.example.demo.dto.Web.CreateCategoryDto;
import com.example.demo.dto.Web.MessageDto;
import com.example.demo.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<MessageDto> create(@RequestAttribute("token") Token token, @RequestBody CreateCategoryDto data){
        if(!token.isAdmin())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        var res = categoryService.create(data.name());
        if(res == null)
            return new ResponseEntity<>(new MessageDto("Erro ao criar categoria"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new MessageDto("Categoria criada com sucesso"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ListPageDto<CategoryDto>> getAll(@RequestAttribute("token") Token token, @RequestParam(defaultValue = "") String query){
        var res = categoryService.getAll(query);
        if(res == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@RequestAttribute("token") Token token, @PathVariable Long id){
        if(!token.isAdmin())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        
        var res = categoryService.delete(id);
        return new ResponseEntity<>(new MessageDto(res.response()), res.success() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
