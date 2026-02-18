import { Routes } from '@angular/router';
import { Home } from './components/home/home';
import { Login } from './components/login/login';
import { Signin } from './components/signin/signin';
import { AdminDashBoard } from './components/Admin/admin-dash-board/admin-dash-board';
import { DashBoard } from './components/Admin/dash-board/dash-board';
import { Users } from './components/Admin/users/users';
import { Settings } from './components/Admin/settings/settings';
import { ProductsEdit } from './components/Admin/products-edit/products-edit';
import { Products } from './components/Admin/products/products';
import { Orders } from './components/Admin/orders/orders';
import { AddProduct } from './components/Admin/add-product/add-product';
import { EditProfile } from './components/User/edit-profile/edit-profile';
import { Cart } from './components/User/cart/cart';
import { Userorders } from './components/User/userorders/userorders';
import { Userdashboard } from './components/User/userdashboard/userdashboard';
import { Navbar } from './components/navbar/navbar';

export const routes: Routes = [
    { path: '' , component : Home},
    { path: 'home' , component : Home},
    { path: 'login' , component : Login},
    { path: 'signin' , component : Signin},
    { path: 'dashBoard' , component : DashBoard},
    { path: 'admin-dashBoard' , component : AdminDashBoard},
    { path: 'users' , component : Users},
    { path: 'orders' , component : Orders},
    { path: 'products' , component : Products},
    { path: 'products-edit' , component : ProductsEdit},
    { path: 'add-product' , component : AddProduct},
    { path: 'settings' , component : Settings},
    { path: 'userDashboard' , component : Userdashboard},
    { path: 'editProfile' , component : EditProfile},
    { path: 'cart' , component : Cart},
    { path: 'userOrders' , component : Userorders},
    {path: 'navbar', component: Navbar}
    
    
];
