Feature: Search google

@web
Scenario:
	Given we are on google.com
	When we search for github
	Then we will see github in the search results

@api
Scenario:
	Given we need api data
	When we query the api
	Then we will receive a valid response
    
@unit
Scenario:
    Given code requires unit tests before deployment
    When new code is checked in
    Then unit tests will run
    
@security
Scenario:
    Given we are on a login page
    When we enter an incorrect password
    Then we will not be able to log in