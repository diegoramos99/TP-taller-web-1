
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

INSERT INTO Receta (nombre, ingredientes, preparacion)
VALUES
('Huevo Revueltos', 'Huevos, Sal, Mantequilla','Bate los huevos con una pizca de sal. En una sartén, derrite la mantequilla a fuego medio. Agrega los huevos batidos y revuelve suavemente hasta que estén cocidos, pero aún cremosos.'),
('Arroz Blanco', 'Arroz, Agua, Sal','Enjuaga el arroz bajo agua fría. En una olla, añade el arroz, el doble de agua y una pizca de sal. Lleva a ebullición, luego cubre y reduce el fuego. Cocina a fuego lento durante 18-20 minutos, hasta que el agua se haya absorbido.'),
('Carne Asada', 'Carne de res, Sal, Pimienta','Sazona la carne con sal y pimienta al gusto. Asa a la parrilla o en una sartén caliente durante unos 4-5 minutos por cada lado, dependiendo del grosor, hasta alcanzar el punto deseado.'),
('Pasta al Ajo', 'Pasta, Ajo, Aceite de oliva','Cocina la pasta según las instrucciones del paquete. En una sartén, calienta aceite de oliva y añade ajo picado. Sofríe hasta que esté dorado, luego mezcla con la pasta cocida y sirve caliente.'),
('Papas Fritas', 'Papas, Aceite, Sal','Pela y corta las papas en tiras. Calienta aceite en una freidora o sartén. Fría las papas en tandas hasta que estén doradas y crujientes. Escurre sobre papel absorbente y espolvorea con sal.'),
('Tortilla Española', 'Huevos, Papas, Cebolla','Pela y corta las papas en rodajas finas. Sofríe las papas y la cebolla en aceite hasta que estén tiernas. Bate los huevos y mezcla con las papas. Vierte en la sartén y cocina a fuego lento hasta que esté firme por ambos lados.'),
('Arroz con Pollo', 'Arroz, Pollo, Verduras','Sofríe trozos de pollo hasta dorarlos. Agrega arroz y verduras picadas. Añade agua y sazona al gusto. Cocina a fuego lento hasta que el arroz esté tierno y haya absorbido el líquido.'),
('Ensalada de Atún', 'Atún, Lechuga, Tomate, Mayonesa','En un tazón, mezcla el atún desmenuzado, lechuga picada y tomate en cubos. Añade mayonesa al gusto y mezcla bien. Sirve fresca.'),
('Pasta con Salsa de Tomate', 'Pasta, Tomates, Cebolla, Ajo','Cocina la pasta según las instrucciones. En una sartén, sofríe cebolla y ajo picados, luego agrega tomates triturados. Cocina hasta que espese y mezcla con la pasta cocida.'),
('Puré de Papas', 'Papas, Mantequilla, Leche','Pela y corta las papas en trozos. Cocina en agua hirviendo hasta que estén tiernas. Escurre y machaca las papas con mantequilla y leche hasta obtener un puré suave. Sazona al gusto.');