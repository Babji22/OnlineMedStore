package com.jsp.OnlineMedStore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.OnlineMedStore.Service.CartService;
import com.jsp.OnlineMedStore.entity.Cart;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {
	
	@Autowired
	CartService cartService;

	@PostMapping("/addToCart/{memberid}/{drugid}")
	public String addToCart(@PathVariable("memberid") Integer memberid,@PathVariable("drugid") Integer drugid)
	{
		cartService.addToCart(memberid, drugid);
		return "redirect:/Medicine";
	}
	
	@GetMapping("/{memberid}")
	public List<Cart> getCartProducts(@PathVariable("memberid") Integer memberid)
	{
		return cartService.getCartProducts(memberid);
		
	}
	
	@DeleteMapping("/{cartId}")//{memberId}")
	public String deleteCartProducts(@PathVariable("cartId") Integer cartId)//,@PathVariable("memberId") Integer memberId)
	{
		return cartService.deleteCartProducts(cartId);//,memberId);
		
	}
	
}
