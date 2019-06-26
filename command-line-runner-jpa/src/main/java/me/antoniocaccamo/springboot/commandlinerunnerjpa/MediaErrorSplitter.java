package me.antoniocaccamo.springboot.commandlinerunnerjpa;

import lombok.extern.slf4j.Slf4j;
import me.antoniocaccamo.springboot.commandlinerunnerjpa.domain.MediaError;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
public class MediaErrorSplitter {


    private static final String PATH_REGEX = "\\ path.*\\s\\[.*\\]";
    private static final Pattern PATH_PATTERN = Pattern.compile(PATH_REGEX);

    private static final String TYPE_REGEX = "\\ type.*\\s\\[.*\\]";
    private static final Pattern TYPE_PATTERN = Pattern.compile(TYPE_REGEX);

    private static final String COMPUTER_REGEX = "^Computer.*\\s\\[.*\\]";
    private static final Pattern COMPUTER_PATTERN = Pattern.compile(COMPUTER_REGEX);

    private static final String START = "[";

    private static final String END = "]";

    private static MediaErrorSplitter instance = new MediaErrorSplitter();

//    public static MediaError splitError(Message message) throws IOException, MessagingException {
//        MediaError me = splitError(message.getContent().toString());
//        me.setReceivedAt(message.getReceivedDate());
//        me.setMessageNumber(message.getMessageNumber());
//        return me;
//    }


    public static MediaError splitError(String errorSting) {
        MediaError error = new MediaError();

        Matcher matcher = null;
        StringBuilder sb = null;


        matcher = COMPUTER_PATTERN.matcher(errorSting);
        if (matcher.find()) {
            sb = new StringBuilder(matcher.group(0));
            int sidx = sb.indexOf(START);
            int eidx = sb.length();
            if (!(sidx < 0)) {
                eidx = sb.indexOf(END, sidx++);
                if (eidx < 0) {
                    eidx = sb.length();
                }
            }
            error.setComputer(sb.substring(sidx, eidx));
        }

        matcher = TYPE_PATTERN.matcher(errorSting);
        if (matcher.find()) {
            sb = new StringBuilder(matcher.group(0));
            int sidx = sb.indexOf(START);
            int eidx = sb.length();
            if (!(sidx < 0)) {
                eidx = sb.indexOf(END, sidx++);
                if (eidx < 0) {
                    eidx = sb.length();
                }
            }
            error.setType(sb.substring(sidx, eidx));
        }

        matcher = PATH_PATTERN.matcher(errorSting);
        if (matcher.find()) {
            sb = new StringBuilder(matcher.group(0));
            int sidx = sb.indexOf(START);
            int eidx = sb.length();
            if (!(sidx < 0)) {
                eidx = sb.indexOf(END, sidx++);
                if (eidx < 0) {
                    eidx = sb.length();
                }
            }
            error.setPath(sb.substring(sidx, eidx));
        }

        error.setOriginalMessage(errorSting);

        return error;
    }

    //    public static void main(String[] args) {
//
//        log.info("string : {}", TEST_STRING);
//        log.info("error  : {}", MediaErroSplitter.getInstance().splitError(MediaErroSplitter.TEST_STRING));
//    }


}
