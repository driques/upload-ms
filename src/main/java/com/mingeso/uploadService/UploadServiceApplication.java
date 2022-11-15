package com.mingeso.uploadService;

import com.mingeso.uploadService.services.FileUploadService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class UploadServiceApplication implements CommandLineRunner {

	@Resource
	FileUploadService fileUploadService;
	public static void main(String[] args) {
		SpringApplication.run(UploadServiceApplication.class, args);
	}

	// Al momento de inicializar la aplicación
	// Se borran las subidas
	// y se genera la carpeta uploads en la raiz del proyecto/contenedor
	@Override
	public void run(String... args) throws Exception {
		fileUploadService.deleteAll();
		fileUploadService.init();
	}

}
