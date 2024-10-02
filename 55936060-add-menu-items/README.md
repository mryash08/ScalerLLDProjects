# Add Menu Items

## Problem Statement

You are building a Restaurant Management System. As a part of this system, you need to build a functionality using which the restaurant admin can add menu items to the system.

## Assignment

Your task is to implement the following functionality.

#### Requirements

1. The add menu item request will contain the following details:
    * User id of the admin who is adding the menu item
    * Name of the menu item
    * Description of the menu item
    * Price of the menu item
    * Dietary details of the menu item - whether it is veg or non-veg or vegan
    * Type of the menu item - whether it is a daily special or a regular menu item
2. Non admin users should not be able to add menu items.
3. Persist the menu item details in the database.
4. Once the menu item is added, the system should return the menu item in response.

#### Instructions

* Refer the `addMenuItem` method inside `MenuItemController` class.
* Refer the `AddMenuItemRequestDto` and `AddMenuItemResponseDto` for understanding the expected input and output to the functionality.
* Refer the models package to understand the models.
* Implement the `MenuService`, `MenuRepository` and `UserRepository` interfaces to achieve the above requirements.
* We need in memory database implementation for this assignment.