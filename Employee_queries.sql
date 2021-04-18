
--View individual earning:
SELECT salary
	FROM employee
	WHERE employeeID=eID

--View all pending customer complaints:
	select * 
	from complain
	where complainID
	not in 
	(select complain_complainID
	from process);

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
SELECT complainID, reason, customer_customerID
	FROM process
	INNER JOIN complain
	WHERE employee_employeeID=eID
