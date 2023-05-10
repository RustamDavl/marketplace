insert into buyer
    (city, house_number, street, email, password, phone_number, register_at, birth_date, gender)
values (
        'Kazan', '16k1', 'Ametevo', 'easton12345@gmail.com', 'pass', '89179209061', current_timestamp, current_date, 'MALE'
       );
insert into seller ( email, password, phone_number, register_at, ownership_form)
values (
            'easton12345@gmail.com', 'pass', '89179209061', current_timestamp,'IP'
       );
insert into shop(goods_category, name, seller_id)
values (
        'MUSIC', 'my shop', 1
       );
