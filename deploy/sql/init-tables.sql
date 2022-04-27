create table ducks_products
(
	id int,
	color varchar(300) null,
	size varchar(500) null,
	stock int not null
);

create unique index ducks_products_id_uindex
	on ducks_products (id);

alter table ducks_products
	add constraint ducks_products_pk
		primary key (id);

alter table ducks_products modify id int auto_increment;


