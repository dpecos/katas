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
    ((isMisplaced) -> result += '*' if isMisplaced) isMisplaced for isMisplaced in misplacedGuessesMap
    result

  mergeCorrectAndMisplacedGuesses: (correctGuessesMap, misplacedGuessesMap) ->
    i = 0
    misplacedGuessesMap = misplacedGuessesMap.map (guess) -> !correctGuessesMap[i++] and guess
