package org.example.resume.parser;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

import java.io.File;
import java.io.IOException;

public class TikaParser {
    private  Tika tika = new Tika();


    public  String parse(File file) throws IOException, TikaException {
        return tika.parseToString(file);
    }


}
