package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Message;
import com.example.model.OrderDetails;
import com.example.model.Product;
import com.example.service.ProductService;

@RestController
@RequestMapping("/product/api")
public class OrderController {
	@Autowired
	private ProductService service;
	
	@PostMapping("/createproduct")
	public Product createProduct(@RequestBody Product product) {
		return service.createProduct(product);
	}
	
	@GetMapping("/product/list")
	public Iterable<Product> getProducts(){
		return service.getProducts();
	}
	@GetMapping("/order/list")
	public Iterable<OrderDetails> getOrders(){
		return service.getOrders();
	}
	@GetMapping("/product/{id}")
	public Optional<Product> getProductsById(@RequestParam("id") Integer id, Model model){
		model.addAttribute("id", id);
		return service.getProductsById(id);
	}
	
	@GetMapping("/order/{orderid}")
	public Optional<OrderDetails> getOrdersById(@RequestParam("id") Integer id, Model model){
		model.addAttribute("id", id);
		return service.getOrdersById(id);
	}
	@GetMapping("/order/place")
	public Message placeOrder(@RequestParam("product_id") Integer id, @RequestParam("quantity") Integer quantity) {
		Message msg = getMsgObj();
		try {
			OrderDetails order = new OrderDetails();
			int code = service.placeOrderDetails(id, quantity, order);
			if(code == 0) {
				msg.setCode(0);
				msg.setMessage("Success");
			}
			else if(code == 1) {
				msg.setCode(1);
				msg.setMessage("Invalid");
			}
			return msg;
		} catch (Exception e) {
			return msg;
		}
	}@GetMapping("/order/update")
	public Message updateOrder(@RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity) {
		Message msg = getMsgObj();
		try {
			int code = service.updateOrderDetails(id, quantity);
			if(code == 0) {
				msg.setCode(0);
				msg.setMessage("Success");
			}
			else if(code == 1) {
				msg.setCode(1);
				msg.setMessage("Invalid");
			}
			return msg;
		} catch (Exception e) {
			return msg;
		}
	}
	
	public static Message getMsgObj() {
		return new Message();
	}
	
}


