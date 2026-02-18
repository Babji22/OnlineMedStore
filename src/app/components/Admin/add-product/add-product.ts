import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-add-product',
  imports: [RouterLink,FormsModule,CommonModule],
  templateUrl: './add-product.html',
  styleUrl: './add-product.css',
})
export class AddProduct {

  product:product =new product();

  https=inject(HttpClient);


  addProduct(){
    this.https.post("http://localhost:8000/drug/addProduct",this.product,{responseType:'text'}).subscribe(
      {
        next:()=>{
          alert("Product Added Successfully");
        },
        error:()=>{
          alert("Error while adding product");
        }
      }
    )
  }
}

class product{
  id:number=0;
  name:String='';
  type:String='';
  price:number=0;
  quantity:number=0;
}
