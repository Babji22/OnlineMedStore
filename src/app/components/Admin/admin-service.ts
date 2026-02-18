import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AdminService 
{
  https=inject(HttpClient);

  admin=new Admin();

  adminRegister(admin: Admin)
  {
    return this.https.post('http://localhost:8000/admin/register',admin,{
        responseType:'text'
    });
  }

  updateAdmin(admin: Admin)
  {
    return this.https.put('http://localhost:8000/admin/update',admin);
  }

  adminLogin(email:String,password:String)
  {
    return this.https.get('http://localhost:8000/admin/login/'+email+'/'+password);
  }






}

class Admin
{
  name:String='';
  email:String='';
  password:String='';
  mobilenumber:String='';
  gender:String=''
}
