import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminOptionComponent } from './admin-option.component';

describe('AdminOptionComponent', () => {
  let component: AdminOptionComponent;
  let fixture: ComponentFixture<AdminOptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminOptionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminOptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
