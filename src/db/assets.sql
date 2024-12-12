-- delete from assets;
select * from assets a
where date_time::date = '2023-09-23';

update assets
set date_time = '2023-09-21 00:00:00'
where date_time::date = '2023-09-22';

delete from assets where date_time >= '2023-09-19 00:00:00';
select * from assets a where date_time >= '2023-09-20 00:00:00';
update assets s set date_time = '2023-09-19 00:00:00' where date_time >= '2023-09-14 19:00:00';

update assets s set date_time = '2023-09-15 16:00:00' where date_time >= '2023-09-15 16:46:00';

select distinct(industry)
from assets a
order by 1;

select industry, sum(cantidad) as amount 
from assets a
where tipo = 'CEDEARS'
and date_time::date = '2023-09-22'
group by industry
order by amount desc

select industry, count(distinct simbolo) as amount, sum(cantidad) as shares_amount
from assets a
where tipo = 'CEDEARS'
and date_time::date = '2023-09-22'
group by industry
order by amount desc, shares_amount desc

select sum(cantidad) 
from assets a
where tipo = 'CEDEARS'
and date_time::date = '2023-09-22'

select 
	industry, 
	round(CAST(sum(cantidad * ppc) AS numeric), 0) as amount
from assets a
where tipo = 'CEDEARS'
group by industry
order by amount desc;

SELECT
    -- a.date_time::date,
    a.industry, 
    ROUND(CAST(SUM(a.cantidad * a.ppc) AS NUMERIC), 0) AS amount,
    ROUND((SUM(a.cantidad * a.ppc)::NUMERIC / total_sum.total_amount::NUMERIC) * 100, 2) AS percentage
FROM assets a
CROSS JOIN (
    SELECT 
    	-- date_time::date, 
    	SUM(cantidad * ppc) AS total_amount
    FROM assets
    WHERE tipo = 'CEDEARS'
    and date_time::date = '2023-09-22'
) AS total_sum
WHERE a.tipo = 'CEDEARS'
and a.date_time::date = '2023-09-22'
GROUP BY a.date_time::date, a.industry, total_sum.total_amount
ORDER BY a.date_time::date, amount DESC;


SELECT
    -- a.date_time::date,
   	a.pais, 
    ROUND(CAST(SUM(a.cantidad * a.ppc) AS NUMERIC), 0) AS amount,
    ROUND((SUM(a.cantidad * a.ppc)::NUMERIC / total_sum.total_amount::NUMERIC) * 100, 2) AS percentage
FROM assets a
CROSS JOIN (
    SELECT 
    	-- date_time::date, 
    	SUM(cantidad * ppc) AS total_amount
    FROM assets
    WHERE tipo = 'CEDEARS'
    and date_time::date = '2023-09-15'
) AS total_sum
WHERE a.tipo = 'CEDEARS'
and a.date_time::date = '2023-09-15'
GROUP BY a.date_time::date, a.pais, total_sum.total_amount
ORDER BY a.date_time::date, amount DESC;



select 
	descripcion, 
	round(CAST(sum(cantidad * ppc) AS numeric), 0) as amount
from assets a
where tipo = 'CEDEARS'
and industry = 'Technology'
group by descripcion 
order by amount desc;

select 
	descripcion, 
	round(CAST(sum(cantidad * ppc) AS numeric), 0) as amount
from assets a
where tipo = 'CEDEARS'
and industry = 'Technology'
and date_time::date = '2023-09-22'
group by descripcion 
order by amount desc;