package me.antoniocaccamo.springboot.commandlinerunnerjpa.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by antonio on 18/05/2019.
 */
@Getter
@Setter
@Entity(name = "MEDIA_ERROR")
@SequenceGenerator(name="MEDIA_ERROR_SEQ", sequenceName="MEDIA_ERROR_SEQ", allocationSize=1)
public class MediaError extends AbstractEntity implements AutoCloseable, Serializable {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

//    public MediaError() {
//        super();
//        postConstruct();
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MEDIA_ERROR_SEQ")
    private Integer id;

   
    @Column(name = "MESSAGE_NUMBER")
    private Integer messageNumber;

   
    @Column(name = "COMPUTER")
    private String computer;

   
    @Column(name = "TYPE")
    private String type;

   
    @Column(name = "PATH")
    private String path;


    @Column(name = "RECEIVED_AT")
    private LocalDateTime receivedAt;

//   
//    @Transient
//    private String receivedDateString;

   
    @Column(name = "ORIGINAL_MESSAGE")
    private String originalMessage;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("messageNumber",messageNumber)
                .append("computer", computer)
                .append("type", type)
                .append("path", path)
                .append("receivedAt", receivedAt)
//                .append("receivedDateString", receivedDateString)
                .append("originalMessage", originalMessage)
                .append("",super.toString())
                .toString();
    }

    public void setReceivedAt(Date receivedAt){
        this.receivedAt = AbstractEntity.convert(receivedAt);
//        if ( receivedAt != null)
//            setReceivedDateString(SIMPLE_DATE_FORMAT.format(receivedAt));

    }

    @Override
    public void close() throws Exception {

    }
}
