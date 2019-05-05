import {User} from '../../user';
import {AuthenticationService} from '../../authentication.service';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {Location} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {TestBed, inject, fakeAsync, ComponentFixture, tick} from '@angular/core/testing';
import {By} from '@angular/platform-browser';
import {HttpClientModule, HttpClient, HttpEvent, HttpEventType} from '@angular/common/http';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule, MatIcon} from '@angular/material';
import {MatInputModule} from '@angular/material';
import { LoginComponent } from 'src/app/modules/authentication/components/login/login.component';


const testConfig={
    userData:{
        positive:{
            firstName:'test',
            lastName:'testlast',
            userId:'testuser',
            password:'testpass'    
        }
    }
}

describe('LoginComponent', ()=>{
    let fixture:ComponentFixture<LoginComponent>;
    let authService: AuthenticationService;
    let spyUser: any;
    let loginComponent: LoginComponent;
    let routes: Router;
    let location: Location;

    class AuthServiceStub{
        currentUser:any;
        constructor(){

        }
        login(credentials){
            if(credentials.userId==testConfig.userData.userId){
                console.log('deta111', this.currentUser);
                return Observable.of(credentials.userId);
                l, 
            }
            return Observable.of(false);
        }
    }
    class dummy{

    }

    beforeEach(async(()=>{
        TestBed.configureTestingModule({
            declarations:[
                LoginComponent,
            ],
            imports:[FormsModule,HttpClientModule,MatFormFieldModule,
                 MatInputModule,MatButtonModule,BrowserAnimationsModule, RouterTestingModule.withRoutes(
                     [{path: '', component:dummy}])],
                     providers:[{provide:AuthenticationService, useClass: AuthServiceStub}],
        }).compileComponents();
    }));

    beforeEach(()=>{
        routes=TestBed.get(Router)
        fixture=TestBed.createComponent(LoginComponent);
        location=TestBed.get(Location);
        loginComponent=fixture.componentInstance;
        fixture.detectChanges();
        fixture.debugElement.injector.get(AuthenticationService);
    });



})


