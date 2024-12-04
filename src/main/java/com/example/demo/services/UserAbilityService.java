package com.example.demo.services;

public interface UserAbilityService {
    public AbilityUser addAbility(long idUser, long idAbility);
    public ResponseDto deleteAbility(long idUser, long idAbility);
}
