CREATE TABLE S_USER_ROLE
(
  ROLE_ID  NUMBER(19),
  USER_ID  NUMBER(19)
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
NOMONITORING;


ALTER TABLE S_USER_ROLE ADD (
  CONSTRAINT S_USER_ROLE_PK
  PRIMARY KEY
  (ROLE_ID, USER_ID));
  
SET DEFINE OFF;
Insert into S_USER_ROLE
   (ROLE_ID, USER_ID)
 Values
   (595, 604);
Insert into S_USER_ROLE
   (ROLE_ID, USER_ID)
 Values
   (594, 604);
Insert into S_USER_ROLE
   (ROLE_ID, USER_ID)
 Values
   (596, 604);
Insert into S_USER_ROLE
   (ROLE_ID, USER_ID)
 Values
   (597, 604);
Insert into S_USER_ROLE
   (ROLE_ID, USER_ID)
 Values
   (598, 604);
Insert into S_USER_ROLE
   (ROLE_ID, USER_ID)
 Values
   (599, 604);
Insert into S_USER_ROLE
   (ROLE_ID, USER_ID)
 Values
   (600, 604);
Insert into S_USER_ROLE
   (ROLE_ID, USER_ID)
 Values
   (601, 604);
Insert into S_USER_ROLE
   (ROLE_ID, USER_ID)
 Values
   (602, 604);
COMMIT;
