-- number of producer under a manager
SELECT   manager_managerID, COUNT(producer.producerID)
FROM        manager 
INNER JOIN  producer  
ON     producer.manager_managerID= manager.managerID
WHERE manager.managerID='M1003'
GROUP BY manager.managerID;

-- View list of Producers being managed by a manager
SELECT   manager_managerID,producer.producerID
FROM        manager 
INNER JOIN  producer  
ON      producer.manager_managerID= manager.managerID
WHERE manager.managerID='M1003';

-- Earnings
SELECT managerID , earnings FROM manager WHERE manager.managerID='M1003'; 

-- the producer with highest rating under a manager
SELECT   producer.manager_managerID,producer.producerID,songs.songID,songs.rating
FROM        producer
INNER JOIN  songs
ON      producer.producerID=  songs.producer_producerID 
WHERE producer.manager_managerID='M1003'
GROUP BY producer.producerID
ORDER BY songs.rating desc
limit 1;

-- the producer with highest views under a manager
SELECT   producer.manager_managerID,producer.producerID,sum(songs.views)
FROM        producer
INNER JOIN  songs
ON      producer.producerID=  songs.producer_producerID 
WHERE producer.manager_managerID='M1003'
GROUP BY producer.producerID
ORDER BY songs.views desc
limit 1;

-- the performance of each producer under a manager
SELECT   producer.producerID, SUM(songs.views),producer.manager_managerID,songs.songID ,songs.rating
FROM        producer
INNER JOIN  songs
ON      producer.producerID=  songs.producer_producerID 
WHERE producer.manager_managerID='M1003'
GROUP BY producer.producerID
ORDER BY songs.views;
 
-- total views of a single manager
SELECT   manager_managerID , sum(songs.views)
FROM     manager 
INNER JOIN  producer  
ON     producer.manager_managerID= manager.managerID
INNER JOIN  songs 
ON     producer.producerID= producer_producerID 
WHERE manager.managerID='M1003'
GROUP BY manager.managerID
ORDER BY songs.views;

-- information of all producer's livestream under a manager
SELECT   manager_managerID , producer.producerID,livestream.livestreamID, livestream.noofattendees
FROM     manager 
INNER JOIN  producer  
ON     producer.manager_managerID= manager.managerID
INNER JOIN  livestream
ON     producer.producerID= producer_producerID 
WHERE manager.managerID='M1003';

-- update producers earnings
UPDATE producer
SET producer.earning = 1233
WHERE producer.producerID="P1002" and manager_managerID="M1038";

-- add a new manager to the manager table
INSERT INTO manager VALUES ("M2001","Aadhya Raj",21112);
