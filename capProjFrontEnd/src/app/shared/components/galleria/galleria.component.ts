import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { take } from 'rxjs';

@Component({
  selector: 'app-galleria',
  templateUrl: './galleria.component.html',
  styleUrls: ['./galleria.component.scss']
})
export class GalleriaComponent {
  @Input() photos!: string[];
  @ViewChild("img", { static: false }) imgRef!: ElementRef;
  cachedImages: {url:string, objUrl:string}[] = [];

  constructor(private http: HttpClient) { }

  loadImage(url: string) {
    let index = this.cachedImages.findIndex(photoUrl => photoUrl.url == url);

    if (index == -1) this.http.get(url, { responseType: 'blob' }).pipe(take(1)).subscribe(blob => {
      let objUrl=URL.createObjectURL(blob);
      this.cachedImages.push({url, objUrl});
      this.imgRef.nativeElement.src=objUrl;
    })
    else this.imgRef.nativeElement.src=this.cachedImages[index].objUrl;
  }

  imageChange(event: any){
    console.log(event)
  }
}
