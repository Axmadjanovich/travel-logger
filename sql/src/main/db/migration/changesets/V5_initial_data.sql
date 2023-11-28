-- Default users
insert into travel_logger.users(full_name, passport_serial_number, birth_date)
VALUES ('Abduvohid Ergashev', 'AB4889006', '01.07.2000'),
       ('Hakimov Qodirjon', 'AB5560834', '10.01.1999');

-- Default vehicles
insert into travel_logger.vehicles (model, manufacturer, reg_number, owner_id)
values ('Gentra', 'Chevrolet', '01A766GB', 1),
       ('Tracker', 'Chevrolet', '01A031MA', 1),
       ('Cobalt', 'Chevrolet', '01Z566SB', 2);

-- Default travel logs
insert into travel_logger.travel_logs(created_date, vehicle_reg_number, owner_id, odometer_start, odometer_end, route, description)
values ('01.12.2023', '01A766GB', 1, 10500, 10572, 'Toshkent-Sirdaryo', 'Xizmat safari'),
       ('02.12.2023', '01A031MA', 1, 7200, 7220, 'Yunusobod-Sergeli', 'Biznes hamkorlar'),
       ('01.12.2023', '01Z566SB', 2, 34677, 35000, 'Toshkent-Andijon', 'Xizmat safari'),
       ('03.12.2023', '01A031MA', 1, 7220, 7600, 'Toshkent-Andijon', 'Hamkorlarni Andijonga olib borish'),
       ('05.12.2023', '01A031MA', 1, 7650, 8000, 'Andijon-Toshkent', 'Toshkentga qaytish'),
       ('03.12.2023', '01A766GB', 1, 10622, 10700, 'Sirdaryo-Toshkent', 'Xizmat safaridan qaytish');