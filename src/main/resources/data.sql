insert into user (id, user_name, password, active, roles) values
(1, 'einstein', 'einstein', true, 'USER'),
(2, 'newton', 'newton', true, 'USER'),
(3, 'jissa', 'jissa', true, 'USER'),
(4, 'miguel', 'miguel', true, 'USER');

insert into user_profile (id, user_name, theme, summary, first_name, last_name, email, phone, designation) values
(1, 'einstein', 1, 'Developed the theory of relativity, one of the two pillars of modern physics. My work is also known for its influence on the philosophy of science.', 'Albert', 'Einstein', 'a.einstein@email.com', '123-456-7890', 'Physicist'),
(2, 'newton', 2, 'Widely recognised as one of the most influential scientists of all time and as a key figure in the scientific revolution', 'Isaac', 'Newton', 'newton@email.com', '123-456-7890', 'Mathematician, physicist, astronomer, theologian, and author'),
(3, 'jissa', 3, 'I have worked as a Digital Marketing Manager for five years. During this time I have been an important and hard-working member of the team and showed my ability to work under pressure to deliver on company targets.', 'Jissa', 'Lora', 'j.lora@email.com', '123-456-7890', 'Digital Marketer'),
(4, 'miguel', 4, 'Dynamic and creative software engineer with over 3 years of experience in producing high-quality and extensible code. Eager to offer superb analytical and computer skills to help your company grow its client base.', 'Miguel', 'Acevedo', 'm.acevedo@email.com', '123-456-7890', 'Software Engineer');