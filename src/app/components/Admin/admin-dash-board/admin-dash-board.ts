import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { DashBoard } from '../dash-board/dash-board';
import { Orders } from '../orders/orders';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Users } from '../users/users';
import { Products } from '../products/products';
import { Settings } from '../settings/settings';
import { Services } from '../../services';
import { Userdashboard } from '../../User/userdashboard/userdashboard';
import { Cart } from '../../User/cart/cart';
import { EditProfile } from '../../User/edit-profile/edit-profile';
import { Userorders } from '../../User/userorders/userorders';
import { window } from 'rxjs';
import { Home } from '../../home/home';

@Component({
  selector: 'app-admin-dash-board',
  imports: [RouterLink,DashBoard,Orders,FormsModule,CommonModule,Users,Products,Settings,Userdashboard,Cart,EditProfile,Userorders,Home],
  templateUrl: './admin-dash-board.html',
  styleUrl: './admin-dash-board.css',
})
export class AdminDashBoard {
data:String='';

router=inject(Router);

service=inject(Services);

check(value:String)
{
  if(value=='logOut')
  {
    // this.service.isAdminLoggedIn=false;
    // window.location.reload();
    this.router.navigate(['/home']);
    return;
  }
  this.data=value;
}
}
