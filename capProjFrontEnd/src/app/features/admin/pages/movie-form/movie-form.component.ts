import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Genres } from 'src/app/features/admin/models/genres';
import { MovieService } from '../../services/movie.service';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { IFormComponent } from 'src/app/core/models/i-form-component';

@Component({
  selector: 'app-movie-form',
  templateUrl: './movie-form.component.html',
  styleUrls: ['./movie-form.component.scss'],
  providers: [ConfirmationService]
})
export class MovieFormComponent implements IFormComponent {
  readonly columnStyles = "col-12 sm:col-6 lg:col-4 xl:col-3";

  f!: FormGroup;
  today: Date;
  genres: SelectItem[];

  constructor(private movieService: MovieService, private messageService: MessageService, private confirmationService: ConfirmationService) {
    this.today = new Date();
    this.genres = Object.entries(Genres).map(item => {
      return { label: item[0], value: item[1] } as SelectItem
    });

    this.f = new FormGroup({
      title: new FormControl("", Validators.required),
      cover: new FormControl(null, Validators.required),
      backgroundCover: new FormControl(null, Validators.required),
      releaseDate: new FormControl("", Validators.required),
      duration: new FormControl("", Validators.required),
      director: new FormControl("", Validators.required),
      actors: new FormControl("", Validators.required),
      description: new FormControl("", [Validators.required, Validators.maxLength(600)]),
      genres: new FormControl("", Validators.required),
      isTridimensional: new FormControl(false)
    });
  }

  isFormDirty(): boolean {
    return this.f.dirty;
  }

  uploadCover(event: any) {
    this.f.controls['cover'].setValue(event.currentFiles[0]);
  }

  uploadBackgroundCover(event: any) {
    this.f.controls['backgroundCover'].setValue(event.currentFiles[0]);
  }

  openConfirmationDialog(): Promise<boolean> {
    return new Promise<boolean>(resolve =>
      this.confirmationService.confirm({
        message: 'Are you sure you want to leave this page? All the data will be lost.',
        header: 'Warning',
        icon: 'pi pi-exclamation-triangle',
        accept: () => resolve(true),
        reject: () => resolve(false)
      })
    )
  }

  submit() {
    this.movieService.create(this.f.value).subscribe(() => {
      this.f.reset();
      this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Movie added successfully.' });
    });
  }
}
