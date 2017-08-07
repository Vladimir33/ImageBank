package com.imagebank.service;

import com.imagebank.model.Image;
import com.imagebank.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    ImageRepository repository;

    @Override
    public Image get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Image> getByTitle(String title) {
        return repository.getByTitle(title);
    }

    @Override
    public Image save(Image image) {
        return repository.save(image);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public Collection<Image> getAll() {
        return repository.getAll();
    }
}
