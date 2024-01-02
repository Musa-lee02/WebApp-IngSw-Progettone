import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InfoComponent } from './component/info/info.component';
import { ContattiComponent } from './component/contatti/contatti.component';
import { AppComponent } from './app.component';
import { AccediComponent } from './component/accedi/accedi.component';
import { HomeComponent } from './component/home/home.component';
import { EsploraComponent } from './component/esplora/esplora.component';
import { EffettuaAnnuncioComponent } from './component/effettua-annuncio/effettua-annuncio.component';

const routes: Routes = [
{ path :'', pathMatch:'full', redirectTo:'/Home'},
 {path: 'Home', component: HomeComponent},
 {path: 'Annuncio', component: EffettuaAnnuncioComponent },
 {path: 'Esplora', component: EsploraComponent},
 {path: 'Esplora/:ambito', component: EsploraComponent},
 {path: 'Accedi', component: AccediComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
