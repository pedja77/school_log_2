INSERT into db_school_2.role VALUES ('1', NOW(), b'0', 'ROLE_ADMIN', NOW(), '0');
INSERT into db_school_2.role VALUES ('2', NOW(), b'0', 'ROLE_STUDENT', NOW(), '0');
INSERT into db_school_2.role VALUES ('3', NOW(), b'0', 'ROLE_PARENT', NOW(), '0');
INSERT into db_school_2.role VALUES ('4', NOW(), b'0', 'ROLE_TEACHER', NOW(), '0');

INSERT into db_school_2.admin VALUES ('5', NOW(), b'0', 'Kole', 'Kostić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'admin', '0', '1');

INSERT into db_school_2.grade VALUES ('6', NOW(), b'0', NOW(), '1', '0');
INSERT into db_school_2.grade VALUES ('7', NOW(), b'0', NOW(), '2', '0');
INSERT into db_school_2.grade VALUES ('8', NOW(), b'0', NOW(), '3', '0');
INSERT into db_school_2.grade VALUES ('9', NOW(), b'0', NOW(), '4', '0');
INSERT into db_school_2.grade VALUES ('10', NOW(), b'0', NOW(), '5', '0');
INSERT into db_school_2.grade VALUES ('11', NOW(), b'0', NOW(), '6', '0');
INSERT into db_school_2.grade VALUES ('12', NOW(), b'0', NOW(), '7', '0');
INSERT into db_school_2.grade VALUES ('13', NOW(), b'0', NOW(), '8', '0');

INSERT into db_school_2.subject VALUES ('14', NOW(), b'0', 'Matematika', NOW(), '0', '5', '6');
INSERT into db_school_2.subject VALUES ('15', NOW(), b'0', 'Poznavanje prirode i društva', NOW(), '0', '2', '6');
INSERT into db_school_2.subject VALUES ('16', NOW(), b'0', 'Matematika', NOW(), '0', '5', '7');
INSERT into db_school_2.subject VALUES ('17', NOW(), b'0', 'Fizičko vaspitanje', NOW(), '0', '4', '7');
INSERT into db_school_2.subject VALUES ('18', NOW(), b'0', 'Srpski jezik', NOW(), '0', '6', '8');
INSERT into db_school_2.subject VALUES ('19', NOW(), b'0', 'Matematika', NOW(), '0', '5', '8');
INSERT into db_school_2.subject VALUES ('20', NOW(), b'0', 'Engleski jezik', NOW(), '0', '2', '9');
INSERT into db_school_2.subject VALUES ('21', NOW(), b'0', 'Španski jezik', NOW(), '0', '2', '9');
INSERT into db_school_2.subject VALUES ('22', NOW(), b'0', 'Istorija', NOW(), '0', '2', '10');
INSERT into db_school_2.subject VALUES ('23', NOW(), b'0', 'Geografija', NOW(), '0', '2', '10');
INSERT into db_school_2.subject VALUES ('24', NOW(), b'0', 'Biologija', NOW(), '0', '2', '11');
INSERT into db_school_2.subject VALUES ('25', NOW(), b'0', 'Muzičko vaspitanje', NOW(), '0', '2', '11');
INSERT into db_school_2.subject VALUES ('26', NOW(), b'0', 'Fizika', NOW(), '0', '3', '12');
INSERT into db_school_2.subject VALUES ('27', NOW(), b'0', 'Informatika', NOW(), '0', '5', '12');
INSERT into db_school_2.subject VALUES ('28', NOW(), b'0', 'Informatika', NOW(), '0', '6', '13');
INSERT into db_school_2.subject VALUES ('29', NOW(), b'0', 'Od igračke do računara', NOW(), '0', '1', '6');

INSERT into db_school_2.teacher VALUES ('30', NOW(), b'0', 'Pavle', 'Mirić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'pavle', '0', '4', '36');
INSERT into db_school_2.teacher VALUES ('31', NOW(), b'0', 'Petra', 'Kojić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'petra', '0', '4', '34');
INSERT into db_school_2.teacher VALUES ('32', NOW(), b'0', 'Julijana', 'Ostojić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'julija', '0', '4', '40');
INSERT into db_school_2.teacher VALUES ('33', NOW(), b'0', 'Miodrag', 'Zatezalo', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'zaki', '0', '4', '36');
INSERT into db_school_2.teacher VALUES ('34', NOW(), b'0', 'Rozalija', 'Dokić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'roki', '0', '4', '36');
INSERT into db_school_2.teacher VALUES ('35', NOW(), b'0', 'Ivan', 'Džunja', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'munja', '0', '4', '37');
INSERT into db_school_2.teacher VALUES ('36', NOW(), b'0', 'Zorana', 'Mihajlović', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'digi42', '0', '4', '42');
INSERT into db_school_2.teacher VALUES ('37', NOW(), b'0', 'Svetislav', 'Andrić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'bata', '0', '4', '36');

INSERT into db_school_2.teacher_subject VALUES ('38', b'0', '14', '30');
INSERT into db_school_2.teacher_subject VALUES ('39', b'0', '15', '30');
INSERT into db_school_2.teacher_subject VALUES ('40', b'0', '16', '32');
INSERT into db_school_2.teacher_subject VALUES ('41', b'0', '17', '33');
INSERT into db_school_2.teacher_subject VALUES ('42', b'0', '18', '33');
INSERT into db_school_2.teacher_subject VALUES ('43', b'0', '19', '34');
INSERT into db_school_2.teacher_subject VALUES ('44', b'0', '20', '35');
INSERT into db_school_2.teacher_subject VALUES ('45', b'0', '21', '35');
INSERT into db_school_2.teacher_subject VALUES ('46', b'0', '22', '35');
INSERT into db_school_2.teacher_subject VALUES ('47', b'0', '23', '33');
INSERT into db_school_2.teacher_subject VALUES ('48', b'0', '23', '31');
INSERT into db_school_2.teacher_subject VALUES ('49', b'0', '23', '35');
INSERT into db_school_2.teacher_subject VALUES ('50', b'0', '26', '36');
INSERT into db_school_2.teacher_subject VALUES ('51', b'0', '27', '37');
INSERT into db_school_2.teacher_subject VALUES ('52', b'0', '28', '34');
INSERT into db_school_2.teacher_subject VALUES ('53', b'0', '28', '32');

INSERT into db_school_2.student VALUES ('54', NOW(), b'0', 'Mile', 'Andrić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'lemi', '0', '2', '2010-02-15', '6');
INSERT into db_school_2.student VALUES ('55', NOW(), b'0', 'Ana', 'Andrić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'ana', '0', '2', '2010-02-15', '7');
INSERT into db_school_2.student VALUES ('56', NOW(), b'0', 'Mile', 'Mikić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'mile2', '0', '2', '2010-02-15', '8');
INSERT into db_school_2.student VALUES ('57', NOW(), b'0', 'Pera', 'Mikić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'pera', '0', '2', '2010-02-15', '9');
INSERT into db_school_2.student VALUES ('58', NOW(), b'0', 'Mile', 'Zorić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'mile3', '0', '2', '2010-02-15', '10');
INSERT into db_school_2.student VALUES ('59', NOW(), b'0', 'Milan', 'Tot', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'miki', '0', '2', '2010-02-15', '11');
INSERT into db_school_2.student VALUES ('60', NOW(), b'0', 'Maja', 'Pilić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'pcelica', '0', '2', '2010-02-15', '12');
INSERT into db_school_2.student VALUES ('61', NOW(), b'0', 'Dubravka', 'Kojić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'duca', '0', '2', '2010-02-15', '13');
INSERT into db_school_2.student VALUES ('61', NOW(), b'0', 'Mile', 'Andrić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'lemi1', '0', '2', '2010-02-15', '13');
INSERT into db_school_2.student VALUES ('63', NOW(), b'0', 'Ana', 'Andrić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'ana1', '0', '2', '2010-02-15', '12');
INSERT into db_school_2.student VALUES ('64', NOW(), b'0', 'Mile', 'Mikić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'mile21', '0', '2', '2010-02-15', '11');
INSERT into db_school_2.student VALUES ('65', NOW(), b'0', 'Pera', 'Mikić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'pera1', '0', '2', '2010-02-15', '10');
INSERT into db_school_2.student VALUES ('66', NOW(), b'0', 'Mile', 'Zorić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'mile31', '0', '2', '2010-02-15', '9');
INSERT into db_school_2.student VALUES ('67', NOW(), b'0', 'Milan', 'Tot', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'miki1', '0', '2', '2010-02-15', '8');
INSERT into db_school_2.student VALUES ('68', NOW(), b'0', 'Maja', 'Pilić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'pcelica1', '0', '2', '2010-02-15', '7');
INSERT into db_school_2.student VALUES ('69', NOW(), b'0', 'Dubravka', 'Kojić', '$2a$10$zMw./sfq5Ipn8OqXoUoSjecWCcbpy2IGsFN0mIV36mLn3jG4MMx8m', NOW(), 'duca1', '0', '2', '2010-02-15', '6');

INSERT into db_school_2.student_subject VALUES ('70', b'0', '54', '14');
INSERT into db_school_2.student_subject VALUES ('70', b'0', '54', '15');
INSERT into db_school_2.student_subject VALUES ('71', b'0', '55', '14');
INSERT into db_school_2.student_subject VALUES ('71', b'0', '54', '15');
INSERT into db_school_2.student_subject VALUES ('72', b'0', '56', '16');
INSERT into db_school_2.student_subject VALUES ('72', b'0', '54', '17');
INSERT into db_school_2.student_subject VALUES ('73', b'0', '57', '15');
INSERT into db_school_2.student_subject VALUES ('74', b'0', '58', '16');
INSERT into db_school_2.student_subject VALUES ('75', b'0', '59', '16');
INSERT into db_school_2.student_subject VALUES ('76', b'0', '60', '17');
INSERT into db_school_2.student_subject VALUES ('77', b'0', '61', '17');
INSERT into db_school_2.student_subject VALUES ('78', b'0', '62', '18');
INSERT into db_school_2.student_subject VALUES ('79', b'0', '63', '18');
INSERT into db_school_2.student_subject VALUES ('80', b'0', '64', '19');
INSERT into db_school_2.student_subject VALUES ('81', b'0', '65', '19');
INSERT into db_school_2.student_subject VALUES ('82', b'0', '66', '20');
INSERT into db_school_2.student_subject VALUES ('83', b'0', '67', '20');
INSERT into db_school_2.student_subject VALUES ('84', b'0', '68', '21');
INSERT into db_school_2.student_subject VALUES ('85', b'0', '69', '21');

update hibernate_sequence set next_val = 90;