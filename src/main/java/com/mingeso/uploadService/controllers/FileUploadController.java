package com.mingeso.uploadService.controllers;


import com.mingeso.uploadService.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
//@CrossOrigin(origins = "http://localhost:3000")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try{
            String filename = file.getOriginalFilename();  // Nombre del archivo
            System.out.println(filename);// Nombre del archivo
            fileUploadService.save(file); // Guarda archivo en carpeta root (uploads)
            return ResponseEntity.ok("Archivo subido correctamente");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Error al subir archivo");
        }
    }



}
