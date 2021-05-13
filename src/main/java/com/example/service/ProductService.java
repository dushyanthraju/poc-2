package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.OrderDao;
import com.example.dao.ProductDao;
import com.example.model.OrderDetails;
import com.example.model.Product;

@Service
public class ProductService {
	@Autowired
	ProductDao productDao;
	
	@Autowired
	OrderDao orderDao;
	
	public Product createProduct(Product product) {
		return productDao.save(product);
	}
	public OrderDetails createOrder(OrderDetails order) {
		return orderDao.save(order);
	}
	public Iterable<Product> getProducts(){
		return productDao.findAll();
	}
	public Iterable<OrderDetails> getOrders(){
		return orderDao.findAll();
	}
	public Optional<Product> getProductsById(Integer id){
		return productDao.findById(id);
	}
	public Optional<OrderDetails> getOrdersById(Integer orderId){
		return orderDao.findById(orderId);
	}
	public int placeOrderDetails(Integer id, Integer quantity, OrderDetails order) {
		int result = 1;
		Product product = productDao.findById(id).orElse(new Product());
		if(product == null)
			return result;
		order.setQuantity(quantity);
		
		order.setProduct(product);
		orderDao.save(order);
		result = 0;
		return result;
		
	}
	
	public int updateOrderDetails(Integer orderId, Integer quantity) {
		int result = 1;
		OrderDetails order = orderDao.findById(orderId).orElse(new OrderDetails());
		if(order.getOrderId() == 0) {
			return result;
		}
		
		order.setQuantity(quantity);
		orderDao.save(order); 
		result = 0;
		
		return result;
	}
}
