Given Jenkins is running
When a sample job is triggered
Then it should successfully run

Given a job ran
When the results are queried
Then those results should be returned

### specs list to be written as gherkin ###
jenkins is running and accessible
elk is running and accessible
jenkins and elk can communicate
jenkins can communicate with nodes
jenkins can (de)allocate nodes on demand
the sanity check runs on checkin
jenkins (and nodes) can pull necessary code from git
jenkins creates the correct number of stages (from yml)
sanity check should catch bad code
