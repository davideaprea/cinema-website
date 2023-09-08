import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InpPasswordComponent } from './inp-password.component';

describe('InpPasswordComponent', () => {
  let component: InpPasswordComponent;
  let fixture: ComponentFixture<InpPasswordComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InpPasswordComponent]
    });
    fixture = TestBed.createComponent(InpPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
