Feature:  Purchase Sauce Lab Back Packs

  @Android @Purchases
  Scenario: Successfully purchase blue Sauce Lab Back Packs
    Given User on the products page
    When User choose product "Sauce Lab Back Packs"
    And User choose type "Blue color"
    And User swipe up "1" times
    And User add quantity to "2"
    And User tap on add to cart
    And User tap on cart icon
    And User on cart page
    And User validate total product price
    And User tap on proceed to checkout
    And User on the login page
    And User input "bod@example.com" as username
    And User input "10203040" as password
    And User tap on login button
    And User on the checkout page
    And User input "James Hasanuddin" as full name
    And User input "Stockbit" as address line 1
    And User input "Jakarta" as city
    And User input "Indonesia" as country
    And User input "12345" as postal code
    And User tap on to payment
    And User input "James Hasanuddin" as full name on card
    And User input "4111111111111111" as card number
    And User input "1225" as expiry date
    And User input "123" as security code
    And User tap on review order
    And User swipe up "1" times
    And User validate total bills
    And User tap on place order
    Then User verify order success
