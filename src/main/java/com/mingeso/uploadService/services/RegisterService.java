package com.mingeso.uploadService.services;


import com.mingeso.uploadService.entities.RegisterEntity;
import com.mingeso.uploadService.repositories.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RegisterService {
    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    RegisterRepository registerRepository;

    public void save(String filename) {
        try{
            registerRepository.dropTable();
            System.out.println("Tabla eliminada");
        }catch (Exception e){
            System.out.println("No se pudo truncar la tabla");
        }

        try{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            //Se busca el archivo guardado
            Resource resource = fileUploadService.load(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));

            String line = br.readLine();

            while (line!=null){
                String[] data = line.split(";");
                //Se crea un nuevo registro
                RegisterEntity register = new RegisterEntity();
                register.setDate(java.time.LocalDate.parse(data[0], dtf));
                register.setTime(data[1]);
                register.setRut(data[2]);

                //Se guarda registro en base de datos
                registerRepository.save(register);

                line = br.readLine();


            }
            br.close();

        }
        catch(Exception e){
            throw new RuntimeException("Error al guardar registro");
        }

    }


    public List<RegisterEntity> getAllRegisters(){
        return registerRepository.findAll();
    }


}
