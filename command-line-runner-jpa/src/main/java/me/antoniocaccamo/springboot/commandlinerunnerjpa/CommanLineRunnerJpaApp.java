package me.antoniocaccamo.springboot.commandlinerunnerjpa;


import lombok.extern.slf4j.Slf4j;
import me.antoniocaccamo.springboot.commandlinerunnerjpa.domain.MediaError;
import me.antoniocaccamo.springboot.commandlinerunnerjpa.repository.MediaErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication @Slf4j
public class CommanLineRunnerJpaApp implements CommandLineRunner{

    @Autowired
    private MediaErrorRepository mediaErrorRepository;

    public static void main(String[] args) {
        log.info(">>> application started <<<");
        SpringApplication.run(CommanLineRunnerJpaApp.class, args);
        log.info(">>> application ended   <<<");
    }

    @Override
    public void run(String... strings) throws Exception {

        log.info("mediaErrorRepository.findAll() : {}",mediaErrorRepository.findAll());
        mediaErrorRepository.saveAll(splitError());
        log.info("mediaErrorRepository.findAll() : {}",mediaErrorRepository.findAll());
    }


    private static final String TEST_STRING = "Computer  [cocquio]\n" +
            "MediaError occured at :\n" +
            " - Computer : cocquio \n" +
            " - Media    : id [124] hash [-665764975] type [Video] path [C:\\SpotCocquio\\Nicolini_ist.mov]"
    ;




    public static List<MediaError> splitError()  {
        LocalDateTime start = LocalDateTime.now();
        log.info("TEST_STRING : {}", TEST_STRING);
        MediaError me = MediaErrorSplitter.splitError(TEST_STRING);
        me.setMessageNumber(7);
        me.setReceivedAt(new Date());
        assert "cocquio".equals(me.getComputer());
        assert "Video".equals(me.getType());
        assert "C:\\SpotCocquio\\Nicolini_ist.mov".equals(me.getPath());
        log.info("---------------------------------------- me : {}", me);
        List<MediaError> mediaErrors = new ArrayList<>();
        mediaErrors.add(me);
//        mediaErrors.add(me);
//        mediaErrors.add(me);
        return mediaErrors;
    }
}
