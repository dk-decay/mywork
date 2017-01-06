import { Injectable } from 'angular2/core';

@Injectable()
export class Users {
    id: string;
    fullname: string;
    email:string;
    username:string;
    password:string;
    confirmPassword:string;
    dateOfBirth:string;
    education:string;
    selfDescription:string;
    hobbies:string;

}