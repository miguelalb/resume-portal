insert into user (id, user_name, password, active, roles) values
(1, 'foo', 'foo', true, 'USER'),
(2, 'bar', 'bar', true, 'USER');

insert into user_profile (id, user_name, theme, summary) values
(1, 'foo', 1, 'This is the summary for user [foo]'),
(2, 'bar', 2, 'This is the summary for user [bar]'),
(3, 'fizz', 3, 'This is the summary for user [fizz]'),
(4, 'buzz', 4, 'This is the summary for user [buzz]');