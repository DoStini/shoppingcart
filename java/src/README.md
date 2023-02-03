# Assumptions

The system using Shopping Cart Interface is not yet ready to handle exceptions.

Some suggestions would be to use either exceptions or booleans, for example when trying to add items that don't exist

String itemType is a product reference

For this situation, left some TODO comments for future Shopping Cart developers!

Whenever a "mystery item" is in the cart, it defaults to a Discontinued product

Whenever an item is scanned and then scanned again later, the receipt will print it in the initial position

# Code smells / Detected Issues

- Primitive obsession 
  - Product reference -> Solved by create class Product that encapsulates this info
  - Product price -> Solved by Currency
- Feature envy
  - Formatting the prices using the integers -> solved by implementing toString method on Currency
  - Shopping cart formatting the receipt
    - Solved by creating a Formatter interface, passed to Shopping Cart using Dependency Injection
    - Should be instanced according to user's preferences in the system

## Tests

- The tests are way too focused in the output stream
  - Create a Format interface and use mocks TODO
- Test individually Formatter
- Use mocks to test if correct print statements called and in the order of scan

# Some changes

## Improving safety

- Replaced `keys[i]` for a key iterator
- Replace HashMap by Interface Corresponding Map
- Remove deprecated usages 
  - Float constructor is now a simple cast

## Refactors

- Create Currency
- Add a product with reference, name and price
- Extract pricer to Interface
- Create Formatter Interface, Dependency Injection

## Total line

In an initial phase, I added a total variable in printReceipt method which was calculated while printing. 
This doesn't seem like a good idea as this function is doing too much things, such as calculating the price and sending information to print

To solve this, the program updates the price as soon as an item is added to the cart. This also allows the user to easily and efficiently view the updated total price after each entry.

## Scan order

To allow print in the scanning order, I used a LinkedHashMap since it preserves insertion order. 

Also created a `ShoppingCartEntry` to allow the update of a previously added product and to remove the responsability of calculating the total of each entry in the printReceipt.
