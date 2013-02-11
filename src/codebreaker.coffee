class CodeBreaker

  constructor: (combination) ->
    @validator = new CombinationValidator
    @matcher = new CombinationMatcher combination

  checkCombination: (combination) ->
    combination = combination.toUpperCase() if combination

    @matcher.calculateMatches combination if @validator.isValid combination

class CombinationValidator
  validGuesses = 'RAMVNI'.split ''
  length = 4

  isValid: (combination) ->
    throw new Error 'Illegal combination' if not combination? or combination?.length != @length
    throw new Error 'Illegal guess' if not @isValidCombination combination
    true

  isValidCombination: (combination) ->
    guessesValidMap = (@isValidGuess guess for guess in combination.split '')
    guessesValidMap.every (guess) -> guess

  isValidGuess: (guess) ->
    guess in validGuesses

class CombinationMatcher
  constructor: (combination) ->
    @correctCombination = combination.toUpperCase()

  calculateMatches: (combination) ->
    correctGuessesMap = @checkCorrectGuesses combination
    misplacedGuessesMap = @checkMisplacedGuesses combination

    @calculateMatchString correctGuessesMap, misplacedGuessesMap
       
  checkCorrectGuesses: (combination) ->
    isGuessInCorrectPosition = (position, guess) => @correctCombination[position] == guess
    i = 0
    (isGuessInCorrectPosition i++, guess for guess in combination.split '')

  checkMisplacedGuesses: (combination) ->
    isGuessMisplaced = (guess) => @correctCombination.indexOf(guess) >= 0
    (isGuessMisplaced guess for guess in combination.split '')

  calculateMatchString: (correctGuessesMap, misplacedGuessesMap) ->
    misplacedGuessesMap = @mergeCorrectAndMisplacedGuesses correctGuessesMap, misplacedGuessesMap

    result = ''
    ((isOk) -> result += 'X' if isOk) isOk for isOk in correctGuessesMap
    ((isOk) -> result += '*' if isOk) isOk for isOk in misplacedGuessesMap
    result

  mergeCorrectAndMisplacedGuesses: (correctGuessesMap, misplacedGuessesMap) ->
    i = 0
    misplacedGuessesMap = misplacedGuessesMap.map (guess) -> !correctGuessesMap[i++] and guess

