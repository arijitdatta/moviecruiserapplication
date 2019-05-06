import {TestBed, inject, fakeAsync, } from '@angular/core/testing';
import {HttpClientModule, HttpClient, HttpEvent, HttpEventType} from '@angular/common/http';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {AuthenticationService} from './authentication.service';
import {Observable} from 'rxjs';


const testConfig={
    addUser:{
        positive:{
            firstName:'test',
            lastName:'testlast',
            userId:'testuser',
            password:'testpass'    
        }
    },

    loginUser:{
        positive:{
            userId:'testuser',
            password:'testpass' 
        }
    }
}

describe('AuthenticationService', ()=>{
    let authService:AuthenticationService;
    beforeEach(()=>{
        TestBed.configureTestingModule({
            imports:[HttpClientModule, HttpClientTestingModule],
            providers:[AuthenticationService]

        });
        authService=TestBed.get(AuthenticationService);
    });

    it('should create authentication service', 
        inject([AuthenticationService], (service:AuthenticationService)=>{
            expect(service).toBeTruthy();
       
    }));

    it('should register user data', fakeAsync(()=>{
        let data=testConfig.addUser.positive;
        inject([AuthenticationService, HttpTestingController],(backend:HttpTestingController)=>{
            const mockReq=backend.expectOne(authService.springEndPoint);
            expect(mockReq.request.url).toEqual(authService.springEndPoint, 'requested url should match with json server api url');
            expect(mockReq.request.method).toBe('POST', 'Should handle requested method type');
            mockReq.flush(data);
            backend.verify();
        });


        authService.registerUser(data).subscribe((res:any)=>{
            expect(res).toBeDefined();
            expect(res._body).toBe(data,'data should be same');

        });

    }));

    it('should login user', fakeAsync(()=>{
        let userdata=testConfig.loginUser.positive;
        inject([AuthenticationService, HttpTestingController],(backend:HttpTestingController)=>{
            const mockReq=backend.expectOne(authService.springEndPoint);
            expect(mockReq.request.url).toEqual(authService.springEndPoint, 'requested url should match with json server api url');
            expect(mockReq.request.method).toBe('POST', 'Should handle requested method type');
            mockReq.flush(userdata);
            backend.verify();
        });


        authService.loginUser(userdata).subscribe((res:any)=>{
            expect(res).toBeDefined();
            expect(res._body).toBe(userdata,'data should be same');

        })

    }))
})