import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InpCheckBoxComponent } from './inp-check-box.component';

describe('InpCheckBoxComponent', () => {
  let component: InpCheckBoxComponent;
  let fixture: ComponentFixture<InpCheckBoxComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InpCheckBoxComponent]
    });
    fixture = TestBed.createComponent(InpCheckBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
