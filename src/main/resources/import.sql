INSERT INTO USER (ID, USER_ID, PASSWORD, NAME, EMAIL, CREATE_DATE) VALUES (1, 'heidencena1', '12', '유원선', 'heidencena1@gmail.com', CURRENT_TIMESTAMP());
INSERT INTO USER (ID, USER_ID, PASSWORD, NAME, EMAIL, CREATE_DATE) VALUES (2, 'heidencena12', '123', '유원성', 'heidencena1@naver.com', CURRENT_TIMESTAMP());

INSERT INTO QUESTION (ID, WRITER_ID, TITLE, CONTENTS, CREATE_DATE, COUNT_OF_ANSWER) VALUES (1, 1, '클래스와 객체의 차이?', '클래스는 객체를 만드는 틀, 객체는 클래스를 바탕으로 메모리에 올라온 실체가 맞나요', CURRENT_TIMESTAMP(), 0);