import { Project } from './Project';
import { Contributor } from './Contributor';

export class Donation{
    id:number;
    amount:number;
    project:Project;
    contributor:Contributor;
    dateTime:Date;
    donationType:string;
}