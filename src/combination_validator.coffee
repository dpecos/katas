class CombinationValidator
  validGuesses = 'RAMVNI'.split ''
  validLength = 4

  isValid: (combination) ->
    throw new Error 'Illegal combination' if not combination? or combination?.length != validLength
    throw new Error 'Illegal guess' if not @isValidCombination combination
    true

  isValidCombination: (combination) ->
    guessesValidMap = (@isValidGuess guess for guess in combination.split '')
    guessesValidMap.every (guess) -> guess

  isValidGuess: (guess) ->
    guess in validGuesses
