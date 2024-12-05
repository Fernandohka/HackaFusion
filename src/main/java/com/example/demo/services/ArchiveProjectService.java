package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.ResponseDto;
import com.example.demo.model.ArchivesProject;


public interface ArchiveProjectService {
    public ArchivesProject post(String name, String archive, Long idProject);
    public List<ArchivesProject> getAll(Long idProject, Integer page, Integer size);
    public ArchivesProject getById(Long idArchiveProject);
    public ResponseDto delete(Long idArchiveProject);
}
