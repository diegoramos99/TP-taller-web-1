package com.tallerwebi.dominio;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ServicioImagen {

    String guardarImagen(MultipartFile imagen) throws IOException;
     String guardarImagenReceta(MultipartFile imagen) throws IOException;
}
