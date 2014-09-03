class CodeBreaker

  constructor: (combination) ->
    @validator = new CombinationValidator
    @matcher = new CombinationMatcher combination

  checkCombination: (combination) ->
    combination = combination?.toUpperCase()

    @matcher.calculateMatches combination if @validator.isValid combination
