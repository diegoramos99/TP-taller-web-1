
INSERT INTO Alimento (nombre, calorias, cantidad, proteinas, grasas, carbohidratos, categoria)
VALUES
('Huevo cocido', 155, '1 unidad', 13, 11, 1.1, 'Proteína'),
('Huevo frito', 196, '1 unidad', 14, 15, 1.2, 'Proteína'),
('Huevo revuelto', 180, '1 unidad', 13, 14, 1.5, 'Proteína'),
('Tortilla de huevo', 154, '1 unidad', 11, 11, 1.0, 'Proteína'),
('Clara de huevo', 17, '1 unidad', 3.6, 0.1, 0.2, 'Proteína'),
('Arroz blanco', 130, '100g', 2.7, 0.3, 28, 'Carbohidrato'),
('Arroz integral', 111, '100g', 2.6, 0.9, 23, 'Carbohidrato'),
('Arroz basmati', 121, '100g', 3.5, 0.4, 25, 'Carbohidrato'),
('Arroz jazmín', 130, '100g', 2.6, 0.3, 28, 'Carbohidrato'),
('Arroz frito', 250, '1 porción', 7.0, 12, 35, 'Carbohidrato'),
('Pechuga de pollo', 165, '100g', 31, 3.6, 0, 'Proteína'),
('Muslo de pollo', 209, '100g', 27, 11, 0, 'Proteína'),
('Pollo al horno', 180, '100g', 27, 7.5, 0, 'Proteína'),
('Pollo a la plancha', 170, '100g', 28, 7.0, 0, 'Proteína'),
('Pollo frito', 260, '1 porción', 21, 14, 11, 'Proteína'),
('Avena en hojuelas', 389, '100g', 16.9, 6.9, 66, 'Carbohidrato'),
('Avena cocida', 71, '100g', 2.5, 1.4, 12, 'Carbohidrato'),
('Avena instantánea', 367, '100g', 12.6, 6.2, 64, 'Carbohidrato'),
('Avena con leche', 150, '1 taza', 5, 3.0, 27, 'Carbohidrato'),
('Batido de avena', 250, '1 vaso', 10, 5, 45, 'Carbohidrato');



INSERT INTO Usuario (activo, apellido, email, informacionAdicional, nombre, objetivoSalud, password, preferenciaAlimenticia, restrincionesAlimentarias, rol)
VALUES
(true, 'García', 'juan.garcia@mail.com', 'Ninguna', 'Juan', 'Perder peso', '1234', 'Vegetariano', 'Sin gluten', 'USER'),
(true, 'Pérez', 'test@unlam.edu.ar', 'Ninguna', 'María', 'Ganar músculo', 'test', 'Omnívora', '', 'USER');

