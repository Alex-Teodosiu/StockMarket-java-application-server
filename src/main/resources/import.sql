insert into account (username, password, date_created, date_updated) values ('lucas', 'password', LOCALTIMESTAMP, LOCALTIMESTAMP);
insert into account (username, password, date_created, date_updated) values ('michal', 'password', LOCALTIMESTAMP, LOCALTIMESTAMP);

insert into transaction (stock_symbol, quantity, date_created, price, is_buy, account_id) values ('AAPL', 5, LOCALTIMESTAMP, 127, true, 1);
insert into transaction (stock_symbol, quantity, date_created, price, is_buy, account_id) values ('TSLA', 3, LOCALTIMESTAMP, 591, true, 1);
insert into transaction (stock_symbol, quantity, date_created, price, is_buy, account_id) values ('NVDA', 1, LOCALTIMESTAMP, 567, true, 2);
insert into transaction (stock_symbol, quantity, date_created, price, is_buy, account_id) values ('INTC', 5, LOCALTIMESTAMP, 60, true, 2);
insert into transaction (stock_symbol, quantity, date_created, price, is_buy, account_id) values ('TSLA', 2, LOCALTIMESTAMP, 700, false, 1);
insert into transaction (stock_symbol, quantity, date_created, price, is_buy, account_id) values ('NVDA', 1, LOCALTIMESTAMP, 450, false, 2);
insert into transaction (stock_symbol, quantity, date_created, price, is_buy, account_id) values ('INTC', 10, LOCALTIMESTAMP, 55, true, 2);


