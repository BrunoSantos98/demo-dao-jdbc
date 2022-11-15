package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Test FindById:

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 01: seller Find By Id ===");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
        System.out.println();

        System.out.println("=== TEST 02: seller Find By Department ===");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for(Seller obj : list){
            System.out.println(obj);
        }
        System.out.println();

        System.out.println("=== TEST 03: seller Find All ===");
       list = sellerDao.findAll();
        for(Seller obj : list){
            System.out.println(obj);
        }
        System.out.println();

        /*
            System.out.println("=== TEST 04: seller Insert ===");
            Seller newSeller = new Seller(null,"Greg Down","Greg@gmail.com",new Date(),4000.00,department);
            sellerDao.insert(newSeller);
            System.out.println("Inserted! new id = " + newSeller.getId());
        */

        System.out.println("=== TEST 05: seller Update ===");
       seller = sellerDao.findById(1);
       seller.setName("Martha Wayne");
        sellerDao.update(seller);
        System.out.println("Update Completed!");

    }
}