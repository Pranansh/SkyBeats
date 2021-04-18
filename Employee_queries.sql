
--View individual earning:
SELECT salary
	FROM employee
	WHERE employeeID=eID

--View all pending customer complaints:
	SELECT complainID, reason, customer_customerID
		FROM complain

--View all producer data:
	SELECT producerID, name, views, earning, manager_managerID
		FROM producer

--View top 5 most played songs:
SELECT songID
	FROM (SELECT songID
		FROM songs
		ORDER BY views desc) as sub
	LIMIT 5
	
--View all resolved customer complaints:
--I'm a bit confused about this because we didn't give names to the attrubutes of process
SELECT complainID, reason, customer_customerID
	FROM process
	INNER JOIN complain
	WHERE employee_employeeID=eID
