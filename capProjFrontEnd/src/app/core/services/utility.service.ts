import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilityService {

  constructor() { }

  getLocalStorageItem<T>(key:string):T | undefined{
    const item=localStorage.getItem(key);
    return item ? JSON.parse(item) : undefined;
  }

  getSessionStorageItem<T>(key: string): T | undefined {
    const item = sessionStorage.getItem(key);
    return item ? JSON.parse(item) : undefined;
  }
}
