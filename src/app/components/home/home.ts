import { Component, inject, signal } from '@angular/core';
import { DrugService } from '../drug-service';
import { Services } from '../services';
import { RouterLink } from '@angular/router';
import { UserService } from '../User/user-service';

@Component({
  selector: 'app-home',
  imports: [RouterLink],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

  memberId:number;  
  memberName:string;

  constructor(private service:UserService){
    this.memberId=this.service.user.id;
    this.memberName=this.service.user.name;
    console.log(this.memberId);
  }

  drugService=inject(DrugService);

  drugId=this.drugService.getDrugById;

  banner="assets/banner.png"

  drugList=signal<any[]>([]);

  ngOnInit(){
    this.getAllDrugs();
  }

  getAllDrugs()
  {
    console.log(this.memberId);
    this.drugService.getAllDrugs().subscribe({
      next:(response:any)=>{
        this.drugList.set(response);
        console.log(this.drugList);
      },
      error:()=>{
        alert("Unable to fetch drug list");
      }
    }); 
  }
 


  addToCart(drugId:number)
  {
    console.log(this.memberId);
    console.log(drugId);
    this.drugService.addToCart(this.memberId, drugId).subscribe({
      next:(response)=>{
        alert("Product added to cart successfully");
      },
      error:()=>{
        // console.log("Error adding product to cart");
        alert("Unable to add product to cart");
      }
    });
  }

}
