package com.example.demo.services;

import java.util.List;

public interface ChatService {
    public Chat post(Long idUserA, Long idUserB);
    public List<Chat> getAllByUser(Long iduser);
}
