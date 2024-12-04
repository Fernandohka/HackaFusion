package com.example.demo.services;

import java.util.List;

public interface ArchiveProjectService {
    public ArchiveProject post(String name, String archive, Long idProject);
    public List<ArchiveProject> getAll(Long idProject, Integer page, Integer size);
    public ArchiveProject getById(Long idArchiveProject);
    public ResponseDto delete(Long idArchiveProject);
}
