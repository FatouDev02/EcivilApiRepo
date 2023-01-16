package com.example.EcivilApi.serviceimpl;

import com.example.EcivilApi.models.Structure;
import com.example.EcivilApi.repository.StructureRepository;
import com.example.EcivilApi.services.StructureService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StructureImpl implements StructureService {
    @Autowired
    StructureRepository structureRepository;



    @Override
    public Structure creer(Structure structure) {
        return structureRepository.save(structure);
    }


    @Override
    public Structure update(Long id, Structure structure) {
        return null;
    }

    @Override
    public List<Structure> getAll() {
        return null;
    }

    @Override
    public Structure GetById(Long id) {
        return null;
    }
}
