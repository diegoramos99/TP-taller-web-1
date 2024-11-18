package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ServicioImagenImpl implements ServicioImagen {

    private static final String DIRECTORIO_IMAGENES = "src/main/resources/images/ejercicios/";
    private static final String DIRECTORIO_IMAGENES_RECETAS = "src/main/resources/images/";

    @Override
    public String guardarImagen(MultipartFile imagen) throws IOException {
        if (imagen.isEmpty()) {
            throw new IllegalArgumentException("El archivo de imagen está vacío.");
        }

        String nombreImagen = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
        Path ruta = Paths.get(DIRECTORIO_IMAGENES, nombreImagen);

        if (!Files.exists(ruta.getParent())) {
            Files.createDirectories(ruta.getParent());
        }

        Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);
        return nombreImagen;
    }

    @Override
    public String guardarImagenReceta(MultipartFile imagen) throws IOException {
        if (imagen.isEmpty()) {
            throw new IllegalArgumentException("El archivo de imagen está vacío.");
        }

        String nombreImagen = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
        Path ruta = Paths.get(DIRECTORIO_IMAGENES_RECETAS, nombreImagen);

        if (!Files.exists(ruta.getParent())) {
            Files.createDirectories(ruta.getParent());
        }

        Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);
        return nombreImagen;
    }
}