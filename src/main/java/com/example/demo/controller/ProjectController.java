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

import com.example.demo.dto.FeedBackProjectDto;
import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.MessageProjectDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.Token;
import com.example.demo.dto.Web.CreateProjectDto;
import com.example.demo.dto.Web.CreateProjectFeedbackDto;
import com.example.demo.dto.Web.CreateProjectMessageDto;
import com.example.demo.dto.Web.MessageDto;
import com.example.demo.dto.Web.UserProjectDto;
import com.example.demo.services.ProjectFeedbackService;
import com.example.demo.services.ProjectMessageService;
import com.example.demo.services.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectMessageService projectMessageService;

    @Autowired
    ProjectFeedbackService projectFeedbackService;
    
    @PostMapping
    public ResponseEntity<MessageDto> create(@RequestAttribute("token") Token token, @RequestBody CreateProjectDto data){
        var res = projectService.create(data.name(), data.description(), data.startDate(), data.endDate(), data.idCategory(), token.getId());
        if(res == null)
            return new ResponseEntity<>(new MessageDto("Erro ao criar projeto"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new MessageDto("Projeto criado com sucesso"), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<MessageDto> addUser(@RequestAttribute("token") Token token, @RequestBody UserProjectDto data){
        var res = projectService.addUser(token.getId(), data.idProject(), data.idUser());
        return new ResponseEntity<>(new MessageDto(res.response()), res.success() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/user")
    public ResponseEntity<MessageDto> deleteUser(@RequestAttribute("token") Token token, @RequestBody UserProjectDto data){
        var res = projectService.deleteUser(token.getId(), data.idProject(), data.idUser());
        return new ResponseEntity<>(new MessageDto(res.response()), res.success() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/others")
    public ResponseEntity<ListPageDto<ProjectDto>> getAll(@RequestAttribute("token") Token token, @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size,@RequestParam(defaultValue = "") String query){
        var res = projectService.getAllPublic(token.getId(), page, size, query);
        if(res == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<ListPageDto<ProjectDto>> getAllMy(@RequestAttribute("token") Token token, @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size,@RequestParam(defaultValue = "") String query){
        var res = projectService.getAllMy(token.getId(), page, size, query);
        if(res == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getById(@PathVariable Long id){
        var res = projectService.getById(id);
        if(res == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ListPageDto<ProjectDto>> getByUser(@PathVariable Long id, @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size){
        var res = projectService.getByUser(id, page, size);
        if(res == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @PostMapping("/message")
    public ResponseEntity<MessageDto> createMessage(@RequestAttribute("token") Token token, @RequestBody CreateProjectMessageDto data){
        var res = projectMessageService.createMessage(data.idProject(), token.getId(), data.description(), data.timestamp());
        if(res == null)
            return new ResponseEntity<>(new MessageDto("Erro ao criar mensagem"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new MessageDto("Mensagem criada com sucesso"), HttpStatus.OK);
    }

    @GetMapping("/{id}/message")
    public ResponseEntity<ListPageDto<MessageProjectDto>> getAllMessage(@PathVariable Long id, @RequestAttribute("token") Token token, @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size){
        var res = projectMessageService.getAllMessage(id, token.getId(), page, size);
        if(res == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @PostMapping("/feedback")
    public ResponseEntity<MessageDto> createFeedback(@RequestAttribute("token") Token token, @RequestBody CreateProjectFeedbackDto data){
        var res = projectFeedbackService.createFeedback(data.idProject(), token.getId(), data.description(), data.isPrivate());
        if(res == null)
            return new ResponseEntity<>(new MessageDto("Erro ao criar feedback"), HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity<>(new MessageDto("Feedback criado com sucesso"), HttpStatus.OK);
    }

    @GetMapping("/{idProject}/feedback/{idUser}")
    public ResponseEntity<ListPageDto<FeedBackProjectDto>> getFeedbackByUser(@PathVariable Long idProject, @PathVariable Long idUser, @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size){
        var res = projectFeedbackService.getFeedbackByUser(idProject, idUser, page, size);
        if(res == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{idProject}/feedback")
    public ResponseEntity<ListPageDto<FeedBackProjectDto>> getAllFeedback(@PathVariable Long idProject, @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size){
        var res = projectFeedbackService.getAllFeedback(idProject, page, size);
        if(res == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
