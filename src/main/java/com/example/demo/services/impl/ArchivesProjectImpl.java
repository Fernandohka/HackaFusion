package com.example.demo.services.impl;

import com.example.demo.dto.ArchivesProjectDto;
import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.services.ArchivesProjectService;

public class ArchivesProjectImpl implements ArchivesProjectService {

    @Override
    public ArchivesProjectDto create(Long idUser, String name, String archive, Long idProject) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ListPageDto<ArchivesProjectDto> getAll(Long idProject, Integer page, Integer size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public ArchivesProjectDto getById(Long idArchiveProject) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public ResponseDto delete(Long idUser, Long idArchiveProject) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
