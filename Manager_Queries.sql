-- View list of Producers being managed by them
SELECT   manager_managerID,producer.producerID,producer.views
FROM        manager 
INNER JOIN  producer  
ON      producer.manager_managerID= manager.managerID;

-- average of total number of views of all the producer under a manager
SELECT   manager_managerID, ROUND(AVG(CAST(producer.views AS FLOAT)), 2)
FROM        manager 
INNER JOIN  producer  
ON     producer.manager_managerID= manager.managerID
GROUP BY manager.managerID;


-- number of producer under a manager
SELECT   manager_managerID, COUNT(producer.producerID),manager.numberofproducer
FROM        manager 
INNER JOIN  producer  
ON     producer.manager_managerID= manager.managerID
GROUP BY manager.managerID;

-- view rating of all songs under the manager
SELECT   manager_managerID, ROUND(AVG(CAST(songs.rating AS FLOAT)), 2)
FROM        manager 
INNER JOIN  producer  
ON     producer.manager_managerID= manager.managerID
INNER JOIN  songs 
ON     producer.producerID= producer_producerID
GROUP BY manager.managerID;

-- Earnings
SELECT managerID , earnings FROM manager; 

-- the producer with highest rating under a manager

SELECT   manager_managerID , max(songs.rating) , producer_producerID ,songs.songID
FROM     manager 
INNER JOIN  producer  
ON     producer.manager_managerID= manager.managerID
INNER JOIN  songs 
ON     producer.producerID= producer_producerID 
GROUP BY manager.managerID
ORDER BY manager.managerID;

-- total views of a single producer
SELECT   producer.producerID, SUM(songs.views)
FROM        producer
INNER JOIN  songs
ON      producer.producerID=  songs.producer_producerID 
GROUP BY producer.producerID
ORDER BY producer.producerID;
 
-- total views of a single manager
SELECT   manager_managerID , sum(songs.views)
FROM     manager 
INNER JOIN  producer  
ON     producer.manager_managerID= manager.managerID
INNER JOIN  songs 
ON     producer.producerID= producer_producerID 
GROUP BY manager.managerID
ORDER BY manager.managerID;

select * from producer where manager_managerID="M1003";
Select * from songs where songs.producer_producerID="P1076";

Update Downloads,Distributors
Set Downloads.Incoming_URL=Distributors.Incoming_URL
Where Downloads.Album_Id=Distributors.Album_Id;
