export class ResultModel {
  totalPhrases: number;
  wrongAnswers: number;
  rightAnswers: number;
  percent: number;
  date: string;
  constructor(options: {
        totalPhrases: number,
        wrongAnswers: number,
        rightAnswers: number
      }) {
      this.totalPhrases = options.totalPhrases;
      this.wrongAnswers = options.wrongAnswers;
      this.rightAnswers = options.rightAnswers;
    }

}
