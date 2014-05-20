drop table monitor_results; 

create table monitor_results (
	id integer not null auto_increment,
	response_time integer, 
	file_size integer,

	creation_year varchar(20),
	creation_month varchar(20),
	creation_date varchar(20),
	creation_hour  varchar(20),
	created datetime,
	primary key(id)
	
) engine InnoDB; 



update monitor_results set creation_year = date_format(created, '%Y');
update monitor_results set creation_month =  date_format(created, '%Y-%m');
update monitor_results set creation_date =  date_format(created, '%Y-%m-%d');
update monitor_results set creation_hour =  date_format(created, '%Y-%m-%d-%H');


select creation_hour, avg(response_time) from monitor_results group by creation_hour order by creation_hour; 
select creation_hour, min(response_time) from monitor_results group by creation_hour order by creation_hour; 

select creation_hour,min(response_time),avg(response_time),  max(response_time) from monitor_results group by creation_hour order by creation_hour desc; 