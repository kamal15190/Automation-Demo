#Author: kamaljswl1990@gmail.com


Feature: Latest Rocket Launch Details
  Want to get the details of latest launch.


  Scenario: Get the launch detail
    Given I have Resources "https://api.spacexdata.com/v4/launches/latest" and Header "Application/json"
    And I send  a "GET" call
    Then I expect status code "200"