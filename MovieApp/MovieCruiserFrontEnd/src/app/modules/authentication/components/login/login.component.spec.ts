import {User} from '../../user';
import {AuthenticationService} from '../../authentication.service';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {of} from 'rxjs';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {Location} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {TestBed, inject, async, ComponentFixture, tick} from '@angular/core/testing';
import {By} from '@angular/platform-browser';
import {HttpClientModule, HttpClient, HttpEvent, HttpEventType} from '@angular/common/http';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule, MatIcon} from '@angular/material';
import {MatInputModule} from '@angular/material';
import { LoginComponent } from './login.component';


const testConfig={
    userData:{
        
            firstName:'test',
            lastName:'testlast',
            userId:'testuser',
            password:'testpass'    
        
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
                return of(credentials.userId);
                 
            }
            return of(false);
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

    it('should create the app component', async(()=>{
        const app=fixture.debugElement.componentInstance;
        expect(app).toBeTruthy();
    }));

    it('should contain two input boxes for userId and Password', ()=>{
        let userId=fixture.debugElement.query(By.css('.inputID'));
        let password=fixture.debugElement.query(By.css('.inputPass'));
        let registerButton=fixture.debugElement.query(By.css('.register-button'));
        let userButton=fixture.debugElement.query(By.css('.login-user'));
        let userIdInput=userId.nativeElement;
        let passwordInput=password.nativeElement;
        let registerButtonInput=registerButton.nativeElement;
        let userButtonInput=userButton.nativeElement;

        expect(userIdInput).toBeTruthy();
        expect(passwordInput).toBeTruthy();
        expect(registerButtonInput).toBeTruthy();
        expect(userButtonInput).toBeTruthy();
    })

    it('should redirect to login if registered successfully', async(()=>{
        
        let userId=fixture.debugElement.query(By.css('.inputID'));
        let password=fixture.debugElement.query(By.css('.inputPass'));
        
        let userButton=fixture.debugElement.query(By.css('.login-user'));
        let userIdInput=userId.nativeElement;
        let passwordInput=password.nativeElement;
        let userButtonInput=userButton.nativeElement;
        fixture.detectChanges();
        fixture.whenStable().then(()=>{
            userIdInput.value='testuser';
            passwordInput.value='testpass';
            userIdInput.dispatchEvent(new Event('input'));
            passwordInput.dispatchEvent(new Event('input'));
            userButtonInput.click();
        }).then(()=>{
            expect(location.path()).toBe('');
        })
       
    }))

})


