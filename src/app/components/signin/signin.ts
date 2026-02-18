import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { AdminService } from '../Admin/admin-service';
import { UserService } from '../User/user-service';
import { Services } from '../services';

@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [RouterLink,FormsModule,CommonModule],
  templateUrl: './signin.html',
  styleUrl: './signin.css',
})
export class Signin {

constructor(private https: HttpClient)
{}



adminService=inject(AdminService);

userService=inject(UserService);

  registerData={
    id:0,
    name:'',
    email:'',
    password:'',
    mobilenumber:'',
    gender:'',
    // address:{
    //   id:0,
    //   street:'',
    //   city:'',
    //   state:'',
    //   country:'',
    //   pinCode:''
    // }
  };

  member:boolean=false;

  register()
  {
    // console.log(this.registerData);
    // console.log(this.member);
    if(this.member==true)
    {
    // this.https.post('http://localhost:8000/admin/register',this.registerData,
    this.adminService.adminRegister(this.registerData).subscribe(
      {
        next:()=>{
          alert("registered sucessfully");
          
        },
        error:()=>
        {
          alert("registeration failed");
        }
      }
    )
  }
  else
  {
   this.userService.userRegister(this.registerData).subscribe(
      {
        next:()=>{
          alert("registered sucessfully");
        },
        error:()=>
        {
          alert("registeration failed");
        }
      }
    );
  }
}

}
