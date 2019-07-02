create database crow;
use crow;

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_PM');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

insert into category(id, description, name, color) values
(1, "Pour les projets liés à la nature", "Nature", "#28a745"),
(2, "Pour les projets liés à la technologie", "Tech", "#dc3545"),
(3, "Pour les projets solidaires et/ou citoyens", "Solidaire", "#ffc107");

insert into contributor(id, nickname, total, temp_amount, avatar) values
(1,"paul",0,0,"https://i.pravatar.cc/150?img=3"),
(2,"alice",0,0,"https://i.pravatar.cc/150?img=2");

insert into project(id, title, pitch, description, donation_current, donation_goal, submission_date, end_date, thumbnail_path, category_id, creator_id) values
(1, "Andes", "Envie d'un voyage inoubliable ?", "Description du projet de voyage dans les Andes", 0, 7000, '2019-06-01', '2019-07-15 00:00:00', "https://upload.wikimedia.org/wikipedia/commons/1/17/Cordillera_de_los_Andes.jpg", 1, 1),
(2, "Crepe-Party", "Pour ceux qui aiment les crêpes", "Description du projet Crepe-Party", 0, 2000, '2019-06-07', '2019-07-07 00:00:00', "https://www.fifteenspatulas.com/wp-content/uploads/2010/12/Crepes-Fifteen-Spatulas-1.jpg", 3, 1),
(3, "Concours de bouffe de bananes", "Tout est dans le titre!!", "Description du projet de bouffe de bananes", 0, 1500, '2019-06-08', '2019-07-08 00:00:00', "https://cdn.shopify.com/s/files/1/0665/4989/products/banane.png?v=1458596631", 3, 2),
(4, "Robot", "Un robot trop cool", "Description du projet Robot", 0, 20000, '2019-06-10', '2019-12-10 00:00:00', "https://images.newrepublic.com/3de3e180569a4793d166f90285ba1f3b1c3cb8c4.jpeg?w=800&q=65&dpi=2.625&fm=pjpg&h=481", 2, 2),
(5, "Course nocturne", "Une course de nuit !", "Description du projet de course nocturne", 0, 10000, '2019-06-25', '2019-07-25 00:00:00', "https://i.ytimg.com/vi/917pnQrog9g/maxresdefault.jpg", 1, 2);