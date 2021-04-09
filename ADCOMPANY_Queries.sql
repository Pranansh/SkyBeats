
/*  view ratind of all songs along with their views is the top query
to make it view ratings and views of a specific song put that name 
of that song in place of "Quisque nonummy" in the second query below
*/
select name , rating , views from songs;
select name, rating, views from songs where STRCMP(songs.`name`, "Quisque nonummy") = 0 ;

/*
Query1: Total funds invested by all the ad companies , so it diplays the adcomapy name 
and the their respectives total investment in skybeats
Query 2: A specific companies wants to see it's total investment in skybeats, just place 
the id of that company in the palce where "A1011" is witten 
*/
select adID,adcompany.`name` , sum(funds) as Total_Funds_Invested 
from adcompany,songs
where songs.adcompany_adID = adcompany.adID
 group by adID ;

Select tab.`name`, tab.adID, tab.Total_Funds_Invested
from 
(
select adID,adcompany.`name` , sum(funds) as Total_Funds_Invested 
from adcompany,songs 
where songs.adcompany_adID = adcompany.adID 
group by adID 
) as tab
where 
STRCMP(tab.adID,"A1011")=0 ;

/* below is the query for number of songs sponsored , here I have taken a specific 
ad company id to write the query, so which ever company is querin write the 
id of that company in the place where "A1034"
Quer1: shows the names of all the songs sponsored
Quer2: show all the count of the songs sponsored*/
select name from songs where STRCMP(adcompany_adID,"A1034")=0 ;
select count(songID) as Songs_Sponsored from songs where STRCMP(adcompany_adID,"A1034")=0 ;

/*
The Query tells the top 5 highest attended live streams along with their  producers
*/
select producer.name as producer_name, Tab.producer_producerID as producer_id,Tab.noofattendees,Tab.livestreamId
from 
(
select * from livestream order by noofattendees desc limit 5
) as Tab, producer
where
Tab.producer_producerID=producer.producerID;


/*
Query1: to search songs by specific rating  range, 
for example purpose I have put rating range as between 5 and 10, 
but change these value as per what the user wants the range, 
for lower limit replace 5 and higher limit replace 10

Query2: to search songs by specific views  range, 
for example purpose I have put rating range as between 1000 and 5000, 
but change these value as per what the user wants the range, 
for lower limit replace 1000 and higher limit replace 5000


*/
select songID, name, rating
from songs
where
rating >=5 and rating<=10;

select songID, name, views
from songs
where
views >=1000 and rating<=5000;

/* highest rated and highest viewed songs quary is written below */
select name, max(rating) as highest_rated from songs; 
select name, max(views) as highest_viewed from songs;


/*Highest sponsored sponsored song in total, it is not specific to the company*/ 
select max(funds) as Highest_sponsored , name from adcompany ; 

/*
It gives amongst all the songs which has got the hightest funds, 
it returns that as the highest sponsored song but since all the ad company are 
spending same amount in different songs so it not possible to calculate the company 
specific highest sponsored song, as all songs sponsored by the ad company at same rate
*/

select  adID,adcompany.`name` as adCompany , songs.`name` as song_name, songID, funds
from adcompany,songs 
where songs.adcompany_adID = adcompany.adID 
Order by funds desc
limit 1 ;
