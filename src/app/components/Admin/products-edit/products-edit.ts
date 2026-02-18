import { Component, inject } from '@angular/core';
import { Services } from '../../services';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DrugService } from '../../drug-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-products-edit',
  imports: [FormsModule,CommonModule],
  templateUrl: './products-edit.html',
  styleUrl: './products-edit.css',
})
export class ProductsEdit {

  router=inject(Router);

  productService=inject(DrugService);

  product:product =this.productService.drug;

constructor() {
  this.check();
}

check():product
{
  // console.log(this.product);
  return this.product;
}

close()
{
  this.router.navigate(['/admin-dashBoard/products']);
}

updateProduct()
{
  this.productService.updateDrug(this.product).subscribe(
    {
      next:()=>{
        alert("Product details updated successfully");
        this.router.navigate(['/admin-dashBoard/products']);
      },
      error:()=>{
        alert("Unable to update product details");
      }
    });
}

}
class product{
  id:number=0;
  name:String='';
  type:String='';
  price:number=0;
  quantity:number=0;
}
