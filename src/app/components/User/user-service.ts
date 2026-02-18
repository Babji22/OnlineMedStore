import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  
  https=inject(HttpClient);

  user=new User();

  address=new addressDTO();

  userRegister(user: any)
  {
    // user.address=this.address;
    return this.https.post('http://localhost:8000/member/register',user,{
      responseType:'text'
      });
  }

  updateUser(user: any, givenaddress?:any)
  {
      user.address=givenaddress;
      console.log(user);
    return this.https.put('http://localhost:8000/member/update',user
    );
  }

  userLogin(email:String,password:String)
  {
    return this.https.get('http://localhost:8000/member/login/'+email+'/'+password);
  }

  getUserDetails(memberId:number)
  {
    return this.https.get('http://localhost:8000/member/getmember/'+memberId);
  }

  deleteUser(memberId:number)
  {
    return this.https.delete('http://localhost:8000/member/'+memberId,{
      responseType:'text'
    });
  }
}

class addressDTO
  {
    id:number=0;
    street:string='';
    city:string='';
    state:string='';
    country:string='';
    pinCode:string='';
}

class User
{
  id:number=0;
  name:string='';
  email:string='';
  password:string='';
  mobilenumber:string='';
  gender:string=''
  address=new addressDTO();
}


