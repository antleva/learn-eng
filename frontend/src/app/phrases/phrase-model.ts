export class PhraseModel {
    id: number;
    type: string;
    description: string;
    translation: string;
    constructor(options: {
            type: string,
            description: string,
            translation: string
          }) {
          this.type = options.type;
          this.description = options.description;
          this.translation = options.translation;
        }




}
