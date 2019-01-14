### Web ###

Given(/^we are on google.com$/) do
	@chrome = Watir::Browser.new :chrome	# open a new browser
	@chrome.goto("http://google.com")		# navigate to google.com
end

When(/^we search for github$/) do
	@chrome.text_field(name: 'q').set("github")		# set search text field to 'github'
	@chrome.button(name: 'btnK').click				# click the search button
end

Then(/^we will see github in the search results$/) do
	expect(@chrome.text.include?("https://github.com/")).to eq(true)	# check that the url for github is on the results page
	@chrome.close	# close the browser
end

#### API ###

Given("we need api data") do
	@uri = URI.parse('https://jsonplaceholder.typicode.com/users')  # parse the url to a Ruby uri 
end

When("we query the api") do
	@response = Net::HTTP.get_response(@uri)    # use Net::HTTP to send a query to the uri
end

Then("we will receive a valid response") do
	expect(@response.kind_of? Net::HTTPSuccess).to eq(true) ## validate that we received a response
end

### Unit ###
Given("code requires unit tests before deployment") do
    puts "we always test our code"
end
When("new code is checked in") do
    puts "new code is in!"
end
Then("unit tests will run") do
    puts "running unit tests here"
end
    
## Security ###
# Given("we are on a login page") do
    # puts "checking security"
# end
# When("we enter an incorrect password") do
    # puts "attempting login with bad password"
# end
# Then("we will not be able to log in") do
    # puts "access denied"
# end