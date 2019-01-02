# env.rb 
require 'selenium-webdriver'
require 'watir'
require 'rspec'
require 'net/http'
require 'uri'
require 'json'

chromedriver_path = File.join(File.absolute_path('../..', File.dirname(__FILE__)),"browsers","chromedriver.exe")
Selenium::WebDriver::Chrome.driver_path = chromedriver_path

