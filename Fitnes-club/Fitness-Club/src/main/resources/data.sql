insert into users(username, password, enabled)
values ('user', '$2a$10$NM.X0whs2gmfOKoD600Mreq755TqlB1WcwRCE5PdpSnSgViIFjoE6',
        true),
       ('owner', '$2a$10$ufqRdihVRqm1M5FMoZsP0ODGhetqK2b4baWKK68iukSOb7WJiuJJC',
                true),
       ('admin', '$2a$10$Dz4QkEWUP3EL4ndMkT8/H.M46b8bvEhLU5ktv4YanokBy798Ev8e2',
        true);

insert into authorities(username, authority)
values ('owner', 'ROLE_ADMIN'),
       ('owner', 'ROLE_OWNER'),
       ('owner', 'ROLE_USER'),
       ('admin', 'ROLE_ADMIN'),
       ('admin', 'ROLE_USER'),
       ('user', 'ROLE_USER');