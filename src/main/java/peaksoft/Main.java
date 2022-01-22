package peaksoft;

import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.util.Scanner;

public class Main {
    static UserService database = new UserServiceImpl();
    static Scanner scanner = new Scanner(System.in);
    static Scanner sr = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {

            try {
                printCancol();
                int number = scanner.nextInt();

                if (number < 0 || number > 6) {
                    throw new Exception("you give wrong number");
                }
                switch (number) {
                    case 1 -> database.createUsersTable();

                    case 2 -> database.dropUsersTable();

                    case 3 -> {
                        System.out.println("write number of students you want to insert");
                        int sum = scanner.nextInt();
                        for (int i = 1; i <= sum; i++) {
                            scanner.nextLine();
                            System.out.println("write name " + i + ">");
                            String a = sr.nextLine();
                            System.out.println("write last_name " + i + ">");
                            String b = sr.nextLine();
                            System.out.println("write age " + i + ">");
                            byte c = sr.nextByte();
                            database.saveUser(a, b, c);
                        }

                    }
                    case 4 -> {
                        long removeById = scanner.nextInt();
                        database.removeUserById(removeById);
                    }
                    case 5 -> {

                        System.out.println(database.getAllUsers());

                    }

                    case 6 -> database.cleanUsersTable();


                    default -> System.out.println("You give wrong ");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void printCancol() {
        System.out.println("print to 1 -> create Table");
        System.out.println("print to 2 -> Drop  Table");
        System.out.println("print to 3 -> CaveUser");
        System.out.println("print to 4 -> Remove User By Id");
        System.out.println("print to 5 -> Get All Users");
        System.out.println("print to 6 -> Clean Users Table");
        System.out.println();
        System.out.println("Print you want to number...");
    }

}
