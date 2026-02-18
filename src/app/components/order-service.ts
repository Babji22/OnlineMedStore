import { HttpClient } from '@angular/common/http';
import { inject, Injectable, Signal, signal } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  
https=inject(HttpClient);

orderedProducts:orderProducts[]=[];

  getOrderList(memberId:number)
  {
    return this.https.get('http://localhost:8000/order/getorders/'+memberId);
  }

  getAllOrders()
  {
    return this.https.get('http://localhost:8000/order/allorders');
  }

  getUserOrders(memberId:number)
  {
    return this.https.get('http://localhost:8000/order/getorders/'+memberId);
  }

  deleteOrder(orderId:number)
  {
    return this.https.delete('http://localhost:8000/order/'+orderId,{
      responseType:'text'
    });
  }
 
orderProduct(cartProducts: any[]) {
  for (let i = 0; i < cartProducts.length; i++) {
    const element = cartProducts[i];
    const orderProd = new orderProducts();
    orderProd.memberId = element.memberId;
    orderProd.drugId = element.drugId;
    orderProd.drugName=element.drugName;
    orderProd.quantity = element.quantity;

    this.orderedProducts.push(orderProd);
  }
  console.log(this.orderedProducts);
  return this.https.post('http://localhost:8000/order/', this.orderedProducts,{
    responseType:'text'
  });
}
}

class orderProducts
{
  id: number=0;
  memberId:number=0;
  drugName:string='';
  drugId:number=0;
  quantity:number=0;
  orderAmount:number=0;
  status_order:boolean=false;
}