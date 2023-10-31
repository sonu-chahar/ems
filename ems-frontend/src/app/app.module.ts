import { NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthHeaderInterceptor } from './auth-header-interceptor';
import { GlobalErrorComponent } from './global-error/global-error.component';


@NgModule({
  declarations: [
    AppComponent,
    GlobalErrorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthHeaderInterceptor,
    multi: true
}],
  bootstrap: [AppComponent]
})
export class AppModule { }
