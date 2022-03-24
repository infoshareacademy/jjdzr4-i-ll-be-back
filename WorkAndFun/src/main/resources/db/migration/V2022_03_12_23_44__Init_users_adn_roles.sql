
ALTER TABLE user
MODIFY COLUMN u_voivodeship VARCHAR(255);

insert into user (u_id, u_username, u_password, u_first_name, u_last_name
, u_phone_number, u_email, u_age, u_voivodeship, u_city, u_city_district)
values ('1',
        'admin',
        # haslo
        '{bcrypt}$2a$12$PMQXm6f3syTVg7ltyTVkMesqwSElEcJI/lL7O4sv2wwcuPjGEuYUq',
        'Kamila',
        'Strzelecka',
        '472384232',
        'kamstrzel@gmail.com',
        '18',
        'OPOLSKIE',
        'Opole',
        null),
       ('2',
        'MKowalski',
        # haslo1
        '{bcrypt}$2a$12$jRkfYN72seRNfvPwWzAvR.FrFUzFWCx/0cLolli1FvUjczU6cufz2',
        'Mariusz',
        'Kowalski',
        '987789654',
        'kowalski.m@gmail.com',
        '31',
        'DOLNOSLASKIE',
        'Jelenia Góra',
        null);

insert into
        users_roles
        (user_id, role_id)
    values
        ('1', '1'),
        ('1', '2'),
        ('2', '2');
