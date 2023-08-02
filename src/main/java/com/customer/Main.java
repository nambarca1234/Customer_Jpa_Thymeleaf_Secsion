//package com.customer;
//
//import com.customer.controller.CustomerController;
//
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        CustomerController controller = new CustomerController();
//        Scanner scanner = new Scanner(System.in);
//        do {
//            System.out.println("Nhap lua chon: ");
//            System.out.println("1. Xem Danh sach Khach hang");
//            System.out.println("2. Them Khach hang");
//            System.out.println("3. Sua danh sach Khach hang");
//            System.out.println("4. Xoa Khach hang");
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//            switch (choice){
//                case 1:
//                    controller.read();
//                    break;
//                case 2:
//                    controller.create();
//                    break;
//                case 3:
//                    controller.update();
//                    break;
//                case 4:
//                    controller.delete();
//                    break;
//            }
//        }
//        while (true);
//    }
//}