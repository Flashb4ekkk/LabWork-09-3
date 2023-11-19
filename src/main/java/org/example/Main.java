package org.example;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter the number of routes: ");
        Route[] routes = new Route[scanner.nextInt()];
        int menuItem = 0, counter = 0;
        do {
            System.out.println("Select an action:");
            System.out.println("[1] - input values from console to array");
            System.out.println("[2] - sort array by route number");
            System.out.println("[3] - print information about routes");
            System.out.println("[4] - save array to file");
            System.out.println("[5] - read array from file");
            System.out.println("[0] - exit menu");
            boolean validInput = false;
            while (!validInput) {
                try {
                    menuItem = scanner.nextInt();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.err.println("Invalid input. Please select an action:");
                    scanner.next();
                }
            }
            switch (menuItem) {
                case 1: {
                    if (counter == routes.length) {
                        System.err.println("Array is full");
                        break;
                    }
                    create(routes, scanner);
                    counter++;
                    break;
                }
                case 2: {
                    sortArray(routes);
                    printArray(routes);
                    break;
                }
                case 3: {
                    printInfoOfPoint(routes);
                    break;
                }
                case 4: {
                    saveArrayInFile(routes, scanner);
                    break;
                }
                case 5: {
                    readArrayFromFile(scanner);
                    break;
                }
                case 0: {
                    break;
                }
                default:
                    System.err.println("Unknown command");
            }
        } while (menuItem != 0);
    }

    public static void saveArrayInFile(Route[] routes, Scanner scanner) {
        System.out.print("Enter file name: ");
        String name = scanner.next();
        String desktopPath = "C:\\Users\\roman\\Desktop\\";
        String fullPath = desktopPath + name + ".txt";

        try {
            FileOutputStream fileOut = new FileOutputStream(fullPath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(routes);

            objectOut.close();
            fileOut.close();

            System.out.println("Array successfully serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readArrayFromFile(Scanner scanner) {
        System.out.print("Enter file name: ");
        String name = scanner.next();
        String desktopPath = "C:\\Users\\roman\\Desktop\\";
        String fullPath = desktopPath + name + ".txt";
        try {
            FileInputStream fileIn = new FileInputStream(fullPath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Route[] routes = (Route[]) objectIn.readObject();

            objectIn.close();
            fileIn.close();

            System.out.println("Routes");
            System.out.println("------------- Routes ------------");
            System.out.println("| " + String.format("%10s", "start") + " | " + String.format("%10s", "end") + " | " + String.format("%3s", "№") + " |");
            System.out.println("---------------------------------");
            for (Route route : routes) {
                System.out.println(route);
            }
            System.out.println("---------------------------------");
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void create(Route[] routes, Scanner scanner) {
        System.out.println("enter name start point: ");
        String start = scanner.next();
        System.out.println("enter name end point: ");
        String end = scanner.next();
        System.out.println("enter route number: ");
        int number = scanner.nextInt();
        Route route = new Route(start, end, number);
        for (int i = 0; i < routes.length; i++) {
            if (routes[i] == null) {
                routes[i] = route;
                break;
            }
        }
    }

    public static void sortArray(Route[] routes){
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes.length - 1; j++) {
                if (routes[j].getNumber() > routes[j + 1].getNumber()) {
                    Route temp = routes[j];
                    routes[j] = routes[j + 1];
                    routes[j + 1] = temp;
                }
            }
        }
    }

    public static void printInfoOfPoint(Route[] routes) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter point name: ");
        String point = scanner.next();
        boolean found = false;
        for (Route route : routes) {
            if (route.getStart().equals(point) || route.getEnd().equals(point)) {

                System.out.println(route);
                found = true;
            }
        }
        if (!found) {
            System.out.println("route, which starts or ends in " + point + ", not found");
        }
    }

    public static void printArray(Route[] routes) {
        System.out.println("---------------------------------");
        System.out.println("| " + String.format("%10s", "start") + " | " + String.format("%10s", "end") + " | " + String.format("%3s", "№") + " |");
        System.out.println("---------------------------------");
        for (Route route : routes) {
            System.out.println(route);
        }
        System.out.println("---------------------------------");
    }
}