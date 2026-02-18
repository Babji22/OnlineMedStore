import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Navbar } from './components/navbar/navbar';
import { Demo } from './components/demo/demo';
import { Services } from './components/services';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,Navbar,Demo,FormsModule,CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('MedStore');

  //  service=inject(Services);

    memberid=0;
   constructor( public service:Services)
   {
    this.memberid=this.service.getDetails.id;
   }
}
