import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InfoComponent } from './component/info/info.component';
import { ContattiComponent } from './component/contatti/contatti.component';
import { AppComponent } from './app.component';
import { AccediComponent } from './component/accedi/accedi.component';
import {RegisterComponent} from "./component/register/register.component";

const routes: Routes = [
  {path :'', pathMatch:'full', redirectTo:'/home'},
 {path: 'Home', component: AppComponent},
 {path: 'Info', component: InfoComponent},
 {path: 'Contatti', component: ContattiComponent},
 {path: 'Accedi', component: AccediComponent},
  {path: 'Register', component: RegisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
