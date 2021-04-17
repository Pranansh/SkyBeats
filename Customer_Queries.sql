-- See all number of songs of producers
select count(producer_producerID), producer_producerID, producer.name
from songs,producer
where producer_producerID=producerID
group by producer_producerID
order by count(producer_producerID) desc;

-- See last 3 songs listened by the customer
select * from listens
where customer_customerID="C1005"
order by dateandtime desc limit 3;

-- Which producers song has been listened most
select producer_producerID, producer.name
from songs,producer,listens
where songs.producer_producerID=producerID
and listens.songs_songID=songs.songID
and listens.customer_customerID="C1005"
group by songs.producer_producerID 
having count(producer_producerID)=
(select max(mycount) from 
(select count(producer_producerID) mycount, producer_producerID, producer.name
from songs,producer,listens
where songs.producer_producerID=producerID
and listens.songs_songID=songs.songID
and listens.customer_customerID="C1005"
group by songs.producer_producerID) 
as t1);

-- see all songs by a particular producer's name
select songID,songs.name,producer_producerID,producer.name
from songs
inner join producer
where producer.name="Fay Conway"
and producer_producerID=producerID;

-- see customers playlists
select * from playlist 
where customer_customerID="C1002";

-- see songs in a particular playlist
select songID,songs.name
from `contains`,songs
where songs_songID=songID
and playlist_playlistID="X1058";

-- see all complains filed by the customer
select customerID,customer.name, reason 
from customer
inner join complain
where customer_customerID=customerID
and customerID="C1002";  
