import { Component, inject } from '@angular/core';
import { Services } from '../../services';

@Component({
  selector: 'app-userdashboard',
  imports: [],
  templateUrl: './userdashboard.html',
  styleUrl: './userdashboard.css',
})
export class Userdashboard {

  service=inject(Services);

  getUserDetails=this.service.getDetails;

}
