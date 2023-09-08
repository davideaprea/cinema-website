import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InpTextComponent } from './inp-text.component';

describe('InpTextComponent', () => {
  let component: InpTextComponent;
  let fixture: ComponentFixture<InpTextComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InpTextComponent]
    });
    fixture = TestBed.createComponent(InpTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
