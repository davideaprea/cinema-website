import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrimarybuttonComponent } from './primarybutton.component';

describe('PrimarybuttonComponent', () => {
  let component: PrimarybuttonComponent;
  let fixture: ComponentFixture<PrimarybuttonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PrimarybuttonComponent]
    });
    fixture = TestBed.createComponent(PrimarybuttonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
