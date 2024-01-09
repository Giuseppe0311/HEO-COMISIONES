package com.pe.HeoComisiones.Utils.Cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CloudService {
    private final Cloudinary cloudinary;
    public String uniqueFileName="sin_nombre" + UUID.randomUUID().toString()+".docx";

    public String upload(byte[] file){
        try {
            Map params = ObjectUtils.asMap("resource_type", "raw", "public_id", "contratos/"+uniqueFileName);
            Map uploadResult = cloudinary.uploader().upload(file, params);
            return uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
