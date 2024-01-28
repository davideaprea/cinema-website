import { Directive, ElementRef, EventEmitter, HostListener, Output } from '@angular/core';
import { FastAverageColor } from 'fast-average-color';

@Directive({
  selector: '[appAvgImageColor]'
})
export class AvgImageColorDirective {

  @Output() avgImgColor: EventEmitter<string> = new EventEmitter();

  constructor(private imageElement: ElementRef<HTMLImageElement>) { }

  @HostListener('load', ['$event.target'])
  onImageLoad(){
    let image: HTMLImageElement = this.imageElement.nativeElement;
    const fac = new FastAverageColor();
    const colors = fac.getColor(image).value;
    this.avgImgColor.emit(`rgba(${colors[0]}, ${colors[1]}, ${colors[2]}, 0.4)`);
  }

}
