select earning from producer where producerID="P1000";

select views from producer where producerID="P1000";

select songs.name , songs.rating from songs 
inner join producer 
on producer.producerID=songs.producer_producerID
where producerID="P1004" order by rating DESC
limit 2;

select manager.name , manager.managerID , producer.producerID from manager
inner join producer
where producer.manager_managerID=manager.managerID and producerID="P1000";

select songs.producer_producerID , songs.adcompany_adID , adcompany.name
from songs 
inner join adcompany
where adcompany.adID=songs.adcompany_adID and songs.producer_producerID="P1000";

select songs.producer_producerID as producerID ,songs.name as song_name
from songs 
inner join producer
where songs.producer_producerID=producer.producerID and songs.producer_producerID="P1000";

select songs.producer_producerID , sum(songs.views)
from songs 
inner join producer
where songs.producer_producerID=producer.producerID and songs.producer_producerID="P1000";

select songs.producer_producerID , songs.name,songs.songID
from songs 
inner join producer
where songs.producer_producerID=producer.producerID and songs.producer_producerID="P1000";

insert into songs values ("S222","yeh",5.7,0,0,"P1000","A1002");
delete from songs where songs.producer_producerID="P1000" and songs.name="yeh";

select songs.name,rank()over(order by rating desc) as rating,producer_producerID
from songs where songs.producer_producerID="P1000";
