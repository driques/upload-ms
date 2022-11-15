package com.mingeso.uploadService.services;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {

    private final Path rootLocation = Paths.get("upload-dir");

    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Guarda archivo en carpeta root (uploads)
    public void save(MultipartFile file) {
        try {
            Files.copy(
                    file.getInputStream(), // Copiar desde archivo que se sube
                    rootLocation.resolve(file.getOriginalFilename()) // Se copia en carpeta root
            );
        } catch (Exception e) {
            throw new RuntimeException("Archivo ya existe en " + e.getMessage());
        }
    }

    //Comprueba si archivo existe en carpeta root y lo devuelve como recurso
    public Resource load(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("No se puede leer el archivo");
            }
        } catch (Exception e) {
            throw new RuntimeException("No se puede leer el archivo" + e.getMessage());
        }
    }


    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }



}
