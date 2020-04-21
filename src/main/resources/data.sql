insert into user(id, first_name, last_name, email, password, phone_Number) values
(1, 'Andżej', 'Andżejewski', 'andzej@wp.pl', '{noop}eeeee', '364125987' ),
(2, 'Justyna','Kamka',  'justynka@wp.pl', '{noop}costam1234', '145876984'),
(3, 'Piotron', 'Mateńka', 'piotron2000@wp.pl', '{noop}niewiem', '136987451'),
(4, 'Jan', 'Kowalski', 'jankowalski@wp.pl', '{noop}jan', '478936541'),
(5, 'Zygfryd', 'DeLowe', 'krzyzacy@onet.pl', '{noop}zigi', '123645872'),
(6, 'Andżelika', 'Andżelewska', 'andzela@gmail.com', '{noop}lili', '253147895');


insert into car (id, producer, model, year_of_production, vin, registration, user_id) values
(1, 'Honda', 'Civic', 2006, 'TBT123234S', 'LLU12345', 3),
(2, 'Fiat', 'Siena', 2002, 'HWD12124', 'NPI123456', 2),
(3, 'Volkswagen', 'Passat', 1998,'NKWD1234', 'LRA12345', 1),
(4, 'Porsche', '911 Turbo', 1982, 'HWDP1234', 'LLU54321', 3),
(5, 'Citroen', 'Saxo', 2001, '2323213fdfd434', 'Lu43872', 4),
(6, 'Ford', 'Escort', 1996, 'JKSDFN24LK45', 'LLU8706', 5),
(7, 'Ford', 'Mondeo', 2004, 'KLMSDHN768LD', 'WRE2832382', 6),
(8, 'Audi', 'A4', 2001, 'JDHGF23723POI', 'LU462831', 1),
(9, 'Volkswagen', 'Golf', 2009, 'JSHF263JD2','NPI232323', 2),
(10, 'Toyota', 'Corolla', 2005, 'GFDA134KJ545', 'LLU42312', 4);


insert into commission (id, description, price, start, end, user_id, car_id) values
(1, 'Przegląd okresowy, wymiana płynów', 300, '2019-10-08 15:00:00', '2020-10-10 15:00:00', 3, 1),
(2, 'Uszczelka pod głowicą, remont silnika', 1000, '2019-10-08 15:00:00', null , 2, 2),
(3, 'wymiana tarcz i klocków, przód', 0, '2020-04-10 15:00:00', null, 4, 5),
(4, 'wymiana chłodnicy', 0, '2020-03-10 15:00:00', null , 2, 9),
(5, 'Przegląd okresowy, montaż wycieraczek', 220, '2020-04-10 15:00:00', '2020-04-12 15:00:00', 6, 7),
(6, 'wymiana tarcz i klocków, tył', 550, '2020-02-10 15:00:00', '2020-02-10 15:00:00', 5, 6),
(7, 'wymiana zawieszenia', 650, '2020-01-10 15:00:00', '2020-01-13 15:00:00', 1, 8),
(8, 'wymiana kół i śrub', 120, '2020-04-15 15:00:00', '2020-04-15 17:00:00', 1, 3),
(9, 'Przegląd okresowy, wymiana płynów', 0, '2020-04-10 15:00:00', null, 4, 10),
(10, 'geometria osi przedniej', 500, '2020-02-07 15:00:00', null, 5, 6),
(11, 'Przegląd okresowy, wymiana płynów', 250, '2020-04-10 15:00:00', '2020-04-10 15:00:00', 3, 4),
(12, 'Przegląd okresowy, wymiana płynów', 300, '2020-04-10 15:00:00', '2020-04-10 15:00:00', 3, 1);


insert into user_role(id, role, description) VALUES
(1,'ROLE_USER', 'default role for user'),
(2, 'ROLE_ADMIN', 'admin role, can control orders');

insert into role_user(user_id, role_id) values
(1,1),
(2,1),
(3,2),
(4,1),
(5,1),
(6,1);
