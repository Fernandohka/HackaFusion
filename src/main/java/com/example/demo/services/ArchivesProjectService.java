package com.example.demo.services;

import com.example.demo.dto.ArchivesProjectDto;
import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;

public interface ArchivesProjectService {
    public ArchivesProjectDto create(Long idUser, String name, String archive, Long idProject);
    public ListPageDto<ArchivesProjectDto> getAll(Long idProject, Integer page, Integer size);
    public ArchivesProjectDto getById(Long idArchiveProject);
    public ResponseDto delete(Long idUser, Long idArchiveProject);
}
