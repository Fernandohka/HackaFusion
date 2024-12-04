package com.example.demo.services;
import com.example.demo.dto.ResponseDto;

public interface UserAbilityService {
    public AbilityUser addAbility(Long idUser, Long idAbility);
    public ResponseDto deleteAbility(Long idUser, Long idAbility);
}
