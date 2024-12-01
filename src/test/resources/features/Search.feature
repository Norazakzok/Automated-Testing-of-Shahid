Feature: Search and Play Videos on Shahid
  As a user who is not logged in
  I want to search for a video and watch it
  So that I can enjoy content without signing in

  Scenario: Search for a video and play it
    Given I am on the Shahid homepage
    When I enter "Top Movie" into the search bar
    And I click the search button
    Then I should see a list of search results related to "Top Results"
    When I select the first video from the results
    Then the video player should open and start buffering
    And the video should be playable