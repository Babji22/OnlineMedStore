import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AdminDashBoard } from '../Admin/admin-dash-board/admin-dash-board';
import { Userdashboard } from '../User/userdashboard/userdashboard';
import { Services } from '../services';
import { AdminService } from '../Admin/admin-service';
import { UserService } from '../User/user-service';

@Component({
  selector: 'app-login',
  imports: [RouterLink,FormsModule,CommonModule,AdminDashBoard,Userdashboard],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  http=inject(HttpClient);

  service=inject(Services);

  adminService=inject(AdminService);

  userService=inject(UserService);

  member:boolean=false;

  constructor(private router:Router)
  {

  }

  getAdminData={
    name:'',
    email:'',
    password:'',
    mobilenumber:'',
    gender:''
  };

  userEmail:String='';
  userPassword:String ='';

  loginData()
  {
    console.log(this.userEmail);
    console.log(this.userPassword);
    
    
    if(this.member==true)
    {
      this.adminService.adminLogin(this.userEmail,this.userPassword
      ).subscribe(
      {
        next:(response:any)=>{
          this.getAdminData=response;
          console.log(response);
          alert("login sucessfull")
          this.router.navigate(['/admin-dashBoard']);
          this.service.loginSuccessful=true;
          this.service.member=this.member;
          this.adminService.admin=response;
        },
        error:()=>
        {
          alert("login unsucessfull")
        }
      }
    );
    }
    else
    {
      this.userService.userLogin(this.userEmail,this.userPassword)
      .subscribe(
      {
        next:(response:any)=>{
          alert("login sucessfull")
             this.router.navigate(['/']);
          // this.router.navigate(['/navbar']);
          this.service.loginSuccessful=true;
       
          this.service.member=this.member;
          this.userService.user=response;
          console.log(this.userService.user);
        },
        error:()=>
        {
          alert("login unsucessfull")
        }
      }
      );
    }
  }
}
