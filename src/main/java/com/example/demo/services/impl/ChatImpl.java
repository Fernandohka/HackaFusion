package com.example.demo.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.Web.ChatDto;
import com.example.demo.dto.Web.MessageDtoPriv;
import com.example.demo.model.Chat;
import com.example.demo.model.MessageChat;
import com.example.demo.repositories.ChatRepo;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.ChatService;
import com.example.demo.services.ImageStorageService;

public class ChatImpl implements ChatService{

    @Autowired
    ChatRepo repo;

    @Autowired
    UserRepo repoUser;

    @Autowired
    ImageStorageService imageServ;

    @Override
    public Chat create(Long idUserA, Long idUserB) {
        var userOP1 = repoUser.findById(idUserA);
        var userOP2 = repoUser.findById(idUserB);

        if(!userOP1.isPresent() || !userOP2.isPresent())
            return null;

        var newChat = new Chat();
        newChat.setUserA(userOP1.get());
        newChat.setUserB(userOP2.get());

        return repo.save(newChat);
    }


    @Override
    public ResponseDto createMessage(Long idUser, Long idChat, String description) {
        var userOp = repoUser.findById(idUser);
        var chatOp = repo.findById(idChat);

        if(!chatOp.isPresent() || !userOp.isPresent())
            return new ResponseDto(false, "Usuario ou chat não encontrado");
        
        var user = userOp.get();
        var chat = chatOp.get();

        if(!user.getChats().contains(chat))
            return new ResponseDto(false, "Usuario Não participa deste chat");

        var newMessage = new MessageChat();
        newMessage.setChat(chat);
        newMessage.setDescription(description);
        newMessage.setUser(user);
        newMessage.setTimestamp(LocalDateTime.now());

        if (!chat.getMessages().add(newMessage))
            return new ResponseDto(false, "Erro ao adicionar nova mensagem");

        repo.save(chat);
        return new ResponseDto(true, "Mensagem adicionada com sucesso");
    }

    @Override
    public ListPageDto<MessageDtoPriv> getMessage(Long idUser, Long idChat) {
        var userOp = repoUser.findById(idUser);
        var chatOP = repo.findById(idChat);

        if(!chatOP.isPresent() || !userOp.isPresent())
            return null;
        
        var user = userOp.get();
        var chat = chatOP.get();

        if(!user.getChats().contains(chat))
            return null;

        var listMessage = new ArrayList<>(chat.getMessages());
        var newList = new ArrayList<MessageDtoPriv>();

        for (MessageChat message : listMessage) {
            var currUser = message.getUser();
            newList.add(
                new MessageDtoPriv(
                    new UserDto(currUser.getId(), currUser.getName(), currUser.getEdv(), currUser.getEmail(), currUser.getNumber(), imageServ.toUrl(currUser.getImage()), currUser.getEts(), currUser.isAdmin()),
                    message.getDescription(), 
                    message.getTimestamp()
                )
            );
        }

        return new ListPageDto<>(0, newList);

    }

    @Override
    public ListPageDto<ChatDto> getAllByUser(Long iduser, String query) {
        var userOp = repoUser.findById(iduser);
        if(!userOp.isPresent())
            return null;
        var user = userOp.get();

        var quetyIsPresent = false;

        if(query!="" && query!=null){
            quetyIsPresent = true;
        }

        var listChat = new ArrayList<>(user.getChats());
        var newList  = new ArrayList<ChatDto>();



        for (Chat  chatPriv : listChat) {
            var receiver = chatPriv.getUserA().getId()!=user.getId()? chatPriv.getUserA():chatPriv.getUserB();
            if(quetyIsPresent && !receiver.getName().contains(query))
                continue;
        
            newList.add(
                new ChatDto(
                    new UserDto(receiver.getId(), receiver.getName(), receiver.getEdv(), receiver.getEmail(), receiver.getNumber(), imageServ.toUrl(receiver.getImage()), receiver.getEts(), receiver.isAdmin()), 
                    chatPriv.getId()
                )
            );
        }

        return new ListPageDto<>(0, newList);

    }
    
}
