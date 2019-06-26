package me.antoniocaccamo.springboot.commandlinerunnerjpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@MappedSuperclass @Slf4j @Getter @Setter
public abstract class AbstractEntity implements Serializable{

    @Value("${spring.datasource.username}")
    @Transient
    private String user;


    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "CREATED_USER")
    private String   createdUser;

    @Column(name = "MODIFIED_AT")
    private LocalDateTime modifiedAt;

    @Column(name = "MODIFIED_USER")
    private String modifiedUser;

    @PostConstruct
    public void postConstruct(){
        if (StringUtils.isEmpty(createdUser)) {
            createdAt = modifiedAt = LocalDateTime.now();
            createdUser = modifiedUser = user;
            log.info("postConstruct..");
        }
    }

    @PreUpdate
    public void prePersist(){
        modifiedAt   = LocalDateTime.now();
        modifiedUser = user;
    }

    public static LocalDateTime convert(Date date) {
        return convert(date, ZoneId.systemDefault());
    }

    public static LocalDateTime convert(Date date, ZoneId zoneId) {
        return date
                .toInstant()
                .atZone(zoneId)
                .toLocalDateTime()
        ;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("modifiedUser", modifiedUser)
                .append("modifiedAt", modifiedAt)
                .append("createdUser", createdUser)
                .append("createdAt", createdAt)
                .toString();
    }
}
