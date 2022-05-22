package com.egadgets.demo3.service;

import com.egadgets.demo3.dao.ProductDAO;
import com.egadgets.demo3.model.Product;

import javax.sql.DataSource;
import java.util.ArrayList;

public class ProductService {
    private DataSource dataSource;
    public static ProductService INSTANCE;

    private ProductService() {

    }

    public static ProductService getInstance() {
        if (INSTANCE == null) {
            synchronized (ProductService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ProductService();
                }
            }
        }
        return INSTANCE;
    }

    public ArrayList<Product> getProductsForPage(ArrayList<Product> products, int itemsForPage, int selectedPage) {
        ArrayList<Product> productsForPage = new ArrayList<>();
        for (int i = itemsForPage * (selectedPage - 1); i < itemsForPage * selectedPage; i++) {
            if (i >= products.size()) {
                break;
            }
            productsForPage.add(products.get(i));
        }
        return productsForPage;
    }

    public ArrayList<Product> getProductsBySellerId(int id) {
        return ProductDAO.getProductsBySellerId(dataSource, id);
    }

    public void deleteProductById(int productId) {
        ProductDAO.deleteProductById(dataSource, productId);
    }

    public void insertProduct(Product product) {
        ProductDAO.insertProduct(dataSource, product);
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ProductDAO().getProducts(dataSource);
        return products;
    }

    public Product getProductById(int id) {
        return ProductDAO.getProductById(dataSource, id);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
