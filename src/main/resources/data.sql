
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

INSERT INTO Receta (nombre, ingredientes, preparacion, tiempo, calorias)
VALUES
('Huevo Revueltos', 'Huevos, Sal, Mantequilla', 'Bate los huevos con una pizca de sal. En una sartén, derrite la mantequilla a fuego medio. Agrega los huevos batidos y revuelve suavemente hasta que estén cocidos, pero aún cremosos.', 10, 200),
('Arroz Blanco', 'Arroz, Agua, Sal', 'Enjuaga el arroz bajo agua fría. En una olla, añade el arroz, el doble de agua y una pizca de sal. Lleva a ebullición, luego cubre y reduce el fuego. Cocina a fuego lento durante 18-20 minutos, hasta que el agua se haya absorbido.', 20, 150),
('Carne Asada', 'Carne de res, Sal, Pimienta', 'Sazona la carne con sal y pimienta al gusto. Asa a la parrilla o en una sartén caliente durante unos 4-5 minutos por cada lado, dependiendo del grosor, hasta alcanzar el punto deseado.', 10, 300),
('Pasta al Ajo', 'Pasta, Ajo, Aceite de oliva', 'Cocina la pasta según las instrucciones del paquete. En una sartén, calienta aceite de oliva y añade ajo picado. Sofríe hasta que esté dorado, luego mezcla con la pasta cocida y sirve caliente.', 15, 400),
('Papas Fritas', 'Papas, Aceite, Sal', 'Pela y corta las papas en tiras. Calienta aceite en una freidora o sartén. Fría las papas en tandas hasta que estén doradas y crujientes. Escurre sobre papel absorbente y espolvorea con sal.', 25, 350),
('Tortilla Española', 'Huevos, Papas, Cebolla', 'Pela y corta las papas en rodajas finas. Sofríe las papas y la cebolla en aceite hasta que estén tiernas. Bate los huevos y mezcla con las papas. Vierte en la sartén y cocina a fuego lento hasta que esté firme por ambos lados.', 30, 450),
('Arroz con Pollo', 'Arroz, Pollo, Verduras', 'Sofríe trozos de pollo hasta dorarlos. Agrega arroz y verduras picadas. Añade agua y sazona al gusto. Cocina a fuego lento hasta que el arroz esté tierno y haya absorbido el líquido.', 40, 500),
('Ensalada de Atún', 'Atún, Lechuga, Tomate, Mayonesa', 'En un tazón, mezcla el atún desmenuzado, lechuga picada y tomate en cubos. Añade mayonesa al gusto y mezcla bien. Sirve fresca.', 10, 250),
('Pasta con Salsa de Tomate', 'Pasta, Tomates, Cebolla, Ajo', 'Cocina la pasta según las instrucciones. En una sartén, sofríe cebolla y ajo picados, luego agrega tomates triturados. Cocina hasta que espese y mezcla con la pasta cocida.', 20, 400),
('Puré de Papas', 'Papas, Mantequilla, Leche', 'Pela y corta las papas en trozos. Cocina en agua hirviendo hasta que estén tiernas. Escurre y machaca las papas con mantequilla y leche hasta obtener un puré suave. Sazona al gusto.', 30, 300),
('Sopa de Verduras', 'Zanahorias, Apio, Cebolla, Caldo de verduras, Sal, Pimienta', 'Sofríe la cebolla, zanahorias y apio en una olla. Añade el caldo y cocina a fuego medio hasta que las verduras estén tiernas.', 30, 150),
('Enchiladas de Pollo', 'Tortillas, Pollo desmenuzado, Salsa de tomate, Cebolla, Queso', 'Rellena las tortillas con pollo y cebolla, cubre con salsa y queso. Hornea a 180°C durante 20 minutos.', 40, 400),
('Tacos de Pescado', 'Pescado, Tortillas, Repollo, Salsa, Limón', 'Cocina el pescado a la parrilla. Sirve en tortillas con repollo y salsa. Exprime limón al gusto.', 25, 350),
('Quiche de Espinacas', 'Masa de tarta, Espinacas, Huevos, Queso, Leche', 'Mezcla espinacas, huevos, queso y leche. Vierte en la masa y hornea a 180°C durante 35 minutos.', 50, 300),
('Pollo al Limón', 'Pechugas de pollo, Limón, Ajo, Aceite de oliva, Sal', 'Marina el pollo con limón, ajo y aceite. Cocina en una sartén hasta dorar por ambos lados.', 25, 350),
('Hamburguesas Caseras', 'Carne molida, Pan de hamburguesa, Lechuga, Tomate, Cebolla', 'Forma las hamburguesas y cocínalas a la parrilla o sartén. Sirve en pan con los acompañamientos.', 30, 500),
('Guiso de Lentejas', 'Lentejas, Zanahorias, Cebolla, Ajo, Caldo', 'Sofríe cebolla y ajo, añade lentejas y caldo. Cocina a fuego lento hasta que las lentejas estén tiernas.', 45, 250),
('Tortitas de Avena', 'Avena, Huevo, Leche, Plátano, Canela', 'Mezcla todos los ingredientes y cocina en una sartén hasta dorar por ambos lados.', 20, 300),
('Curry de Garbanzos', 'Garbanzos, Leche de coco, Curry, Espinacas, Cebolla', 'Sofríe la cebolla, añade garbanzos y curry. Agrega leche de coco y cocina hasta espesar. Añade espinacas al final.', 30, 400),
('Brownies de Chocolate', 'Chocolate, Mantequilla, Azúcar, Huevos, Harina', 'Derrite chocolate y mantequilla. Mezcla con azúcar y huevos, luego añade la harina. Hornea a 180°C por 25 minutos.', 35, 250),
('Arroz a la Cubana', 'Arroz, Huevos, Plátano, Tomate, Aceite', 'Cocina el arroz como de costumbre. Fría los huevos y el plátano. Sirve el arroz con el huevo y el plátano, y acompaña con salsa de tomate.', 25, 350),
('Arroz de Champiñones', 'Arroz Arborio, Champiñones, Caldo, Cebolla, Queso', 'Sofríe la cebolla y champiñones, añade el arroz y el caldo poco a poco. Cocina hasta que el arroz esté cremoso. Agrega queso al final.', 30, 450),
('Arroz con Leche', 'Arroz, Leche, Azúcar, Canela, Pasas', 'Cocina el arroz en leche con azúcar y canela. Cocina a fuego lento hasta que espese. Agrega pasas y sirve frío o caliente.', 40, 300),
('Paella de Mariscos', 'Arroz, Mariscos, Pimiento, Azafrán, Caldo', 'Sofríe los pimientos y añade el arroz. Agrega los mariscos y el caldo con azafrán. Cocina hasta que el arroz esté tierno.', 35, 500),
('Arroz Mexicano', 'Arroz, Tomate, Cebolla, Pimiento, Caldo', 'Sofríe cebolla y pimiento, agrega el arroz y los tomates triturados. Añade caldo y cocina hasta que el arroz esté cocido.', 30, 350),
('Pollo al Curry', 'Pechugas de pollo, Curry, Leche de coco, Cebolla, Espinacas', 'Sofríe la cebolla, añade el pollo y curry. Agrega leche de coco y espinacas. Cocina hasta que el pollo esté tierno.', 30, 450),
('Ensalada César de Pollo', 'Pechugas de pollo, Lechuga, Queso parmesano, Aderezo César', 'Cocina el pollo a la parrilla y córtalo en tiras. Mezcla con lechuga, queso y aderezo. Sirve frío.', 20, 350),
('Sopa de Pollo con Fideos', 'Pollo, Fideos, Zanahoria, Apio, Caldo', 'Hierve el pollo y desmenúzalo. Agrega fideos, zanahoria y apio al caldo. Cocina hasta que los fideos estén tiernos.', 40, 300),
('Tikka Masala de Pollo', 'Pechugas de pollo, Yogur, Especias, Tomate, Cebolla', 'Marina el pollo en yogur y especias. Sofríe cebolla, agrega el pollo y tomate. Cocina hasta que esté cocido.', 45, 500),
('Pollo a la Naranja', 'Pechugas de pollo, Jugo de naranja, Ajo, Miel, Salsa de soja', 'Marina el pollo en jugo de naranja, ajo y miel. Cocina en una sartén hasta dorar y caramelizar.', 30, 400),
('Carne Bistec a la Parrilla', 'Carne, Sal, Pimienta, Aceite', 'Sazona el bistec con sal y pimienta. Cocina a la parrilla o sartén hasta el punto deseado.', 20, 350),
('Estofado de Carne', 'Carne de res, Zanahorias, Cebolla, Caldo, Papas', 'Sofríe la carne, añade cebolla y zanahorias. Agrega caldo y cocina a fuego lento hasta que esté tierno.', 60, 400),
('Carne al Vino', 'Carne de res, Vino tinto, Cebolla, Zanahorias, Especias', 'Dora la carne, añade cebolla y zanahorias. Vierte vino y cocina a fuego lento hasta que esté tierno.', 50, 500),
('Chili con Carne', 'Carne molida, Frijoles, Tomate, Cebolla, Especias', 'Sofríe cebolla y carne, añade tomates y frijoles. Cocina a fuego lento con especias.', 40, 450),
('Kebabs de Carne', 'Carne de res, Especias, Pimiento, Cebolla, Palitos', 'Corta la carne y vegetales en trozos, sazona y ensarta en palitos. Asa hasta que estén cocidos.', 25, 300);
