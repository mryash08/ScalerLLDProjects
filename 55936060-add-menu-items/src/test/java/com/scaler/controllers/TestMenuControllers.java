package com.scaler.controllers;

import com.scaler.dtos.*;
import com.scaler.models.User;
import com.scaler.models.UserType;
import com.scaler.repositories.MenuRepository;
import com.scaler.repositories.UserRepository;
import com.scaler.services.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestMenuControllers {

    private UserRepository userRepository;
    private MenuRepository menuRepository;

    private MenuService menuService;

    private MenuController menuController;

    @BeforeEach
    public void setupTest() throws Exception {
        initializeComponents();
    }

    private void initializeComponents() throws Exception {
        initializeRepositories();
        initializeMenuService();
        initializeTicketController();
    }

    private <T> T createInstance(Class<T> interfaceClass, Reflections reflections) throws Exception {
        Set<Class<? extends T>> implementations = reflections.getSubTypesOf(interfaceClass);
        if (implementations.isEmpty()) {
            throw new Exception("No implementation for " + interfaceClass.getSimpleName() + " found");
        }

        Class<? extends T> implementationClass = implementations.iterator().next();
        Constructor<? extends T> constructor = implementationClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }

    private <T> T createInstanceWithArgs(Class<T> interfaceClass, Reflections reflections, List<Object> dependencies) throws Exception {
        Set<Class<? extends T>> implementations = reflections.getSubTypesOf(interfaceClass);
        if (implementations.isEmpty()) {
            throw new Exception("No implementation for " + interfaceClass.getSimpleName() + " found");
        }
        Class<? extends T> implementationClass = implementations.iterator().next();
        Constructor<?>[] constructors = implementationClass.getConstructors();
        Constructor<?> constructor = Arrays.stream(constructors)
                .filter(constructor1 -> constructor1.getParameterCount() == dependencies.size())
                .findFirst().orElseThrow(() -> new Exception("No constructor with " + dependencies.size() + " arguments found"));
        constructor.setAccessible(true);
        Object[] args = new Object[constructor.getParameterCount()];
        for (int i = 0; i < constructor.getParameterCount(); i++) {
            for (Object dependency : dependencies) {
                if (constructor.getParameterTypes()[i].isInstance(dependency)) {
                    args[i] = dependency;
                    break;
                }
            }
        }
        return (T) constructor.newInstance(args);
    }

    private void initializeRepositories() throws Exception {
        Reflections repositoryReflections = new Reflections(UserRepository.class.getPackageName(), new SubTypesScanner(false));
        this.menuRepository = createInstance(MenuRepository.class, repositoryReflections);
        this.userRepository = createInstance(UserRepository.class, repositoryReflections);
    }


    private void initializeMenuService() throws Exception {
        Reflections serviceReflections = new Reflections(MenuService.class.getPackageName(), new SubTypesScanner(false));
        this.menuService = createInstanceWithArgs(MenuService.class, serviceReflections, Arrays.asList(this.menuRepository, this.userRepository));
    }

    private void initializeTicketController() {
        this.menuController = new MenuController(this.menuService);
    }


    @Test
    public void testAddMenuItem_Success() throws Exception {
        User adminUser = new User();
        adminUser.setUserType(UserType.ADMIN);
        adminUser.setName("admin");
        adminUser.setPassword("admin");
        adminUser.setPhone("1234567890");
        adminUser = userRepository.save(adminUser);

        AddMenuItemRequestDto requestDto = new AddMenuItemRequestDto();
        requestDto.setUserId(adminUser.getId());
        requestDto.setName("Paneer Tikka");
        requestDto.setPrice(200);
        requestDto.setDietaryRequirement("VEG");
        requestDto.setItemType("DAILY_SPECIAL");
        requestDto.setDescription("Paneer Tikka is a vegetarian dish from the Indian subcontinent made from paneer marinated in spices and grilled in a tandoor.");
        AddMenuItemResponseDto addMenuItemResponseDto = menuController.addMenuItem(requestDto);
        assertEquals(addMenuItemResponseDto.getStatus(), ResponseStatus.SUCCESS, "AddMenuItemResponseDto status should be SUCCESS");
        assertNotNull(addMenuItemResponseDto.getMenuItem(), "AddMenuItemResponseDto menuItem should not be null");
    }

    @Test
    public void testAddMenuItem_AddedByCustomer() throws Exception {
        User customerUser = new User();
        customerUser.setUserType(UserType.CUSTOMER);
        customerUser.setName("admin");
        customerUser.setPassword("admin");
        customerUser.setPhone("1234567890");
        customerUser = userRepository.save(customerUser);

        AddMenuItemRequestDto requestDto = new AddMenuItemRequestDto();
        requestDto.setUserId(customerUser.getId());
        requestDto.setName("Paneer Tikka");
        requestDto.setPrice(200);
        requestDto.setDietaryRequirement("VEG");
        requestDto.setItemType("DAILY_SPECIAL");
        requestDto.setDescription("Paneer Tikka is a vegetarian dish from the Indian subcontinent made from paneer marinated in spices and grilled in a tandoor.");
        AddMenuItemResponseDto addMenuItemResponseDto = menuController.addMenuItem(requestDto);
        assertEquals(addMenuItemResponseDto.getStatus(), ResponseStatus.FAILURE, "AddMenuItemResponseDto status should be Failure as user is not an admin");
        assertNull(addMenuItemResponseDto.getMenuItem(), "AddMenuItemResponseDto menuItem should be null");
    }

    @Test
    public void testAddMenuItem_AddedByNonExistingUser() throws Exception {
        User adminUser = new User();
        adminUser.setUserType(UserType.ADMIN);
        adminUser.setName("admin");
        adminUser.setPassword("admin");
        adminUser.setPhone("1234567890");
        adminUser = userRepository.save(adminUser);

        AddMenuItemRequestDto requestDto = new AddMenuItemRequestDto();
        requestDto.setUserId(adminUser.getId() + 1);
        requestDto.setName("Paneer Tikka");
        requestDto.setPrice(200);
        requestDto.setDietaryRequirement("VEG");
        requestDto.setItemType("DAILY_SPECIAL");
        requestDto.setDescription("Paneer Tikka is a vegetarian dish from the Indian subcontinent made from paneer marinated in spices and grilled in a tandoor.");
        AddMenuItemResponseDto addMenuItemResponseDto = menuController.addMenuItem(requestDto);
        assertEquals(addMenuItemResponseDto.getStatus(), ResponseStatus.FAILURE, "AddMenuItemResponseDto status should be Failure as user does not exist");
        assertNull(addMenuItemResponseDto.getMenuItem(), "AddMenuItemResponseDto menuItem should be null");
    }
}
