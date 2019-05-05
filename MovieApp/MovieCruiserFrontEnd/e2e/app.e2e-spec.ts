import { AppPage } from './app.po';
import {browser, by, element} from 'protractor';

import { protractor } from 'protractor/built/ptor';



describe('movie-cruiser-frontend App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display Title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('MovieCruiserFrontend');
  });

  it('should be redirected to /user/login route on opening the application', () => {
    expect(browser.getCurrentUrl()).toContain('/user/login')
  });

  it('should be redirected to /user/register route', () => {
    browser.element(by.css('.register-user')).click()
    expect(browser.getCurrentUrl()).toContain('/user/register')
  });

  it('should be able to register user', () => {
    browser.element(by.id('firstName')).sendKeys('Super User');
    browser.element(by.id('lastName')).sendKeys('Super LastUser');
    browser.element(by.id('userId')).sendKeys('SuperUser');
    browser.element(by.id('password')).sendKeys('SuperUser12');
    browser.element(by.css('.register-user')).click()
    expect(browser.getCurrentUrl()).toContain('/user/login')
  });

  it('should be able to login user and navigate to popular movies', () => {
    browser.element(by.id('userId')).sendKeys('SuperUser');
    browser.element(by.id('password')).sendKeys('SuperUser12');
    browser.element(by.css('.login-user')).click()
    expect(browser.getCurrentUrl()).toContain('/movies/popular')
  });

  it('should be able to search for movies', () => {
    browser.element(by.css('.search-button')).click()
    expect(browser.getCurrentUrl()).toContain('/movies/search')
    browser.element(by.id('search-button-input')).sendKeys('Super')
    browser.element(by.id('search-button-input')).sendKeys(protractor.Key.RETURN)

    const searchItems=element.all(by.css('.movie-title'));
    expect(searchItems.count()).toBe(20);
    for(let i=1;i<1; i+=1){
      expect(searchItems.get(i).getText()).toContain('Super')
    }
  });

  it('should be able to add movie to watchlist', async () => {
   browser.driver.manage().window().maximize();
   browser.driver.sleep(1000);
   const searchItems=element.all(by.css('.movie-thumbnail'));
   expect(searchItems.count()).toBe(20);
   searchItems.get(0).click;
   browser.element(by.css('.add-watchlist-button')).click()
  });
  
});
