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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.Token;
import com.example.demo.dto.Web.AbilityDto;
import com.example.demo.dto.Web.CarrerDto;
import com.example.demo.dto.Web.MessageDto;
import com.example.demo.services.CareerService;

@RestController
public class CareerController {
    @Autowired
    CareerService service;

    @GetMapping("/career")
    public ResponseEntity<ListPageDto<CarrerDto>> getAbilitys(@RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "0") Integer size,@RequestParam(defaultValue = "") String query){
        try {
            return new ResponseEntity<>(service.getAll(page, size, query),HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/career/user/{id}")
    public ResponseEntity<ListPageDto<CarrerDto>> getAbilitys(@PathVariable Long id ,@RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "0") Integer size,@RequestParam(defaultValue = "") String query){
        try {

            var career = service.getAllByUser(id,page, size);

            if(career==null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(career,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admin/career")
    public ResponseEntity<MessageDto> createAbility(@RequestAttribute("token") Token token,@RequestBody AbilityDto data){

        if(!token.isAdmin()){
            return new ResponseEntity<>(new MessageDto("Usuario sem autorização"),HttpStatus.UNAUTHORIZED);
        }

        var ability = service.create(data.name());

        if(ability==null){
            return new ResponseEntity<>(new MessageDto("Erro ao adicionar habilidade"),HttpStatus.BAD_REQUEST);
        } 
        return new ResponseEntity<>(new MessageDto("Criado com sucesso!!"),HttpStatus.OK);
    }

    @DeleteMapping("/admin/career/{id}")
    public ResponseEntity<MessageDto> deleteAbility(@RequestAttribute("token") Token token,@PathVariable Long id){

        if(!token.isAdmin()){
            return new ResponseEntity<>(new MessageDto("Usuario sem autorização"),HttpStatus.UNAUTHORIZED);
        }

        var res = service.delete(id);

        if(!res.success())
            return new ResponseEntity<>(new MessageDto(res.response()),HttpStatus.BAD_REQUEST);
            
        return new ResponseEntity<>(new MessageDto("Deletado com sucesso!!"),HttpStatus.OK);
    }

    @PostMapping("/career/user/{id}")
    public ResponseEntity<MessageDto> addUserAbility(@RequestAttribute("token") Token token,@PathVariable Long id){

        var res = service.addCareer(token.getId(), id);

        if(!res.success())
            return new ResponseEntity<>(new MessageDto(res.response()),HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new MessageDto("Carreira adicionado com  sucesso!!"),HttpStatus.OK);
    }

    @DeleteMapping("/career/user/{id}")
    public ResponseEntity<MessageDto> removeUserAbility(@RequestAttribute("token") Token token,@PathVariable Long id){

        var res = service.deleteCareer(token.getId(), id);

        if(!res.success())
            return new ResponseEntity<>(new MessageDto(res.response()),HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new MessageDto("Carreira removida com  sucesso!!"),HttpStatus.OK);
    }


}
