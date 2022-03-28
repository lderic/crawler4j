package com.lderic.crawler4j.connection.converter;

import com.lderic.crawler4j.connection.Receivable;
import com.lderic.crawler4j.connection.Sendable;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class StringConverter implements Receivable<String>, Sendable<String> {
    @Override
    public String toOriginal(InputStream content) {
        String result = null;
        try (
                BufferedInputStream bis = new BufferedInputStream(content);
                ByteArrayOutputStream baos = new ByteArrayOutputStream(8192)
        ) {
            int len;

            while ((len = bis.read()) != -1) {
                baos.write(len);
            }
            result = baos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public byte[] toBytes(String original) {
        return original.getBytes(StandardCharsets.UTF_8);
    }
}