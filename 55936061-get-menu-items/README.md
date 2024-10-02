# Implement functionality using which customers can browse the menu items

## Problem Statement
You are building a Restaurant Management System. As a part of this system, you need to implement functionality using which customers can browse the menu items and place an order.

## Assignment

Your task is to implement the following functionality in the system.

#### Requirements

1. The get menu items request will get dietary preference as input. The dietary preference can be either "veg" or "non-veg" or "vegan".
2. If an invalid dietary preference is given to this functionality, we should get an error saying "Invalid dietary preference".
3. When a valid dietary preference is given, you need to get the menu items from the database and filter the menu items based on the dietary preference and return the menu items in response.
4. If dietary preference is not given, you need to return all the menu items in response.

#### Instructions

* Refer the `getMenuItems` function in MenuController class.
* Refer the `GetMenuItemsRequestDto` and `GetMenuItemsResponseDto` classes for understanding the expected input and output to the functionality.
* Refer the models package for reference of the models.
* Implement the MenuRepository and MenuService interfaces to achieve the above requirements.