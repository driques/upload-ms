package com.mingeso.uploadService.controllers;


import com.mingeso.uploadService.entities.RegisterEntity;
import com.mingeso.uploadService.repositories.RegisterRepository;
import com.mingeso.uploadService.services.FileUploadService;
import com.mingeso.uploadService.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private RegisterService registerService;

    @Autowired
    private RegisterRepository registerRepository;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try{
            String filename = file.getOriginalFilename();  // Nombre del archivo
            System.out.println(filename);// Nombre del archivo
            fileUploadService.save(file); // Guarda archivo en carpeta root (uploads)
            registerService.save(filename); // Guarda registros en base de datos
            return ResponseEntity.ok("Archivo subido correctamente");   // Retorna mensaje de exito
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Error al subir archivo");
        }
    }

    @GetMapping("/registers")
    public ResponseEntity<List<RegisterEntity>> getRegisters(){
        try{
            List<RegisterEntity> registers = registerRepository.findAll();
            return ResponseEntity.ok(registers);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }


}
