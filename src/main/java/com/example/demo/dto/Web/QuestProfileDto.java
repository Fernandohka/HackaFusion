package com.example.demo.dto.Web;

import com.example.demo.dto.ForumDto;
import com.example.demo.dto.UserDto;

public record QuestProfileDto(
    UserDto user,
    Long idQiestion,
    String title,
    String descriptio,
    ForumDto forum
) {}
