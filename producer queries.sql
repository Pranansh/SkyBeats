/*displaying earning of a specific producer */
select earning from producer where producerID="P1000";

/*displaying top 2 songs of producer */
select songs.name , songs.rating from songs 
inner join producer 
on producer.producerID=songs.producer_producerID
where producerID="P1000" order by rating DESC
limit 2;

/* displaying manager of the producer */
select manager.name , manager.managerID , producer.producerID from manager
inner join producer
where producer.manager_managerID=manager.managerID and producerID="P1000";

/* Sponsors of the producer  */
select songs.producer_producerID , songs.adcompany_adID , adcompany.name
from songs 
inner join adcompany
where adcompany.adID=songs.adcompany_adID and songs.producer_producerID="P1000";

/* displaying sum of  views of a specific producer */
select songs.producer_producerID , sum(songs.views)
from songs 
inner join producer
where songs.producer_producerID=producer.producerID and songs.producer_producerID="P1000";

/* Displaying songs produced by specific producer */
select songs.producer_producerID , songs.name,songs.songID
from songs 
inner join producer
where songs.producer_producerID=producer.producerID and songs.producer_producerID="P1000";

/* inserting and deleting songs produced by a producer */
insert into songs values ("S222","yeh",5.7,0,0,"P1000","A1002");
delete from songs where songs.producer_producerID="P1000" and songs.name="yeh";

/* displaying songs of a producer by rank */
select songs.name,rank()over(order by rating desc) as rating,producer_producerID
from songs where songs.producer_producerID="P1000";

/* inserting and deleting  a producer */
insert into producer values ("P1100","Hulk",65544,"M1000");
delete from producer where producerID="P1100"
