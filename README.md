# HubParser

The goal is to write a parser in Java that parses web server access log file, loads the log to MySQL and checks if a given IP makes more than a certain number of requests for the given duration. 

The tool takes "accessLog", "startDate", "duration" and "threshold" as command line arguments. "startDate" is of "yyyy-MM-dd.HH:mm:ss" format, "duration" can take only "hourly", "daily" as inputs and "threshold" can be an integer.
Example run from Command Line: java -cp "parser.jar" com.ef.Parser --accesslog=/path/to/file --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100 

For Log4j to work properly, user must adjust the log location in the log4j.properties file to point to somewhere with write accessability.