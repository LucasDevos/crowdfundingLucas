import { Donation } from './Donation';

export class Contributor{
    id:number;
    nickname:string;
    donation:Donation;
    total:number;
    tempAmount:number;
    avatar:string;
}