package com.imagebank.service;

import com.imagebank.model.Image;

import java.util.Collection;

public interface ImagesService {

    Image get(int id);

    Collection<Image> getByTitle(String title);

    Image save(Image image);

    boolean delete(int id);

    Collection<Image> getAll();
}
