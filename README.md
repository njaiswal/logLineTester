# logLineTester

This Project aims to write a simple junit test to verify that log lines get logged.

It uses following:

* Mock the Log Appender which is used for log lines we are interested in testing
* Capture the logs via mocked Appender during the test
* Verify log lines in the test
