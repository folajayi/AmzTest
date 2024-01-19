Feature: Add Second Product to Cart and Verify Prices
  This is to Add Second Product to Cart and Verify Prices
  Scenario: Validate product page price is identical cart price and cart subtotal when the second product is selected
    Given That user is on amazon website
    When user searches for product 'laptop'
    When user selects second preferred product
    Then System displays product details
    When user adds product to cart
    Then Product is added to cart successfully
    When user navigates to cart
    Then system displays cart details with product price equal to product details price
    And system displays cart details with product subtotal price equal to product details price