package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Main {
    public static void main(String[] args) {
        //Test FindById:

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 01: seller Find By Id ===");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
        System.out.println();

        
    }
}