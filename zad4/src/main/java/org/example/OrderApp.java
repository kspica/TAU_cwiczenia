package org.example;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class OrderApp {

    public static void main(String[] args) throws Exception {
        InventoryService inventoryService = new InventoryService();
        PaymentService paymentService = new PaymentService();
        NotificationService notificationService = new NotificationService();

        OrderService orderService = new OrderService(inventoryService, paymentService, notificationService);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer przedmiotu który chcesz zamówić oraz ilość w formacie PRZEDMIOT_ILOSC:\n" +
                "1. Lego  10zł\n" +
                "2. Duplo 15zł\n" +
                "3. Cobi  20zł");
        String order = scanner.nextLine();
        if (validateOrder(order)) {
            Order orderObject = getOrder(order);
            orderService.processOrder(orderObject);
        } else {
            System.out.println("Invalid order: " + order);
        }
        scanner.close();
    }

    private static boolean validateOrder(String order) {
        String regex = "^[1-3]_\\d+$";
        Pattern pattern = Pattern.compile(regex);

        return order != null && pattern.matcher(order).matches();
    }

    public static Order getOrder(String order) {
        Map<String, Integer> prices = Map.of(
                "1", 10,
                "2", 15,
                "3", 20);
        String[] orderInfo = order.split("_");
        return new Order(orderInfo[0], Integer.valueOf(orderInfo[1]), prices.get(orderInfo[0]));
    }

}
