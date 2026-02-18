import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ProductsEdit } from '../products-edit/products-edit';
import { Services } from '../../services';
import { RouterLink } from '@angular/router';
import { DrugService } from '../../drug-service';

@Component({
  selector: 'app-products',
  imports: [RouterLink,FormsModule,CommonModule,ProductsEdit],
  templateUrl: './products.html',
  styleUrl: './products.css',
})
export class Products implements OnInit {

  https=inject(HttpClient);

  productList=signal<product[]>([]);

  productService=inject(DrugService);

  data:number=0;

  // productService=inject(Services);

  productEdit(value:number)
  {
    this.data=value;
    for (let i = 0; i < this.productList().length; i++) {
      if(this.productList()[i].id==value)
      {
        this.productService.drug=this.productList()[i];
        break;
      }
      
    }
    // this.productService.productEdit(value);
  }

  ngOnInit()
  {
    this.productsData();
  }

  // constructor()
  // {
  //   this.productsData();
  // }

  productsData()
  {
    this.productService.getAllDrugs().subscribe(
    {
      next:(data:any)=>{
        this.productList.set(data); 
        console.log(this.productList);
      },
      error:()=>
      { 
        alert("Unable to fetch product details");
      }
    // this.https.get('http://localhost:8000/drug/alldrugs').subscribe(
    // {
    //   next:(data:any)=>{
    //    this.productList=data;
    //    console.log(this.productList);
    //   },
    //   error:()=>
    //   {
    //     alert("Unable to fetch product details");
    //   }
    }  
    );
  }

  deleteProduct(id:number)
  {
    // console.log(id);
    this.productService.deleteDrug(id).subscribe(
    {
      next:()=>{ 
        alert("Product deleted Sucessfully");
      },
      error:()=>
      {
        alert("Unable to Delete the product");
      } 
    });
  


  //   this.https.delete('http://localhost:8000/drug/'+id,{
  //     responseType:'text'
  //   }).subscribe(
  //   {
  //     next:()=>
  //     {
  //       alert("Product deleted Sucessfully");
  //     },
  //     error:()=>
  //     {
  //       alert("Unable to Delete the product");
  //     }
  //   }  
  //   )
  // }
}
}

class product{
  id:number=0;
  name:String='';
  type:String='';
  price:number=0;
  quantity:number=0;
}
