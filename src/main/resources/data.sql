insert into user(id, first_name, last_name, email, password) values
(1, 'Andżej', 'Andżejewski', 'andzej@wp.pl', 'chuj1234'),
(2, 'Justynka','Kamka',  'justynka@wp.pl', 'costam1234'),
(3, 'Piotron', 'Mateńka', 'piotron2000@wp.pl', 'niewiem1234');

insert into car (id, producer, model, year_of_production, vin, registration, user_id) values
(1, 'Honda', 'Civic', 2006, 'TBT123234S', 'LLU12345', 3),
(2, 'Fiat', 'Siena', 2002, 'HWD12124', 'NPI123456', 2),
(3, 'Volkswagen', 'Passat', 1998,'NKWD1234', 'LRA12345', 1),
(4, 'Porsche', '911 Turbo', 1982, 'HWDP1234', 'LLU54321', 3);


insert into commission (id, description, price, start, end, user_id, car_id) values
(1, 'Przegląd okresowy, wymiana płynów', 300, '2017-10-08 15:00:00', '2017-10-10 15:00:00', 3, 1),
(2, 'Uszczelka pod głowicą, remont', 1000, '2017-10-08 15:00:00', null , 2, 2);

insert into user_role(role, description) VALUES ('ROLE_USER', 'default role for user');