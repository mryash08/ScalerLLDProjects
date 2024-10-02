# Generate Bill for Restaurant Management System

## Problem Statement

You are building a Restaurant Management System. As a part of this system, you need to build a functionality using which customers can generate a bill for their orders.

## Assignment

Your task is to implement the following functionality.

#### Requirements

1. Generally a customer places multiple orders before requesting for the bill. Hence, our system must be able to track orders placed by a customer.
    * We should have an entity called as `CustomerSession` in our system which will help us track the orders placed by a customer.
    * Once the customer places their 1st order, we should create a `CustomerSession` for them with status as `ACTIVE`.
    * All the subsequent orders placed by the customer should be associated with the `CustomerSession` created for them.
    * Once the customer requests for the bill, we should mark the `CustomerSession` as `ENDED`.
2. The request for generating the bill will contain just the customer id.
3. This functionality should aggregate the items ordered by the customer and calculate the total amount to be paid by the customer.
4. This functionality should also calculate GST and service charge on the total amount.
5. GST will be 5% of the total food cost and service charge will be 10% of the total food cost.
6. Return the bill details in the response.

#### Instructions

* Refer the `generateBill` method inside `OrderController` class.
* Refer the `GenerateBillRequestDto` and `GenerateBillResponseDto` for understanding the expected input and output to the functionality.
* Refer the models package to understand the models.
* Implement the `OrderService`, `CustomerSessionRepository` and `OrderRepository` interfaces to achieve the above requirements.
* We need in memory database implementation for this assignment.
* Refer the `TestOrderController` class to understand the test cases that will be used to evaluate your solution.
* Do not modify existing methods and their parameters for interfaces, feel free to add more methods if required.