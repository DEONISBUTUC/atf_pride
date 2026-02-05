Feature: Wikipedia Search Functionality

  @Search
  Scenario: Depune petitie protectia consumatorilor
    Given 'Petitionarul' este authorizat
    When user searches for "Test Automation Framework"
    Then the article for "Test Automation Framework" should be displayed