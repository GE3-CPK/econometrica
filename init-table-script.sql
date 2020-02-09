create table COUNTRY
(
	ISO_CODE VARCHAR2(3) not null,
	NAME VARCHAR2(100) not null
)
/

create unique index COUNTRY_ISO_CODE_UINDEX
	on COUNTRY (ISO_CODE)
/

create unique index COUNTRY_NAME_UINDEX
	on COUNTRY (NAME)
/

alter table COUNTRY
	add constraint COUNTRY_PK
		primary key (ISO_CODE)
/

create table COUNTRY_DATASET
(
	START_YEAR VARCHAR2(4) not null,
	NAME VARCHAR2(255) not null,
	COUNTRY_CODE VARCHAR2(3)
		constraint COUNTRY_ISO_CODE_GDP_FK
			references COUNTRY,
	END_YEAR VARCHAR2(4) not null,
	DESCRIPTION VARCHAR2(512),
	DATASET_ID NUMBER generated as identity
		constraint COUNTRY_DATASET_PK
			primary key
)
/

create table COUNTRY_DATA
(
	DATA_YEAR VARCHAR2(4) not null,
	VALUE VARCHAR2(255) not null,
	DATASET NUMBER
		constraint DATASET_ID_FK
			references COUNTRY_DATASET
)
/

