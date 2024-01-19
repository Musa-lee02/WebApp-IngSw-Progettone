import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InfoComponent } from './component/info/info.component';
import { ContattiComponent } from './component/contatti/contatti.component';
import { AppComponent } from './app.component';
import { AccediComponent } from './component/accedi/accedi.component';
import { HomeComponent } from './component/home/home.component';
import { EsploraComponent } from './component/esplora/esplora.component';
import { EffettuaAnnuncioComponent } from './component/effettua-annuncio/effettua-annuncio.component';
import { ProfiloComponent } from './component/profilo/profilo.component';
import { ConfermaEmailComponent } from './component/accedi/conferma-email/conferma-email.component';
import { ChatComponent } from './component/chat/chat.component';
import { RecuperoComponent } from './component/accedi/recupero/recupero.component';
import { RinserisciPasswordComponent } from './component/accedi/rinserisci-password/rinserisci-password.component';
import { SearchBarComponent } from './component/search-bar/search-bar.component';
import { FooterComponent } from './component/footer/footer.component';



const routes: Routes = [

 {path: 'Home', component: HomeComponent},
 {path: 'Chat', component: ChatComponent },
 {path: 'Esplora/:ambito/:zona', component: EsploraComponent},
 {path: 'Accedi', component: AccediComponent},
 {path: 'Profilo', component: ProfiloComponent},
 {path: 'ConfermaAccount', component: ConfermaEmailComponent},
 {path: 'Annuncio', component: EffettuaAnnuncioComponent},
 {path: 'Recupero', component: RecuperoComponent},
 {path: 'Recupero/Reinserisci', component: RinserisciPasswordComponent},
 {path: 'search-bar', component: SearchBarComponent},
 {path: 'Footer', component: FooterComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
