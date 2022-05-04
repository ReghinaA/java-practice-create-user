Feature: Login and Creating a User

  # @close - so the browser window won't close in the end
  Scenario: Navigate to "The Store login" page and Create a User
    Given I create an account using my e-mail
    And I Fill out Your Personal Information fields


  Scenario: Navigate to "T-shirts" page and make an order
    Given I navigate to T-shirts page
    And I select Faded Short Sleeve T-shirts
    Then T-shirts shopping page opened and t-shirt model is 'Faded Short Sleeve T-shirts'
    And I select T-shirt quantity, size, color and click Add to cart
    When Pop up confirmation window 'Product successfully added to your shopping cart' comes up
    Then I check product name 'Faded Short Sleeve T-shirts' and Total
    And I select Proceed to checkout
