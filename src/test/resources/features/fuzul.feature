@fuzulAPI
Feature: Fuzul API

  @tc01 @smoke @regression
  Scenario:TC01_Entity_Testing
    Given User sends a GET request for Entity
    Then User verifies that status code is equal to 200
    Then User verifies that apiResultType is equal to 1
    Then User verifies that the data contains 28 values
    Then User verifies that data with id number 22 exists
    Then User verifies that the data.value contains "Satılık" and "Kiralık" and "Günlük"
    Then User verifies that the entityGroupId of id number 4 is 2.

  @tc02 @smoke @regression
  Scenario:TC02_EntityGroup_Testing
    Given User sends a GET request for EntityGroup
    Then User verifies that status code is equal to 200
    Then User verifies that apiResultType is equal to 1
    Then User verifies that the data contains 5 values
    Then User verifies that data with id number 1 2 3 4 5 exists
    Then User verifies that the Emlak tipi contains "Satılık" "Kiralık" "Günlük" values
    Then User verifies that the Oda Sayisi contains "1 + 1"
    Then User verifies that the Bulundugu kat contains 7 values


  @tc03 @smoke @regression
  Scenario:TC03_Product_Testing
    Given User sends a GET request for Product
    Then User verifies that status code is equal to 200
    Then User verifies that pageNumber is equal to 1
    Then User verifies that pageSize is equal to 30
    Then User verifies that apiResultType is equal to 1
    Then User verifies that the data contains 30 values
    Then User verifies that productPhotoUrl is not empty
    Then User verifies that data with id number 1 12 18 25 9 exists
    Then User verifies that all values for id 5

  @tc04 @smoke @regression
  Scenario:TC04_Product/id_Testing
    Given User sends a GET request for Product 7
    Then User verifies that status code is equal to 200
    Then User verifies that apiResultType is equal to 1
    Then User verifies for all values

  @tc05 @smoke @regression
  Scenario: TC05_WeatherForecast_Testing
    Given User sends a GET request for WeatherForecast
    Then User verifies that status code is equal to 200
      # it gives 500... fail