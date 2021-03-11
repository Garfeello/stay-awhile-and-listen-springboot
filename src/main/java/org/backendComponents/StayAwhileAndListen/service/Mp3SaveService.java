package org.backendComponents.StayAwhileAndListen.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class Mp3SaveService {

    public byte[] getMpegBlob(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
