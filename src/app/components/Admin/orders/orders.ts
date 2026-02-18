import { Component, inject, signal } from '@angular/core';
import { OrderService } from '../../order-service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-orders',
  imports: [FormsModule,CommonModule],
  templateUrl: './orders.html',
  styleUrl: './orders.css',
})
export class Orders {

  orderService=inject(OrderService);

  orderList=signal<Order[]>([]);

 ngOnInit()
  {
    this.getAllOrders();
  }

  getAllOrders()
  {
    this.orderService.getAllOrders().subscribe(
      {
        next:(orders:any)=>{
        this.orderList.set(orders);
        },
        error:()=>{
          alert("Unable to fetch orders");
        } 
      }

  );
  }

  deleteOrder(orderId:number)
  {
    this.orderService.deleteOrder(orderId).subscribe( 
      {
        next:()=>{
          alert("Order deleted successfully");  
          this.getAllOrders();
        },
        error:()=>{
          alert("Unable to delete order");
        }
      }
    );  
  }
}

class Order {
  id!:number;
  drugId!:number;
  drugName!:string;
  quantity!:number;
  // orderDate!:string;
  orderStatus!:string;
  totalAmount!:number;
  memberId!:number;
}