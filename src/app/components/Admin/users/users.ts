import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-users',
  imports: [FormsModule,CommonModule],
  templateUrl: './users.html',
  styleUrl: './users.css',
})
export class Users {

  usersList = signal<user[]>([]);

  http=inject(HttpClient);

 ngOnInit()
  {
    this.usersData();
  }

  usersData()
  {
    this.http.get('http://localhost:8000/member/findall',
      // {
      //   responseType:'text'
      // }
    ).subscribe(
      {
        next:(data:any)=>{
          // alert("login sucessfull")
          this.usersList.set(data);
          console.log(this.usersList);
        },
        error:()=>
        {
          alert("Unable fetch the Users Data")
        }
      }
    )
  } 
  
  deleteUser(userId:number)
  {
    this.http.delete('http://localhost:8000/member/'+userId, 
    ).subscribe(
      {
        next:(responseType)=>{  
          alert("User deleted successfully");
        },
        error:()=>{
          alert("Unable to delete user");
        }
      }
    );
  }
}

class user
{
  id:number=0;
  name:String='';
  email:String='';
  gender:String='';
  date:String='';
}
