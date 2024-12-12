DROP TABLE if exists public.assets;

CREATE TABLE public.assets (
	id serial primary key,
	date_time timestamp NOT NULL DEFAULT now(),
	simbolo varchar(255) NULL,
	descripcion varchar(255) NULL,
	pais varchar(255) NULL,
	mercado varchar(255) NULL,
	tipo varchar(255) NULL,
	plazo varchar(255) NULL,
	moneda varchar(255) NULL,
	industry varchar(255) NULL,
	cantidad float8 NULL,
	comprometido float8 NULL,
	puntosvariacion float8 NULL,
	variaciondiaria float8 NULL,
	ultimoprecio float8 NULL,
	ppc float8 NULL,
	gananciaporcentaje float8 NULL,
	gananciadinero float8 NULL,
	valorizado float8 NULL,
	parking bool NULL
);