import { Component, inject } from '@angular/core';
import { Services } from '../../services';
import { AdminService } from '../admin-service';
import { routes } from '../../../app.routes';
import { Router } from '@angular/router';
import { AdminDashBoard } from '../admin-dash-board/admin-dash-board';
import { form } from '@angular/forms/signals';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dash-board',
  imports: [AdminDashBoard,FormsModule,CommonModule],
  templateUrl: './dash-board.html',
  styleUrl: './dash-board.css',
})
export class DashBoard {

  router=inject(Router);

  service=inject(Services);

  adminService=inject(AdminService);

  getAdminDetails=this.service.getDetails;

  // updatedData={
  //   name:'',
  //   email:'',
  //   password:'',
  //   mobilenumber:'',
  //   gender:''
  // };
  
}
