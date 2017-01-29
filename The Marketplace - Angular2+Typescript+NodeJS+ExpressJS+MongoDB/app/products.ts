import { Injectable } from 'angular2/core';

@Injectable()
export class Products {
    id: string;
    imgUrl: string;
    category : string;
    title: string;
    desc: string;
    amount: number;
    postDate : string;
    type : string;
}