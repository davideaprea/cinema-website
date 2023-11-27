import { Component } from '@angular/core';
import { Hall } from '../../models/hall';
import { HallService } from '../../services/hall.service';
import { HallStatus } from '../../models/hall-status';
import { ConfirmationService } from 'primeng/api';

@Component({
  selector: 'app-halls',
  templateUrl: './halls.component.html',
  styleUrls: ['./halls.component.scss'],
  providers: [ConfirmationService]
})
export class HallsComponent {
  halls: Hall[] = [];

  constructor(private hallService: HallService, private confirmationService: ConfirmationService) {
    hallService.getAll().subscribe(h => this.halls = h);
  }

  onHallCreation(hall:Hall){
    this.halls.push(hall);
  }

  toggleStatus(hall: Hall) {
    this.confirmationService.confirm({
      message: 'Are you sure that you want to set this hall unavailable?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        hall.status = hall.status == HallStatus.AVAILABLE ? HallStatus.UNDER_MAINTENANCE : HallStatus.AVAILABLE;
        this.hallService.editHall(hall).subscribe();
      }
    })
  }

  trackByFn(index: number, name: Hall): number {
    return name.id;
  }
}
