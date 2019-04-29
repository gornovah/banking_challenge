INSERT INTO CUSTOMER(id, user, password) VALUES (cus_seq.nextval, 'TestUser', '123456789');

INSERT INTO DEPOSIT(id, AMOUNT, CUSTOMER_ID) VALUES (deposit_generator.nextval, 12.5, 1);
INSERT INTO DEPOSIT(id, AMOUNT, CUSTOMER_ID) VALUES (deposit_generator.nextval, 10, 1);
INSERT INTO DEPOSIT(id, AMOUNT, CUSTOMER_ID) VALUES (deposit_generator.nextval, 100, 1);
INSERT INTO DEPOSIT(id, AMOUNT, CUSTOMER_ID) VALUES (deposit_generator.nextval, 133, 1);
