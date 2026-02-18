import { Component, inject, signal } from '@angular/core';
import { OrderService } from '../../order-service';
import { Services } from '../../services';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UserService } from '../user-service';

@Component({
  selector: 'app-userorders',
  imports: [FormsModule,CommonModule],
  templateUrl: './userorders.html',
  styleUrl: './userorders.css',
})
export class Userorders {

  orderService=inject(OrderService);

  userService=inject(UserService);


  
  userOrderList=signal<Order[]>([]);

 ngOnInit()
  {
    this.getUserOrders(this.userService.user.id);
  }


  getUserOrders(memberId: number)
  {
    this.orderService.getUserOrders(memberId).subscribe(
      {
        next:(orders:any)=>{ 
        this.userOrderList.set(orders);
        console.log(this.userOrderList());
        },
        error:()=>{
          alert("Unable to fetch orders");
        } 
      }
    );
  }

}

class Order {
  id!:number;
  drugId!:number;
  memberId!:number;
  drugName!:string;
  quantity!:number;
  orderStatus!:boolean;
  orderAmount!:number;
}
