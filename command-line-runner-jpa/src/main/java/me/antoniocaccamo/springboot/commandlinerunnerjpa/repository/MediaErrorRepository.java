package me.antoniocaccamo.springboot.commandlinerunnerjpa.repository;

import me.antoniocaccamo.springboot.commandlinerunnerjpa.domain.MediaError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaErrorRepository extends JpaRepository<MediaError, Integer> {

}
