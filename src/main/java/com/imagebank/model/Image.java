package com.imagebank.model;

import javax.persistence.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Entity
@Table(name = "images", uniqueConstraints = {@UniqueConstraint(columnNames = "title", name = "images_unique_title_idx")})
public class Image extends BaseEntity {


    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private String description;

    public Image() {
    }

    public Image(Integer id, String link, String title, Category category, String description) {
        super(id);
        this.link = link;
        this.title = title;
        this.category = category;
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Image{" +
                "title='" + title + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                '}';
    }

    @PostRemove
    private void postRemove() {
        Path file = Paths.get(this.getLink());
        try {
            Files.delete(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
