import { Component, inject } from '@angular/core';
import { Services } from '../../services';
import { Router } from '@angular/router';
import { AdminService } from '../admin-service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-settings',
  imports: [FormsModule,CommonModule],
  templateUrl: './settings.html',
  styleUrl: './settings.css',
})
export class Settings {


   router=inject(Router);


  adminService=inject(AdminService);

  service=inject(Services);

  getAdminDetails=this.adminService.admin;

  updateAdmin=this.adminService.admin;
  updateAdminDetails()
  {
    // console.log(this.getAdminDetails);
    this.adminService.updateAdmin(this.getAdminDetails).subscribe(
      {
        next:(response:any)=>{
          alert("Profile Updated Successfully");
          this.getAdminDetails=response;
          this.router.navigate(['/admin-dashBoard']);
        },
        error:()=>{
          alert("Error while updating profile");
        }
      }
    );
  }
}
