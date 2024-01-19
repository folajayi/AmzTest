Feature: Add First Product to Cart and Verify Prices
    This is to Add First Product to Cart and Verify Prices

    Scenario: Validate product page price is identical cart price and cart subtotal when the first product is selected
          Given That user is on amazon website
          When user searches for product 'monitor'
          When user selects first preferred product
          Then System displays product details
          When user adds product to cart
          Then Product is added to cart successfully
          When user navigates to cart
          Then system displays cart details with product price equal to product details price
          And system displays cart details with product subtotal price equal to product details price