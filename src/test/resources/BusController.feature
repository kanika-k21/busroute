Feature: BusController Test

Background:
    * url 'http://localhost:3000'

Scenario: Get Bus By Registration Number
    Given path 'buses/123'
    When method get
    Then status 200
    And match response == "Controller functionality working!!"