package com.imagebank.repository;

import com.imagebank.model.Image;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
public class ImageRepositoryImpl implements ImageRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Image get(int id) {
        return em.find(Image.class, id);
    }

    @Override
    public Collection<Image> getByTitle(String title) {
        TypedQuery<Image> query = em.createQuery("SELECT image FROM Image image where image.title=:title order by image.title", Image.class);
        return query.setParameter("title", title).getResultList();
    }


    @Override
    @Transactional
    public Image save(Image image) {
        if (image.isNew()) {
            em.persist(image);
            return image;
        } else {
            em.merge(image);
            return image;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        Query query = em.createQuery("DELETE from Image image where image.id=:id");
        return query.setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Collection<Image> getAll() {
        TypedQuery<Image> query = em.createQuery("SELECT image FROM Image image order by image.title", Image.class);
        return query.getResultList();
    }
}
