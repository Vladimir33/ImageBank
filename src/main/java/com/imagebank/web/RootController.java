package com.imagebank.web;


import com.imagebank.model.Category;
import com.imagebank.model.Image;
import com.imagebank.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


@Controller("/")
public class RootController {

    @Autowired
    ImagesService service;

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("images", service.getAll());
        return "images";
    }

    @PostMapping("/filter")
    public String filter(Model model, HttpServletRequest request) {
        String title = request.getParameter("title");
        assert title != null;
        if (!title.isEmpty()) {
            model.addAttribute("images", service.getByTitle(title));
        } else {
            model.addAttribute("images", service.getAll());
        }
        return "images";
    }


    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("image", service.get(Integer.valueOf(request.getParameter("id"))));
        return "image";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("image", new Image());
        return "image";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        service.delete(Integer.valueOf(request.getParameter("id")));
        return "redirect:/";
    }

    @PostMapping("/images")
    public String updateOrCreate(HttpServletRequest request) {
        String id = request.getParameter("id");
        String link = request.getParameter("link");

        Image image = new Image(id.isEmpty() ? null : Integer.valueOf(id),
                link.isEmpty() ? null : link,
                request.getParameter("title"),
                Enum.valueOf(Category.class, request.getParameter("category")),
                request.getParameter("description"));

        service.save(image);

        return "redirect:/";
    }
}
