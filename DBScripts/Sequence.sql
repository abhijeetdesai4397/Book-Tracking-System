create sequence seq_book_id
  start with 10000
  MAXVALUE 19999
  increment by 1
  nocache
  nocycle;
  
create sequence seq_request_id
  start with 20000
  MAXVALUE 29999
  increment by 1
  nocache
  nocycle;
  
  create sequence seq_emptracking_id
  start with 30000
  MAXVALUE 39999
  increment by 1
  nocache
  nocycle;