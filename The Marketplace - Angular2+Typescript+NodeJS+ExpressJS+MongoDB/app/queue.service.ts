import {Injectable} from 'angular2/core';
import {Subject} from 'rxjs/Subject';
import { Observable } from 'rxjs/Observable';

export class QueueService {

    private parentQueue = new Subject<string>();
    private childQueue = new Subject<string>();

    parentQOb$ = this.parentQueue.asObservable();
        childQOb$ = this.childQueue.asObservable();


        sendParent(str : string) {
            this.parentQueue.next(str);

        }


        sendChild(str : string) {
            this.childQueue.next(str);

        }
}