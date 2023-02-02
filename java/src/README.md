# Assumptions

The system using Shopping Cart Interface is not yet ready to handle exceptions.

Some suggestions would be to use either exceptions or booleans, for example when trying to add items that don't exist

String itemType is a product reference

For this situation, left some TODO comments for future Shopping Cart developers!

Whenever a "mystery item" is in the cart, it defaults to a Discontinued product

# Code smells / Detected Issues

- Primitive obsession 
  - Product reference -> Solved by create class Product that encapsulates this info
  - Product price -> Solved by Currency
- Feature envy
  - Formatting the prices using the integers -> solved by implementing toString method on Currency
  - Shopping cart formatting the receipt

## Tests

- The tests are way too focused in the output stream
  - Create a Format interface and use mocks TODO



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
- Create Formatter Interface TODO
