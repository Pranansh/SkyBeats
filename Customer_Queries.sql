-- See all number of songs of producers
select producer_producerID, count(producer_producerID), producer.name
from songs
inner join producer
on producer_producerID=producerID
group by producer_producerID
order by count(producer_producerID) desc;

-- See last 3 songs listened by the customer
select * from listens
where customer_customerID="C1005"
order by dateandtime desc limit 3;

-- Which producers song has been listened most
(select max(mycount), t1.name from 
(select count(producer_producerID) mycount,producer.name
from songs
inner join producer
inner join listens
on (songs.producer_producerID=producerID
and listens.songs_songID=songs.songID)
where listens.customer_customerID="C1005"
group by songs.producer_producerID) 
as t1);

-- see all songs by a particular producer's name
select songID,songs.name,producer_producerID,producer.name
from songs
inner join producer on producer_producerID=producerID
where producer.name="Fay Conway";

-- see customers playlists
select * from playlist 
where customer_customerID="C1002";

-- see songs in a particular playlist
select songID,songs.name
from `contains` 
inner join songs
on songs_songID=songID
where playlist_playlistID="X1058";

-- see all complains filed by the customer
select customerID,customer.name, reason 
from customer
inner join complain
on customer_customerID=customerID
where customerID="C1002";  
