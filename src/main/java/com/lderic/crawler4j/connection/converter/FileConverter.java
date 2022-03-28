package com.lderic.crawler4j.connection.converter;

import com.lderic.crawler4j.connection.Receivable;
import com.lderic.crawler4j.connection.Sendable;

import java.io.*;

public class FileConverter implements Receivable<File>, Sendable<File> {
    private final File file;

    public FileConverter(File file) {
        this.file = file;
    }

    @Override
    public File toOriginal(InputStream content) {
        try (
                BufferedInputStream bis = new BufferedInputStream(content);
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))
        ) {
            int len;

            while ((len = bis.read()) != -1) {
                bos.write(len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public byte[] toBytes(File original) {
        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                ByteArrayOutputStream baos = new ByteArrayOutputStream(8192)
        ) {
            int len;

            while ((len = bis.read()) != -1) {
                baos.write(len);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            throw new Error("FileConverter Error");
        }
    }
}