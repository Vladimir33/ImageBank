package com.imagebank.web;

import com.imagebank.model.Image;
import com.imagebank.service.ImagesService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;


@Controller(value = "/upload")
public class UploadController {

    private static final String UPLOAD_DIRECTORY = "C:\\uploads";

    @Autowired
    ImagesService service;

    @PostConstruct
    void init() throws Exception {
        if (new File(UPLOAD_DIRECTORY).mkdir()) {
            throw new RuntimeException("unable to create directory");
        }
    }

    @PostMapping
    public String upload(Model model, HttpServletRequest request) {
        String name = "";
        File file = null;
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> fileItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : fileItems) {
                    if (!item.isFormField()) {
                        name = new File(item.getName()).getName();
                        try {
                            file = new File(UPLOAD_DIRECTORY + File.separator + name);
                            item.write(file);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("image", new Image());
        assert file != null;
        model.addAttribute("message", "Image: " + name + " Uploaded Successfully");
        model.addAttribute("link", file.getAbsolutePath());
        model.addAttribute("fileName", name);
        return "image";
    }
}
