INSERT INTO unit (id, code, description, created_date) VALUES (1, 'kg', 'Kilogram', '2001-01-01');
INSERT INTO unit (id, code, description, created_date) VALUES (2, 'l', 'Liter', '2001-01-01');

INSERT INTO item (id, name, price, unit_id, created_date) VALUES (1, 'Air Mineral', 3000, 2, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (2, 'Beras', 1, 6000, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (3, 'Gula Pasir', 10000, 1, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (4, 'Gula Merah', 5000, 1, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (5, 'Garam Halus', 3000, 1, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (6, 'Tepung Terigu', 6000, 1, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (7, 'Bawang Merah', 30000, 1, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (8, 'Bawang Putih', 40000, 1, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (9, 'Cabai Merah Kering', 50000, 1, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (10, 'Cabai Rawit Setan', 45000, 2, '2001-01-01');

INSERT INTO stock (item_id, quantity, created_date) VALUES (1, 6000, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (2, 500, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (3, 500, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (4, 100, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (5, 500, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (6, 250, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (7, 100, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (8, 100, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (9, 50, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (10, 50, '2020-01-01');