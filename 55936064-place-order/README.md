# Track orders for Restaurant Management System

## Problem Statement

You are building a Restaurant Management System. As a part of this system, you need to build a functionality using which orders placed by a customer can be tracked.

## Assignment

Your task is to implement the following functionality.

#### Requirements

1. Generally a customer places multiple orders before requesting for the bill. Hence, our system must be able to track orders placed by a customer.
    * We should have an entity called as `CustomerSession` in our system which will help us track the orders placed by a customer.
    * Once the customer places their 1st order, we should create a `CustomerSession` for them with status as `ACTIVE`.
    * All the subsequent orders placed by the customer should be associated with the `CustomerSession` created for them.
2. The request for placing an order will contain the user id of the customer and a Map<Long,Integer>, where the key will be menu item id and value is the quantity of the menu item ordered.
3. This functionality should store the order details in the database.
4. If the order is placed for a customer who is not present in the database, then we should throw an error.
5. If an order contains a menu item which is not present in the database, then we should throw an error.
6. Return the order details in the response.

#### Instructions

* Refer the `placeOrder` method inside `OrderController` class.
* Refer the `PlaceOrderRequestDto` and `PlaceOrderResponseDto` for understanding the expected input and output to the functionality.
* Refer the models package to understand the models.
* Implement the `OrderService`, `CustomerSessionRepository`, `UserRepository`, `MenuItemRepository` and `OrderRepository` interfaces to achieve the above requirements.
* We need in memory database implementation for this assignment.
* Refer the `TestOrderController` class to understand the test cases that will be used to evaluate your solution.
* Do not modify existing methods and their parameters for interfaces, feel free to add more methods if required.