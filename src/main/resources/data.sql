
-- Insertar Desayunos
INSERT INTO Alimento (nombre, calorias, proteinas, grasas, carbohidratos, categoria, cantidad, tipo, dieta) VALUES
('Avena con Frutas', 250, 10, 5, 45, 'Desayuno', '1 taza', 'Desayuno', 'Vegano'),
('Smoothie Verde', 200, 5, 2, 40, 'Desayuno', '1 vaso', 'Desayuno', 'Vegano'),
('Tofu Revuelto', 220, 15, 12, 6, 'Desayuno', '1 taza', 'Desayuno', 'Vegano'),
('Yogur de Soja con Granola', 180, 8, 4, 30, 'Desayuno', '1 taza', 'Desayuno', 'Vegano'),
('Pan Integral con Aguacate', 300, 8, 15, 35, 'Desayuno', '2 rebanadas', 'Desayuno', 'Vegano'),
('Batido de Plátano y Espinaca', 210, 6, 1, 45, 'Desayuno', '1 vaso', 'Desayuno', 'Vegano'),
('Chía Pudding', 180, 5, 7, 30, 'Desayuno', '1 taza', 'Desayuno', 'Vegano'),

('Tostadas Francesas', 350, 10, 15, 50, 'Desayuno', '2 rebanadas', 'Desayuno', 'Vegetariano'),
('Yogur con Frutas y Miel', 250, 9, 4, 40, 'Desayuno', '1 taza', 'Desayuno', 'Vegetariano'),
('Huevos Revueltos', 300, 20, 25, 2, 'Desayuno', '2 huevos', 'Desayuno', 'Vegetariano'),
('Quesadilla de Espinaca', 320, 15, 15, 35, 'Desayuno', '1 porción', 'Desayuno', 'Vegetariano'),
('Tortilla Española', 350, 12, 20, 30, 'Desayuno', '1 porción', 'Desayuno', 'Vegetariano'),
('Pancakes con Miel', 400, 8, 10, 60, 'Desayuno', '3 pancakes', 'Desayuno', 'Vegetariano'),
('Muffin de Arándano', 220, 4, 9, 35, 'Desayuno', '1 muffin', 'Desayuno', 'Vegetariano'),

('Huevos Benedictinos', 400, 22, 30, 18, 'Desayuno', '2 huevos', 'Desayuno', 'omnivoro'),
('Bacon y Huevos', 450, 30, 35, 1, 'Desayuno', '2 huevos y 2 tiras', 'Desayuno', 'omnivoro'),
('Tostada de Aguacate con Huevo', 300, 12, 14, 30, 'Desayuno', '1 tostada', 'Desayuno', 'omnivoro'),
('Batido de Proteína', 250, 25, 5, 20, 'Desayuno', '1 vaso', 'Desayuno', 'omnivoro'),
('Sándwich de Huevo y Tocino', 500, 20, 25, 40, 'Desayuno', '1 sándwich', 'Desayuno', 'omnivoro'),
('Crepas con Jamón y Queso', 400, 15, 20, 30, 'Desayuno', '2 crepas', 'Desayuno', 'omnivoro'),
('Omelette de Verduras', 300, 20, 22, 5, 'Desayuno', '1 porción', 'Desayuno', 'omnivoro');

-- Insertar Almuerzos
INSERT INTO Alimento (nombre, calorias, proteinas, grasas, carbohidratos, categoria, cantidad, tipo, dieta) VALUES
('Ensalada de Garbanzos', 350, 15, 10, 50, 'Almuerzo', '1 taza', 'Almuerzo', 'Vegano'),
('Bowl de Quinoa y Verduras', 400, 14, 8, 60, 'Almuerzo', '1 taza', 'Almuerzo', 'Vegano'),
('Tacos de Lentejas', 450, 18, 12, 70, 'Almuerzo', '3 tacos', 'Almuerzo', 'Vegano'),
('Sopa de Verduras', 200, 5, 1, 40, 'Almuerzo', '1 taza', 'Almuerzo', 'Vegano'),
('Hamburguesa de Frijoles Negros', 350, 20, 5, 55, 'Almuerzo', '1 hamburguesa', 'Almuerzo', 'Vegano'),
('Curry de Garbanzos', 380, 15, 10, 55, 'Almuerzo', '1 porción', 'Almuerzo', 'Vegano'),
('Stir-Fry de Tofu', 420, 20, 15, 40, 'Almuerzo', '1 taza', 'Almuerzo', 'Vegano'),

('Ensalada de Quinoa y Feta', 400, 15, 10, 50, 'Almuerzo', '1 taza', 'Almuerzo', 'Vegetariano'),
('Pasta al Pesto', 500, 15, 20, 60, 'Almuerzo', '1 plato', 'Almuerzo', 'Vegetariano'),
('Wrap de Verduras y Hummus', 350, 10, 8, 50, 'Almuerzo', '1 wrap', 'Almuerzo', 'Vegetariano'),
('Pizza Vegetariana', 600, 25, 25, 70, 'Almuerzo', '1 porción', 'Almuerzo', 'Vegetariano'),
('Tortilla de Espinacas', 300, 15, 12, 30, 'Almuerzo', '1 porción', 'Almuerzo', 'Vegetariano'),
('Risotto de Champiñones', 450, 18, 10, 60, 'Almuerzo', '1 plato', 'Almuerzo', 'Vegetariano'),
('Lasaña Vegetariana', 600, 20, 25, 80, 'Almuerzo', '1 porción', 'Almuerzo', 'Vegetariano'),

('Pollo Asado con Verduras', 600, 40, 30, 20, 'Almuerzo', '1 porción', 'Almuerzo', 'omnivoro'),
('Tacos de Pollo', 500, 35, 20, 50, 'Almuerzo', '3 tacos', 'Almuerzo', 'omnivoro'),
('Pasta con Salsa Boloñesa', 700, 25, 15, 90, 'Almuerzo', '1 plato', 'Almuerzo', 'omnivoro'),
('Ensalada César con Pollo', 550, 30, 25, 20, 'Almuerzo', '1 porción', 'Almuerzo', 'omnivoro'),
('Arroz Frito con Cerdo', 600, 20, 30, 70, 'Almuerzo', '1 taza', 'Almuerzo', 'omnivoro'),
('Sándwich de Pavo', 450, 30, 10, 40, 'Almuerzo', '1 sándwich', 'Almuerzo', 'omnivoro'),
('Bowl de Pollo Teriyaki', 650, 35, 25, 80, 'Almuerzo', '1 taza', 'Almuerzo', 'omnivoro');

-- Insertar Meriendas
INSERT INTO Alimento (nombre, calorias, proteinas, grasas, carbohidratos, categoria, cantidad, tipo, dieta) VALUES
('Batido de Frutas', 150, 3, 1, 35, 'Merienda', '1 vaso', 'Merienda', 'Vegano'),
('Galletas de Avena', 200, 5, 7, 30, 'Merienda', '3 galletas', 'Merienda', 'Vegano'),
('Hummus con Verduras', 250, 10, 12, 20, 'Merienda', '1 taza', 'Merienda', 'Vegano'),
('Frutos Secos Mixtos', 300, 10, 20, 20, 'Merienda', '1/2 taza', 'Merienda', 'Vegano'),
('Barra de Granola', 200, 5, 5, 35, 'Merienda', '1 barra', 'Merienda', 'Vegano'),
('Palomitas de Maíz', 100, 3, 1, 20, 'Merienda', '1 taza', 'Merienda', 'Vegano'),
('Gelatina de Frutas', 80, 2, 0, 20, 'Merienda', '1 porción', 'Merienda', 'Vegano'),

('Yogur con Granola', 250, 9, 4, 40, 'Merienda', '1 taza', 'Merienda', 'Vegetariano'),
('Tostadas con Mantequilla de Maní', 300, 12, 18, 30, 'Merienda', '2 rebanadas', 'Merienda', 'Vegetariano'),
('Frutas con Chocolate', 180, 2, 7, 30, 'Merienda', '1 porción', 'Merienda', 'Vegetariano'),
('Mini Quiches de Espinaca', 220, 10, 15, 15, 'Merienda', '2 mini quiches', 'Merienda', 'Vegetariano'),
('Cheesecake de Frutos Rojos', 400, 8, 25, 30, 'Merienda', '1 porción', 'Merienda', 'Vegetariano'),
('Smoothie de Fresa', 200, 4, 1, 40, 'Merienda', '1 vaso', 'Merienda', 'Vegetariano'),
('Pudding de Chía', 180, 5, 9, 30, 'Merienda', '1 taza', 'Merienda', 'Vegetariano'),

('Galletas de Chocolate Chip', 250, 3, 12, 35, 'Merienda', '3 galletas', 'Merienda', 'omnivoro'),
('Sándwich de Jamón y Queso', 400, 20, 20, 30, 'Merienda', '1 sándwich', 'Merienda', 'omnivoro'),
('Batido de Proteína con Plátano', 300, 25, 5, 40, 'Merienda', '1 vaso', 'Merienda', 'omnivoro'),
('Nachos con Queso', 400, 10, 25, 30, 'Merienda', '1 porción', 'Merienda', 'omnivoro'),
('Tortas de Arroz con Mantequilla', 200, 5, 10, 25, 'Merienda', '3 tortas', 'Merienda', 'omnivoro'),
('Galletas de Avena con Pasas', 220, 4, 7, 35, 'Merienda', '2 galletas', 'Merienda', 'omnivoro'),
('Smoothie de Mango', 250, 4, 1, 55, 'Merienda', '1 vaso', 'Merienda', 'omnivoro');

-- Insertar Cenas
INSERT INTO Alimento (nombre, calorias, proteinas, grasas, carbohidratos, categoria, cantidad, tipo, dieta) VALUES
('Ensalada de Espinaca', 200, 5, 1, 40, 'Cena', '1 porción', 'Cena', 'Vegano'),
('Sopa de Lentejas', 250, 13, 2, 40, 'Cena', '1 taza', 'Cena', 'Vegano'),
('Pasta con Salsa de Tomate', 300, 10, 5, 60, 'Cena', '1 plato', 'Cena', 'Vegano'),
('Buddha Bowl', 400, 12, 8, 70, 'Cena', '1 tazón', 'Cena', 'Vegano'),
('Verduras Asadas', 180, 4, 8, 25, 'Cena', '1 porción', 'Cena', 'Vegano'),
('Curry de Verduras', 350, 10, 15, 40, 'Cena', '1 porción', 'Cena', 'Vegano'),
('Tofu al Horno con Especias', 400, 20, 20, 30, 'Cena', '1 porción', 'Cena', 'Vegano'),

('Pasta con Salsa Alfredo', 500, 15, 30, 60, 'Cena', '1 plato', 'Cena', 'Vegetariano'),
('Ensalada Caprese', 300, 12, 18, 20, 'Cena', '1 porción', 'Cena', 'Vegetariano'),
('Tortilla de Papas', 400, 10, 25, 40, 'Cena', '1 porción', 'Cena', 'Vegetariano'),
('Pizza Margherita', 600, 25, 30, 70, 'Cena', '1 porción', 'Cena', 'Vegetariano'),
('Risotto de Espárragos', 500, 12, 15, 80, 'Cena', '1 plato', 'Cena', 'Vegetariano'),
('Tacos de Queso', 350, 18, 22, 30, 'Cena', '2 tacos', 'Cena', 'Vegetariano'),
('Bowl de Quinoa y Espinaca', 400, 15, 8, 60, 'Cena', '1 tazón', 'Cena', 'Vegetariano'),

('Salmón a la Parrilla', 600, 40, 30, 20, 'Cena', '1 porción', 'Cena', 'omnivoro'),
('Pollo al Limón', 500, 35, 20, 30, 'Cena', '1 porción', 'Cena', 'omnivoro'),
('Carne Asada', 700, 40, 35, 0, 'Cena', '1 porción', 'Cena', 'omnivoro'),
('Tacos de Carne', 600, 30, 25, 40, 'Cena', '3 tacos', 'Cena', 'omnivoro'),
('Sopa de Pollo', 350, 20, 10, 30, 'Cena', '1 taza', 'Cena', 'omnivoro'),
('Pasta con Pollo y Espinacas', 550, 30, 25, 40, 'Cena', '1 plato', 'Cena', 'omnivoro'),
('Ensalada de Atún', 400, 25, 15, 20, 'Cena', '1 porción', 'Cena', 'omnivoro');


INSERT INTO Usuario (activo, apellido, email, informacionAdicional, nombre, objetivoSalud, password, preferenciaAlimenticia, restrincionesAlimentarias, rol)
VALUES
(true, 'García', 'juan.garcia@mail.com', 'Ninguna', 'Juan', 'Perder peso', '1234', 'Vegetariano', 'Sin gluten', 'USER'),
(true, 'Pérez', 'test@unlam.edu.ar', 'Ninguna', 'María', 'Ganar músculo', 'test', 'Omnívora', '', 'USER');

