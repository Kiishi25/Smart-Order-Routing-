import { TestBed, async } from '@angular/core/testing';
import { HomepageComponent } from './homepage.component';
describe('HomepageComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        HomepageComponent
      ],
    }).compileComponents();
  }));
  it('should create the homepage', async(() => {
    const fixture = TestBed.createComponent(HomepageComponent);
    const homepage = fixture.debugElement.componentInstance;
    expect(homepage).toBeTruthy();
  }));
  it(`should have as title 'homepage'`, async(() => {
    const fixture = TestBed.createComponent(HomepageComponent);
    const homepage = fixture.debugElement.componentInstance;
    expect(homepage.title).toEqual('homepage');
  }));
});
