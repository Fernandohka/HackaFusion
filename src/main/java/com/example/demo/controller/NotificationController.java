package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.NotificationDto;
import com.example.demo.dto.Token;
import com.example.demo.services.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping
    public ResponseEntity<ListPageDto<NotificationDto>> getAll(@RequestAttribute("token") Token token, @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size){
        var res = notificationService.getAll(token.getId(), page, size);
        if(res == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
