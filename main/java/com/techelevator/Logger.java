package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private File file;
    private PrintWriter pw;

    public Logger(String fileName) {

        this.file = new File(fileName);

    }

    public void writeMessage(String message) {

        if(this.file.exists()) {
            try {
                this.pw = new PrintWriter( new FileOutputStream(this.file, true) );
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                this.pw = new PrintWriter(this.file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        LocalDate today = LocalDate.now();
        LocalTime time = LocalTime.now();
        String formattedTime = DateTimeFormatter.ofPattern("hh:mm:ss a").format(time);
        this.pw.println(today + " " + formattedTime + " " + message);
        this.pw.flush();
        this.pw.close();
    }

}
