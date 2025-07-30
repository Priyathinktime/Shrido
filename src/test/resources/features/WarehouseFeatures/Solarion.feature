
Feature: Solarion API Functional Tests
  This feature validates Solarion REST API functionalities including user, folder, file, and content operations.

  Background:
    Given the base URI is set to the API
    
@SolarionTest
  Scenario: Retrieve user details by ID
    When I send a GET request to the "User Details" endpoint and capture the response body
    Then the response status code should be 200
    And the response body should contain the key "success" with value "true"

@SolarionTest
  Scenario: Create a new folder
    When I send a POST request to the endpoint with payload from "Create_Folder.json" and capture the response body
    Then the response status code should be 200
    And the response body should contain the key "success" with value "true"

@SolarionTest
  Scenario: Partially update folder metadata
    When I send a PATCH request to the endpoint with payload from "Update_Folder.json" and capture the response body
    Then the response status code should be 200
    And the response body should contain the key "success" with value "true"
    
@SolarionTest
  Scenario: Retrieve folder details
    When I send a GET request to the "Get Folder" endpoint and capture the response body
    Then the response status code should be 200
    And the response body should contain the key "success" with value "true"

@SolarionTest
  Scenario: Copy multiple objects
    When I send a POST request to the endpoint with payload from "Copy Multiple Object.json" and capture the response body
    Then the response status code should be 200
    And the response body should contain the key "success" with value "true"

@SolarionTest
  Scenario: Mark a folder as favourite
    When I send a PATCH request to the endpoint with payload from "MarkFolderFavorite.json" and capture the response body
    Then the response status code should be 200
    And the response body should contain the key "success" with value "true"

@SolarionTest
  Scenario: Create a new file
    When I send a POST request to the endpoint with payload from "CreateFile.json" and capture the response body
    Then the response status code should be 200
    And the response body should contain the key "success" with value "true"

@SolarionTest
  Scenario: Upload content to a file
    When I send a POST request to the endpoint with payload from "Upload Content.json" and capture the response body
    Then the response status code should be 200
    And the response body should contain the key "success" with value "true"

