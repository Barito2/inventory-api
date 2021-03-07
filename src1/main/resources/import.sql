INSERT INTO unit (id, is_deleted, code, description, created_date) VALUES (1, false,  'kg', 'Kilogram', '2001-01-01');
INSERT INTO unit (id, is_deleted, code, description, created_date) VALUES (2, false, 'l', 'Liter', '2001-01-01');

INSERT INTO item (id, is_deleted, name, price, unit_id, created_date) VALUES (1, false, 'Air Mineral', 3000, 2, '2001-01-01');
INSERT INTO item (id, is_deleted, name, price, unit_id, created_date) VALUES (2, false, 'Beras', 1, 6000, '2001-01-01');
INSERT INTO item (id, is_deleted, name, price, unit_id, created_date) VALUES (3, false, 'Gula Pasir', 10000, 1, '2001-01-01');
INSERT INTO item (id, is_deleted, name, price, unit_id, created_date) VALUES (4, false, 'Gula Merah', 5000, 1, '2001-01-01');
INSERT INTO item (id, is_deleted, name, price, unit_id, created_date) VALUES (5, false, 'Garam Halus', 3000, 1, '2001-01-01');
INSERT INTO item (id, is_deleted, name, price, unit_id, created_date) VALUES (6, false, 'Tepung Terigu', 6000, 1, '2001-01-01');
INSERT INTO item (id, is_deleted, name, price, unit_id, created_date) VALUES (7, false, 'Bawang Merah', 30000, 1, '2001-01-01');
INSERT INTO item (id, is_deleted, name, price, unit_id, created_date) VALUES (8, false, 'Bawang Putih', 40000, 1, '2001-01-01');
INSERT INTO item (id, is_deleted, name, price, unit_id, created_date) VALUES (9, false, 'Cabai Merah Kering', 50000, 1, '2001-01-01');
INSERT INTO item (id, is_deleted, name, price, unit_id, created_date) VALUES (10, false, 'Cabai Rawit Setan', 45000, 2, '2001-01-01');

INSERT INTO stock (item_id, is_deleted, quantity, total_price, created_date) VALUES (1, false, 600, 1800000, '2020-01-01');
INSERT INTO stock (item_id, is_deleted, quantity, total_price, created_date) VALUES (2, false, 500, 3000000, '2020-01-01');
INSERT INTO stock (item_id, is_deleted, quantity, total_price, created_date) VALUES (3, false, 500, 500000, '2020-01-01');