import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubcategoryManageComponent } from './subcategory-manage.component';

describe('SubcategoryManageComponent', () => {
  let component: SubcategoryManageComponent;
  let fixture: ComponentFixture<SubcategoryManageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SubcategoryManageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SubcategoryManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
