import { HttpClient } from '@angular/common/http';
import { Component, inject, signal } from '@angular/core';
import { DrugService } from '../../drug-service';
import { OrderService } from '../../order-service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UserService } from '../user-service';
import { Services } from '../../services';

@Component({
  selector: 'app-cart',
  imports: [FormsModule,CommonModule],
  templateUrl: './cart.html',
  styleUrl: './cart.css',
})
export class Cart {

  memberService=inject(UserService);
  drugService=inject(DrugService);
  orderService=inject(OrderService);

  cartProducts=signal<CartProduct[]>([]);

  memberId=0;
  ngOnInit()
  {
    this.memberId=this.memberService.user.id;
    console.log(this.memberId);
    this.getCartProducts(this.memberId);
  }

  getCartProducts(id:number)
  {
    this.drugService.getCartProducts(id).subscribe({
      next:(data:any)=>{
        this.cartProducts.set(data);
        console.log(this.cartProducts());
      },
      error:()=>{
        alert("Unable to fetch cart products");
      }
    });
  }

  addToCart(memberId:number, drugId:number)
  {
    this.drugService.addToCart(memberId, drugId).subscribe({
      next:()=>{
        alert("Product added to cart successfully");
      },
      error:()=>{
        alert("Unable to add product to cart");
      }
    });
  }

  placeOrder()
  {
    console.log(this.cartProducts());
    this.orderService.orderProduct(this.cartProducts()).subscribe({
      next:()=>{
        for(let i=0;i<this.cartProducts().length;i++)
        {
          this.drugService.deleteCartProduct(this.cartProducts()[i].cartId).subscribe();
        }
        this.cartProducts.set([]);
        alert("Order placed successfully");
      },
      error:()=>{
        alert("Unable to place order");
      }
    });
  }

  

}

class CartProduct{
  cartId!:number;
  memberId!:number;
  drugId!:number;
  quantity!:number;
  drugName!:string;
  price!:number;
}
