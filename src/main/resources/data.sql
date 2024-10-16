
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


INSERT INTO Usuario (actividad, activo, altura, apellido, edad, email,     formula, informacionAdicional, nombre, objetivoSalud,     password, peso, preferenciaAlimenticia, restrincionesAlimentarias,     rol, sexo )
VALUES ( 'moderado', true, 175.0, 'Pérez', 25, '123@gmail.com',     'Fórmula de ejemplo', 'Ninguna', 'Juan', 'mantener',     '123', 70.0, 'vegetariano', 'lácteos', 'USER', 'masculino' );


INSERT INTO Receta (nombre, ingredientes, preparacion, tiempo, calorias,imagen)
VALUES
('Huevo Revueltos', 'Huevos, Sal, Mantequilla', 'Bate los huevos con una pizca de sal. En una sartén, derrite la mantequilla a fuego medio. Agrega los huevos batidos y revuelve suavemente hasta que estén cocidos, pero aún cremosos.', 10, 200,'images/huevo_revuelto.jpg'),
('Arroz Blanco', 'Arroz, Agua, Sal', 'Enjuaga el arroz bajo agua fría. En una olla, añade el arroz, el doble de agua y una pizca de sal. Lleva a ebullición, luego cubre y reduce el fuego. Cocina a fuego lento durante 18-20 minutos, hasta que el agua se haya absorbido.', 20, 150,'images/arroz_blanco.jpg'),
('Carne Asada', 'Carne de res, Sal, Pimienta', 'Sazona la carne con sal y pimienta al gusto. Asa a la parrilla o en una sartén caliente durante unos 4-5 minutos por cada lado, dependiendo del grosor, hasta alcanzar el punto deseado.', 10, 300,'images/plato_vacio.jpg'),
('Pasta al Ajo', 'Pasta, Ajo, Aceite de oliva', 'Cocina la pasta según las instrucciones del paquete. En una sartén, calienta aceite de oliva y añade ajo picado. Sofríe hasta que esté dorado, luego mezcla con la pasta cocida y sirve caliente.', 15, 400,'src/main/resources/arroz_blanco.jpg'),
('Papas Fritas', 'Papas, Aceite, Sal', 'Pela y corta las papas en tiras. Calienta aceite en una freidora o sartén. Fría las papas en tandas hasta que estén doradas y crujientes. Escurre sobre papel absorbente y espolvorea con sal.', 25, 350,'images/plato_vacio.jpg'),
('Tortilla Española', 'Huevos, Papas, Cebolla', 'Pela y corta las papas en rodajas finas. Sofríe las papas y la cebolla en aceite hasta que estén tiernas. Bate los huevos y mezcla con las papas. Vierte en la sartén y cocina a fuego lento hasta que esté firme por ambos lados.', 30, 450,'images/plato_vacio.jpg'),
('Arroz con Pollo', 'Arroz, Pollo, Verduras', 'Sofríe trozos de pollo hasta dorarlos. Agrega arroz y verduras picadas. Añade agua y sazona al gusto. Cocina a fuego lento hasta que el arroz esté tierno y haya absorbido el líquido.', 40, 500,'images/arrozCpollo.jpg'),
('Ensalada de Atún', 'Atún, Lechuga, Tomate, Mayonesa', 'En un tazón, mezcla el atún desmenuzado, lechuga picada y tomate en cubos. Añade mayonesa al gusto y mezcla bien. Sirve fresca.', 10, 250,'images/plato_vacio.jpg'),
('Pasta con Salsa de Tomate', 'Pasta, Tomates, Cebolla, Ajo', 'Cocina la pasta según las instrucciones. En una sartén, sofríe cebolla y ajo picados, luego agrega tomates triturados. Cocina hasta que espese y mezcla con la pasta cocida.', 20, 400,'images/plato_vacio.jpg'),
('Puré de Papas', 'Papas, Mantequilla, Leche', 'Pela y corta las papas en trozos. Cocina en agua hirviendo hasta que estén tiernas. Escurre y machaca las papas con mantequilla y leche hasta obtener un puré suave. Sazona al gusto.', 30, 300,'images/plato_vacio.jpg'),
('Sopa de Verduras', 'Zanahorias, Apio, Cebolla, Caldo de verduras, Sal, Pimienta', 'Sofríe la cebolla, zanahorias y apio en una olla. Añade el caldo y cocina a fuego medio hasta que las verduras estén tiernas.', 30, 150,'images/plato_vacio.jpg'),
('Enchiladas de Pollo', 'Tortillas, Pollo desmenuzado, Salsa de tomate, Cebolla, Queso', 'Rellena las tortillas con pollo y cebolla, cubre con salsa y queso. Hornea a 180°C durante 20 minutos.', 40, 400,'images/plato_vacio.jpg'),
('Tacos de Pescado', 'Pescado, Tortillas, Repollo, Salsa, Limón', 'Cocina el pescado a la parrilla. Sirve en tortillas con repollo y salsa. Exprime limón al gusto.', 25, 350,'images/plato_vacio.jpg'),
('Quiche de Espinacas', 'Masa de tarta, Espinacas, Huevos, Queso, Leche', 'Mezcla espinacas, huevos, queso y leche. Vierte en la masa y hornea a 180°C durante 35 minutos.', 50, 300,'images/plato_vacio.jpg'),
('Pollo al Limón', 'Pechugas de pollo, Limón, Ajo, Aceite de oliva, Sal', 'Marina el pollo con limón, ajo y aceite. Cocina en una sartén hasta dorar por ambos lados.', 25, 350,'images/plato_vacio.jpg'),
('Hamburguesas Caseras', 'Carne molida, Pan de hamburguesa, Lechuga, Tomate, Cebolla', 'Forma las hamburguesas y cocínalas a la parrilla o sartén. Sirve en pan con los acompañamientos.', 30, 500,'images/plato_vacio.jpg'),
('Guiso de Lentejas', 'Lentejas, Zanahorias, Cebolla, Ajo, Caldo', 'Sofríe cebolla y ajo, añade lentejas y caldo. Cocina a fuego lento hasta que las lentejas estén tiernas.', 45, 250,'images/plato_vacio.jpg'),
('Tortitas de Avena', 'Avena, Huevo, Leche, Plátano, Canela', 'Mezcla todos los ingredientes y cocina en una sartén hasta dorar por ambos lados.', 20, 300,'images/plato_vacio.jpg'),
('Curry de Garbanzos', 'Garbanzos, Leche de coco, Curry, Espinacas, Cebolla', 'Sofríe la cebolla, añade garbanzos y curry. Agrega leche de coco y cocina hasta espesar. Añade espinacas al final.', 30, 400,'images/plato_vacio.jpg'),
('Brownies de Chocolate', 'Chocolate, Mantequilla, Azúcar, Huevos, Harina', 'Derrite chocolate y mantequilla. Mezcla con azúcar y huevos, luego añade la harina. Hornea a 180°C por 25 minutos.', 35, 250,'images/plato_vacio.jpg'),
('Arroz a la Cubana', 'Arroz, Huevos, Plátano, Tomate, Aceite', 'Cocina el arroz como de costumbre. Fría los huevos y el plátano. Sirve el arroz con el huevo y el plátano, y acompaña con salsa de tomate.', 25, 350,'images/arrozCubana.jpg'),
('Arroz de Champiñones', 'Arroz Arborio, Champiñones, Caldo, Cebolla, Queso', 'Sofríe la cebolla y champiñones, añade el arroz y el caldo poco a poco. Cocina hasta que el arroz esté cremoso. Agrega queso al final.', 30, 450,'images/arrozChampi.jpg'),
('Arroz con Leche', 'Arroz, Leche, Azúcar, Canela, Pasas', 'Cocina el arroz en leche con azúcar y canela. Cocina a fuego lento hasta que espese. Agrega pasas y sirve frío o caliente.', 40, 300,'images/arrozLeche.jpg'),
('Paella de Mariscos', 'Arroz, Mariscos, Pimiento, Azafrán, Caldo', 'Sofríe los pimientos y añade el arroz. Agrega los mariscos y el caldo con azafrán. Cocina hasta que el arroz esté tierno.', 35, 500,'images/plato_vacio.jpg'),
('Arroz Mexicano', 'Arroz, Tomate, Cebolla, Pimiento, Caldo', 'Sofríe cebolla y pimiento, agrega el arroz y los tomates triturados. Añade caldo y cocina hasta que el arroz esté cocido.', 30, 350,'images/arrozMexicana.jpg'),
('Pollo al Curry', 'Pechugas de pollo, Curry, Leche de coco, Cebolla, Espinacas', 'Sofríe la cebolla, añade el pollo y curry. Agrega leche de coco y espinacas. Cocina hasta que el pollo esté tierno.', 30, 450,'images/plato_vacio.jpg'),
('Ensalada César de Pollo', 'Pechugas de pollo, Lechuga, Queso parmesano, Aderezo César', 'Cocina el pollo a la parrilla y córtalo en tiras. Mezcla con lechuga, queso y aderezo. Sirve frío.', 20, 350,'images/plato_vacio.jpg'),
('Sopa de Pollo con Fideos', 'Pollo, Fideos, Zanahoria, Apio, Caldo', 'Hierve el pollo y desmenúzalo. Agrega fideos, zanahoria y apio al caldo. Cocina hasta que los fideos estén tiernos.', 40, 300,'images/plato_vacio.jpg'),
('Tikka Masala de Pollo', 'Pechugas de pollo, Yogur, Especias, Tomate, Cebolla', 'Marina el pollo en yogur y especias. Sofríe cebolla, agrega el pollo y tomate. Cocina hasta que esté cocido.', 45, 500,'images/plato_vacio.jpg'),
('Pollo a la Naranja', 'Pechugas de pollo, Jugo de naranja, Ajo, Miel, Salsa de soja', 'Marina el pollo en jugo de naranja, ajo y miel. Cocina en una sartén hasta dorar y caramelizar.', 30, 400,'images/plato_vacio.jpg'),
('Carne Bistec a la Parrilla', 'Carne, Sal, Pimienta, Aceite', 'Sazona el bistec con sal y pimienta. Cocina a la parrilla o sartén hasta el punto deseado.', 20, 350,'images/plato_vacio.jpg'),
('Estofado de Carne', 'Carne de res, Zanahorias, Cebolla, Caldo, Papas', 'Sofríe la carne, añade cebolla y zanahorias. Agrega caldo y cocina a fuego lento hasta que esté tierno.', 60, 400,'images/plato_vacio.jpg'),
('Carne al Vino', 'Carne de res, Vino tinto, Cebolla, Zanahorias, Especias', 'Dora la carne, añade cebolla y zanahorias. Vierte vino y cocina a fuego lento hasta que esté tierno.', 50, 500,'images/plato_vacio.jpg'),
('Chili con Carne', 'Carne molida, Frijoles, Tomate, Cebolla, Especias', 'Sofríe cebolla y carne, añade tomates y frijoles. Cocina a fuego lento con especias.', 40, 450,'images/plato_vacio.jpg'),
('Kebabs de Carne', 'Carne de res, Especias, Pimiento, Cebolla, Palitos', 'Corta la carne y vegetales en trozos, sazona y ensarta en palitos. Asa hasta que estén cocidos.', 25, 300,'images/plato_vacio.jpg');


