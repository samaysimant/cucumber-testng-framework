#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Planit Automation Features
  I want to use this template for my feature file

  @smoke @regression
  Scenario: Register User
    Given I am in home page
    And I cliked My account link to navigate to signup page
    When I click Register link
    Then Registration page must be displayed
    When I entered username and email
    And clicked on Register button
    Then I must get Registration confirmation message
 
    
    @smoke2
  Scenario: User creation Test 
    Given I am in home page
    And I cliked My account link to navigate to signup page
    When I enter my credentials and pressed submit
    Then I should be at landing page

 
