create table if not exists persistent_logins (
  username varchar_ignorecase(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
  );

INSERT INTO Role VALUES (1, 'ADMIN');
INSERT INTO Role VALUES (2, 'SELLER');
INSERT INTO Role VALUES (3, 'BUYER');

INSERT INTO User VALUES (0,1,'$2a$10$5yC4meSZGDFC/bEWquhkAe.3B6B0kk5GJcu4dTN3O52G0jiBdauVa','admin');
-- INSERT INTO User VALUES (1,1,'$2a$10$5yC4meSZGDFC/bEWquhkAe.3B6B0kk5GJcu4dTN3O52G0jiBdauVa','seller');
INSERT INTO User_role VALUES (1,0);
-- INSERT INTO User_role VALUES (2,1);