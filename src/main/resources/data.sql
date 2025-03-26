INSERT INTO public.ROLES (role) VALUES
                                              ('ROLE_ADMIN'),
                                              ('ROLE_USER');

INSERT INTO public.ACCOUNTS (username, password, email, phone, status, createDate, updateDate, ROLEID) VALUES
                                                                                                                     ('ilhan', '$2a$10$fGO.odXMzmReI5kNUPkW4e.z.MYEn/YKcM1g2ORzeoPPZjjH.H0Xi', 'ilhan.5453@gmail.com', '05555555555', DEFAULT, NOW(), NULL, 1),
                                                                                                                     ('cem', '$2a$10$ngCz2oIlDJF9qVT5LE09LuyswA6AkD.dEBujtr9i.ctr899zENgOG', 'cem_85638@gmail.com', '05555555556', DEFAULT, NOW(), NULL, 2),
                                                                                                                     ('deniz', '$2a$10$8JB8gp41d.wCkAGNyOxVgOHg1G5VVkl2pFzbf02X8L/OFjATv8.vu', 'deniz_1298@gmail.com', '05555555557', DEFAULT, NOW(), NULL, 2),
                                                                                                                     ('ahmet', '$2a$10$rKCVXxAZ4PxY8QGbOuUrBuJU96rdA20EaqmtFaEDyzVUdKcp9j0Yq', 'ahmet123@gmail.com', '05555555558', DEFAULT, NOW(), NULL, 2),
                                                                                                                     ('furkan', '$2a$10$57ZZlufwwTsl4rX4gCo8rOr203KibczuahXCW5uDswZ3TVaxPTGZa', 'furkan3875@gmail.com', '01234567890', DEFAULT, NOW(), NULL, 2);

INSERT INTO public.POSTS (accountId, context, status, createDate, updateDate) VALUES
                                                                                            (1, 'Merhaba Dünya!', DEFAULT, NOW(), NULL),
                                                                                            (3, 'Merhaba la gardas ?', DEFAULT, NOW(), NULL),
                                                                                            (4, 'Bugun hava cok bozdu!', DEFAULT, NOW(), NULL),
                                                                                            (2, 'Hoca imza almaya basladi mi?', DEFAULT, NOW(), NULL),
                                                                                            (5, 'Naber müdür ?', DEFAULT, NOW(), NULL);

INSERT INTO public.INTERACTIONS (accountId, postId, context, type, status, createDate, updateDate) VALUES
                                                                                                                 (2, 2, 'Sana Da Merhaba Deniz!', 0, DEFAULT, NOW(), NULL),
                                                                                                                 (2, 1, ' ', 1, DEFAULT, NOW(), NULL),
                                                                                                                 (3, 3, 'Gercekten buz gibiydi!', 0, DEFAULT, NOW(), NULL),
                                                                                                                 (4, 4, ' ', 1, DEFAULT, NOW(), NULL),
                                                                                                                 (1, 1, ' ', 2, DEFAULT, NOW(), NULL);