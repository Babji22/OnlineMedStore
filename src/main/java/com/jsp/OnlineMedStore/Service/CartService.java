package com.jsp.OnlineMedStore.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.OnlineMedStore.DTO.DrugDTO;
import com.jsp.OnlineMedStore.Exception.NotFoundException;
import com.jsp.OnlineMedStore.Repository.CartRepository;
import com.jsp.OnlineMedStore.Repository.DrugRepository;
import com.jsp.OnlineMedStore.entity.Cart;
import com.jsp.OnlineMedStore.entity.Drug;

@Service
public class CartService {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	DrugRepository drugRepository;

	public String addToCart(Integer memberid,Integer drugid)
	{
		Optional<Drug> drug= drugRepository.findById(drugid);
		if(drug.isPresent())
		{
			Cart cart=new Cart();
			cart.setDrugId(drugid);
			cart.setMemberId(memberid);
			cart.setDrugName(drug.get().getName());
			cart.setPrice(drug.get().getPrice());
			cart.setQuantity(1);
			cartRepository.save(cart);
			System.out.println(cartRepository.save(cart));
			return "cart added sucessfully";
		}
		else
		{
			throw new NotFoundException("Drug is not available");
		}
		
	}

	public List<Cart> getCartProducts(Integer memberid) {
		
		List<Cart> cartList=new ArrayList<Cart>();
		
		List<Cart> cartData= cartRepository.findAll();
		if(cartData.size()>0)
		{
			for(Cart cart: cartData)
			{
				if(cart.getMemberId()==memberid)
				{
					cartList.add(cart);
				}
			}
			return cartList;
		}
		else
		{
			throw new NotFoundException("Your cart is EMPTY");
		}
		
		
		
	}

	public String deleteCartProducts(Integer cartId) {
		Optional<Cart> cartData= cartRepository.findById(cartId);
		if(cartData.isPresent())
		{
			cartRepository.deleteById(cartId);
			return "Deleted Sucessfully";
		}
		throw new NotFoundException("Cart Data is Not Found");
	}
	
}
