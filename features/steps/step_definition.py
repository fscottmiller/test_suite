from behave import *
from selenium.webdriver import *
from mamba import *
from expects import *
import requests

### Web ###

@given('we are on google.com')
def step(context):
    context.browser = Chrome()
    context.browser.get('http://www.google.com')

@when('we search for github')
def step(context):
    context.browser.find_element(by='name',value='q').send_keys('github') # set search text field to 'github'
    context.browser.find_element(by='name',value='btnK').click # click the search button

@then('we will see github in the search results')
def step(context):
    # expect(@chrome.text.include?("https://github.com/")).to eq(true)	# check that the url for github is on the results page
    # expect(context.).to(equal((0, 0)))
    txt = context.browser.find_element_by_xpath(".//html").text
    expect(txt).to(contain('github'))

#### API ###

@given('we need api data')
def step(context):
    context.url = 'https://jsonplaceholder.typicode.com/users'
    print('given')

@when('we query the api')
def step(context):
    context.req = requests.get(context.url)

@then('we will receive a valid response')
def step(context):
    expect(context.req.status_code).to(equal(200))

