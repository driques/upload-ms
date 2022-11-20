package com.mingeso.uploadService.repositories;

import com.mingeso.uploadService.entities.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity, Long> {
    //Truncate the register table
    @Modifying
    @Query(value = "TRUNCATE TABLE register;",nativeQuery = true)
    void dropTable();

    //Get all registers
    @Query(value = "SELECT * FROM register", nativeQuery = true)
    Iterable<RegisterEntity> getAllRegisters();


}
