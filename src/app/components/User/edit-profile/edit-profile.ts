import { Component, inject } from '@angular/core';
import { Services } from '../../services';
import { Router } from '@angular/router';
import { UserService } from '../user-service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-profile',
  imports: [FormsModule,CommonModule],
  templateUrl: './edit-profile.html',
  styleUrl: './edit-profile.css',
})
export class EditProfile {

  service=inject(Services);

  userService=inject(UserService);

  router=inject(Router);

  getUserDetails=this.userService.user;

  address=this.userService.user.address;

  updateUserDetails()
  {
    console.log(this.getUserDetails);
    this.userService.updateUser(this.getUserDetails, this.address).subscribe(
      {
        next:(response:any)=>{
          alert("Profile Updated Successfully");
          this.getUserDetails=response;
          // this.address=this.getUserDetails.address;
          // console.log(this.getUserDetails);
          this.router.navigate(['/admin-dashBoard']);
        },
        error:()=>{
          alert("Error while updating profile");
        }
      }
    );
  }

  ngOnInit()
  {
    this.getUserAndAddressDetails();
  }

  getUserAndAddressDetails()
  {
    this.userService.getUserDetails(this.userService.user.id).subscribe(
      {
        next:(response:any)=>{
          this.getUserDetails=response;
          this.address=this.getUserDetails.address;
          console.log(this.address);
        }
      }
    );
  }
}
