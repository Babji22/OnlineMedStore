import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Services } from '../services';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink,FormsModule,CommonModule],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {

  // service=inject(Services);

  
  memberId:number;  
  memberName:string;

  constructor(private service:Services){
    this.memberId=this.service.getDetails.id;
    this.memberName=this.service.getDetails.name;
    console.log(this.memberId);
  }

  // memberName=this.service.getDetails.name;
}
