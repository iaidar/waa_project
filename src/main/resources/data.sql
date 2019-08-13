create table if not exists persistent_logins (
  username varchar_ignorecase(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
  );

INSERT INTO Role VALUES (1, 'ADMIN');
INSERT INTO Role VALUES (2, 'SELLER');
INSERT INTO Role VALUES (3, 'BUYER');

INSERT INTO User VALUES (0,1,'webstorewaa@gmail.com','$2a$10$5yC4meSZGDFC/bEWquhkAe.3B6B0kk5GJcu4dTN3O52G0jiBdauVa','admin');
INSERT INTO User_role VALUES (1,0);

-- for tests
INSERT INTO cart VALUES (10020);
INSERT INTO cart VALUES (10021);

INSERT INTO User VALUES (10000,1,'by_sennadj@esi.dz','$2a$10$5yC4meSZGDFC/bEWquhkAe.3B6B0kk5GJcu4dTN3O52G0jiBdauVa','buyer');
INSERT INTO User_role VALUES (3,10000);
INSERT INTO buyer VALUES (10001,10020,10000);



INSERT INTO User VALUES (10002,1,'ysennadj@esi.dz','$2a$10$5yC4meSZGDFC/bEWquhkAe.3B6B0kk5GJcu4dTN3O52G0jiBdauVa','buyer2');
INSERT INTO User_role VALUES (3,10002);
INSERT INTO buyer VALUES (10003,10021,10002);

INSERT INTO User VALUES (10004,1,'by_sennadj@esi.dz','$2a$10$5yC4meSZGDFC/bEWquhkAe.3B6B0kk5GJcu4dTN3O52G0jiBdauVa','seller1');
INSERT INTO User_role VALUES (2,10004);
INSERT INTO seller VALUES (10005,10004);

INSERT INTO User VALUES (10006,1,'by_sennadj@esi.dz','$2a$10$5yC4meSZGDFC/bEWquhkAe.3B6B0kk5GJcu4dTN3O52G0jiBdauVa','seller2');
INSERT INTO User_role VALUES (2,10006);
INSERT INTO seller VALUES (10007,10006);

INSERT INTO product VALUES (10008,1,'description 1',10,'product 1',10005);
INSERT INTO product VALUES (10009,1,'description 2',20,'product 2',10005);
INSERT INTO product VALUES (10010,1,'description 3',30,'product 3',10007);

