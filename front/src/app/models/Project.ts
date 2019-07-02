import { Category } from './Category';
import { Contributor } from './Contributor';
import { ProjectUpdate } from './ProjectUpdate';

export class Project{
    id:number;
    title:string;
    pitch:string;
    description:string;
    thumbnailPath:string;
    category:Category;
    donationGoal:number;
    donationCurrent:number;
    submissionDate:Date;
    endDate:Date;
    updates:ProjectUpdate[];
    creator:Contributor;
}