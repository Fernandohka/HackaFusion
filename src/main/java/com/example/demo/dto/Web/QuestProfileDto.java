package com.example.demo.dto.Web;

import com.example.demo.dto.ForumDto;
import com.example.demo.dto.UserDto;

public record QuestProfileDto(
    UserDto user,
    Long idQuestion,
    String title,
    String description,
    ForumDto forum
) {}
