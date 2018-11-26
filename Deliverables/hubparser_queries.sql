# Query to get the IP Addresses that made more than 100 requests between 2017-01-01 13:00:00 and 2017-01-01 14:00:00.
select distinct ipAddress from requestlog where requestDate between '2017-01-01 13:00:00' and '2017-01-01 14:00:00' group by id, ipAddress having count(*) > 100;

# Query to get all of the requests made for the IP Address 192.168.228.188. User has to be aware that the id is used to differentiate each run of the software.
select * from requestlog where ipAddress = '192.168.228.188';