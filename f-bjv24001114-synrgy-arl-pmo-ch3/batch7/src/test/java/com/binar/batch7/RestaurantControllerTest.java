package com.binar.batch7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RestaurantControllerTest {

    @Test
    void testInitializeMenu_Positive() {

        RestaurantController controller = new RestaurantController();


        controller.initializeMenu();


        assertTrue(!controller.getMenu().isEmpty());
    }

    @Test
    void testInitializeMenu_Negative() {

        RestaurantController controller = new RestaurantController();

        controller.initializeMenufalse();

        assertTrue(!controller.getMenu().isEmpty());
    }


    static class RestaurantController {
        private Map<Integer, MenuItem> menu;

        public void initializeMenufalse() {
            menu = new HashMap<>();
        }
        public void initializeMenu() {
            menu = new HashMap<>();
            menu.put(1, new MenuItem("Nasi Goreng", 15000));
            menu.put(2, new MenuItem("Mie Goreng", 13000));
            menu.put(3, new MenuItem("Nasi + Ayam", 18000));
            menu.put(4, new MenuItem("Es Teh Manis", 3000));
            menu.put(5, new MenuItem("Es Jeruk", 5000));
            menu.put(6, new MenuItem(null, 6000));
        }

        public Map<Integer, MenuItem> getMenu() {
            return menu;
        }
    }

    // Dummy implementation of MenuItem to make the test compile
    static class MenuItem {
        private String name;
        private double price;

        public MenuItem(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }
}