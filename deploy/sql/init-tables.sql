create table ducks_products
(
	id int,
	color varchar(300) null,
	size varchar(500) null,
	slug varchar(500) null,
	reference varchar(500) null unique,
	stock int not null
);

create unique index ducks_products_id_uindex
	on ducks_products (id);

alter table ducks_products
	add constraint ducks_products_pk
		primary key (id);

alter table ducks_products modify id int auto_increment;


create table orders
(
	id int,
	client_name varchar(300) not null,
	qtt int not null
);

create unique index orders_id_uindex
	on orders (id);

alter table orders
	add constraint orders_pk
		primary key (id);

alter table orders modify id int auto_increment;

alter table orders
	add duck int not null;

alter table orders
	add constraint orders_ducks_products_id_fk
		foreign key (duck) references ducks_products (id)
			on update cascade on delete cascade;

alter table ducks_products
	add price double not null;





