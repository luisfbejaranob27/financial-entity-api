
INSERT INTO clients (identification_type, identification_number, names, surnames, email, birth_date) VALUES ('CC', '1017128469', 'Luis Ferley', 'Bejarano Buritica', 'luisfbejaranob@outlook.com', '1986-04-27');


INSERT INTO products (account_type, account_number, status, balance, gmf_exempt) VALUES ('SAVINGS_ACCOUNT', '5312345678', 'ACTIVE', 1000000.0, true);
INSERT INTO products (account_type, account_number, status, balance, gmf_exempt) VALUES ('CURRENT_ACCOUNT', '3312345678', 'ACTIVE', 1000000.0, true);

INSERT INTO transactions (transaction_type, origin_account, destination_account, amount) VALUES ('CONSIGNMENT', '5312345678', '5312345678', 250000.0)