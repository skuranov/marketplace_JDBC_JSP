CREATE TABLE Items (
	Item_ID integer NULL, 
	Seller_Id integer NOT NULL ,
  Title varchar2 (100) NOT NULL ,
	Description varchar2 (2000) NOT NULL ,
	Start_Price number NOT NULL ,
	Time_Left number NOT NULL ,
	Start_Bidding_Date date NOT NULL ,
	Buy_It_Now varchar2(1) NULL ,
	Bid_Increment number);
CREATE SEQUENCE items_seq;

Create Unique Index PK_Items
	On Items(Item_ID);
CREATE  INDEX Items_Seller_ID ON Items(Seller_ID);	
CREATE  INDEX Items_Start_Price ON Items(Start_Price);	
CREATE  INDEX Items_Start_Bidding_Date ON Items(Start_Bidding_Date);	
CREATE  INDEX Items_Buy_It_Now ON Items(Buy_It_Now);	

CREATE TABLE Bids (
	Bid_ID integer NULL,
	Bidder_Id integer NOT NULL ,
	Item_Id_Bid integer NOT NULL,
  Bid_Value number NOT NULL);

Create Unique Index PK_Bids
	On Bids(Bid_ID);
CREATE SEQUENCE Bids_seq;

CREATE  INDEX Bids_Bidder_Id ON Bids(Bidder_Id);	
CREATE  INDEX Bids_Item_Id_Bid ON Bids(Item_Id_Bid);	
CREATE TABLE Users (
	User_ID integer NULL,
	Full_Name varchar2 (200) NOT NULL,
	Billing_Adress varchar2 (300) NOT NULL,
	Login varchar2 (30) NOT NULL,
	Password varchar2 (50) NOT NULL,
  Role varchar2 (30) NOT NULL
  );
CREATE SEQUENCE users_seq;

Create Unique Index PK_Users
	On Users(User_ID);
Alter Table Users
	Add (CONSTRAINT PK_Users Primary Key(User_ID));
CREATE  INDEX Users_Full_Name ON Users(Full_Name);	
CREATE  INDEX Users_Billing_Adress ON Users(Billing_Adress);	
CREATE  INDEX Users_Login ON Users(Login);	
CREATE  INDEX Users_Password ON Users(Password);	
CREATE  INDEX Users_Role ON Users(Role);	
CREATE TABLE ROLES (
		Role_Name varchar2 (30) NOT NULL
	);
	
CREATE OR REPLACE TRIGGER items_inc 
BEFORE INSERT ON items 
FOR EACH ROW
BEGIN
  SELECT items_seq.NEXTVAL
  INTO   :new.Item_id
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER Bids_inc 
BEFORE INSERT ON Bids
FOR EACH ROW
BEGIN
  SELECT Bids_seq.NEXTVAL
  INTO   :new.Bid_id
  FROM   dual;
END;	
	
CREATE OR REPLACE TRIGGER users_inc 
BEFORE INSERT ON users 
FOR EACH ROW
BEGIN
  SELECT users_seq.NEXTVAL
  INTO   :new.user_id
  FROM   dual;
END;
	
Alter Table Roles
	Add (CONSTRAINT PK_Roles Primary Key(Role_Name));
Alter Table Bids
	Add (CONSTRAINT PK_Bids Primary Key(Bid_ID));
Alter Table Items
	Add (CONSTRAINT PK_Items Primary Key(Item_ID));
Alter Table Users
	Add (CONSTRAINT FK_Roles_Users FOREIGN KEY (Role) REFERENCES Roles (Role_Name));   
Alter Table Items
	Add (CONSTRAINT FK_Items_Users FOREIGN KEY (Seller_Id) REFERENCES Users (User_Id)); 
Alter Table Bids
	Add (CONSTRAINT FK_Bids_Users FOREIGN KEY (Bidder_Id) REFERENCES Users (User_Id)); 
Alter Table Bids
	Add (CONSTRAINT FK_Bids_Items FOREIGN KEY (Item_Id_Bid) REFERENCES Items (Item_Id)); 
	
Insert Into Roles VALUES('simpleUser'); 
Insert Into Roles VALUES('advancedUser'); 
Insert Into Users VALUES(2,'John Lenon','UK, London','jhonny','123123','simpleUser');
Insert Into Users VALUES(2,'Paul Makkartney','UK, Edinburgh','paulmak','222222','simpleUser');
Insert Into Users VALUES(2,'Ringo Star','UK, Glasgo','ringost','111111','simpleUser');
Insert Into Items VALUES(1,1,'Fender Guitar','Telecaster',1000,100,To_TimeStamp('12/7/2015', 'MM/DD/YYYY HH24:MI:SS'),'',10);
Insert Into Items VALUES(1,2,'Gibson Guitar','Les Paul',1200,50,To_TimeStamp('11/25/2015', 'MM/DD/YYYY HH24:MI:SS'),'',10);
Insert Into Items VALUES(1,3,'Tama','Simple drumkit',1500,0,To_TimeStamp('12/11/2014', 'MM/DD/YYYY HH24:MI:SS'),'1','');
Insert Into Items VALUES(1,2,'Hohner Harmonica','Golden Melody',20,30,To_TimeStamp('12/7/2015', 'MM/DD/YYYY HH24:MI:SS'),'',0.50);
Insert Into Items VALUES(1,2,'Hohner Harmonica','Golden Melody',20,1,To_TimeStamp('12/12/2015', 'MM/DD/YYYY HH24:MI:SS'),'1','');
Insert Into Bids VALUES(1,3,1,1050);
Insert Into Bids VALUES(1,2,1,1100);
Insert Into Bids VALUES(1,1,4,20);
/