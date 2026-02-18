import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Services {
  
  http=inject(HttpClient);

  loginSuccessful:boolean=false;

  // getAddress=
  // {
  //   id:0, 
  //   street:'',
  //   city:'',
  //   state:'',
  //   country:'',
  //   pinCode:''
  // };

  getDetails= 
  {
    id:0,
    name:'',
    email:'',
    password:'',
    mobilenumber:'',
    gender:'',
  //   address:{
  //   id:0, 
  //   street:'',
  //   city:'',
  //   state:'',
  //   country:'',
  //   pinCode:''
  // }
  };

  



  member=false;

  product:product=new product;

  productEdit(productData:any)
  {
    // this.http.get('http://localhost:8000/drug/'+id).subscribe(
    // {
    //   next:(data:any)=>{
    //    this.product=data;
    //    console.log(this.product);
    //   },
    //   error:()=>
    //   {
    //     alert("Unable to fetch product details");
    //   }
    // }  
    // )
    this.product=productData;
    // console.log(this.product);


  }

}


class product{
  id:number=0;
  name:String='';
  type:String='';
  price:number=0;
  quantity:number=0;
}