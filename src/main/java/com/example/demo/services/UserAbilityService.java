package com.example.demo.services;

public interface UserAbilityService {
    public AbilityUser addAbility(Long idUser, Long idAbility);
    public ResponseDto deleteAbility(Long idUser, Long idAbility);
}
