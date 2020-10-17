export interface DifficultLevel {
  testDifficultId: string;
  oneAnswerCount: number;
  oneAnswerThesisTypes: string[];
  oneAnswerByDefinitionCount: number;
  oneAnswerByDefinitionThesisTypes: string[];
  severalAnswerCount: number;
  severalAnswerThesisTypes: string[];
  matchCount: number;
  matchThesisTypes: string[];
  demoCodeCount: number;
  demoCodeThesisTypes: string[];
  unorderedListCount: number;
  unorderedListThesisTypes: string[];
}
