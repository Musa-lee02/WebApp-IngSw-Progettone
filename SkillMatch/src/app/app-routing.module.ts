import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InfoComponent } from './component/info/info.component';
import { ContattiComponent } from './component/contatti/contatti.component';
import { AppComponent } from './app.component';
import { AccediComponent } from './component/accedi/accedi.component';
import { HomeComponent } from './component/home/home.component';

const routes: Routes = [
{ path :'', pathMatch:'full', redirectTo:'/Home'},
 {path: 'Home', component: HomeComponent},
 {path: 'Info', component: InfoComponent},
 {path: 'Contatti', component: ContattiComponent},
 {path: 'Accedi', component: AccediComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
