import { Reponse } from './reponse';

export interface Question {
    score: number;
    title: string;
    reponses : Reponse[];
}
