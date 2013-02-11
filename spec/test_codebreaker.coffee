errorIllegalCombination = new Error('Illegal combination')
errorIllegalGuess = new Error('Illegal guess')

codeBreaker = null

describe 'Errors', ->

  beforeEach ->
    codeBreaker = new CodeBreaker 'xxxx'

  describe 'Incorrect invocations', ->

    it 'should fail without parameters', ->
      expect(-> codeBreaker.checkCombination()).toThrow(errorIllegalCombination)

    it 'should fail with undefined string', ->
      expect(-> codeBreaker.checkCombination null).toThrow(errorIllegalCombination)

    it 'should fail with empty string', ->
      expect(-> codeBreaker.checkCombination '').toThrow(errorIllegalCombination)

    it 'should fail with string of size different than 4', ->
      expect(-> codeBreaker.checkCombination 'a').toThrow(errorIllegalCombination)
      expect(-> codeBreaker.checkCombination 'aa').toThrow(errorIllegalCombination)
      expect(-> codeBreaker.checkCombination 'aaa').toThrow(errorIllegalCombination)
      expect(-> codeBreaker.checkCombination 'aaaaa').toThrow(errorIllegalCombination)

  describe 'Combinations with incorrect values', ->
    
    it 'should fail with not allowed values', ->
      expect(-> codeBreaker.checkCombination 'xxxx').toThrow(errorIllegalGuess)

    it 'should fail with just one not allowed values', ->
      expect(-> codeBreaker.checkCombination 'axmm').toThrow(errorIllegalGuess)

  describe 'Combinations with legal values', ->

    it 'should not throw an error when using just legal guesses', ->
      expect(codeBreaker.checkCombination 'rvni').toBe('')
      expect(codeBreaker.checkCombination 'rami').toBe('')

describe 'Matches in wrong positions', ->

  beforeEach ->
    codeBreaker = new CodeBreaker 'MARA'

  it 'should find one guess in incorrect position', ->
    expect(codeBreaker.checkCombination 'iiai').toBe("*")

  it 'should find two guesses in incorrect positions', ->
    expect(codeBreaker.checkCombination 'aiai').toBe("**")

  it 'should find three guesses in incorrect positions', ->
    expect(codeBreaker.checkCombination 'amai').toBe("***")

  it 'should find all guesses in incorrect positions', ->
    expect(codeBreaker.checkCombination 'amar').toBe("****")

describe 'Matches in correct positions', ->

  beforeEach ->
    codeBreaker = new CodeBreaker 'MARA'

  it 'should find one guess in correct position', ->
    expect(codeBreaker.checkCombination 'vvva').toBe("X")

  it 'should find two guesses in correct positions', ->
    expect(codeBreaker.checkCombination 'vvra').toBe("XX")

  it 'should find three guesses in correct positions', ->
    expect(codeBreaker.checkCombination 'vara').toBe("XXX")

  it 'should find find all guesses in correct positions', ->
    expect(codeBreaker.checkCombination 'mara').toBe("XXXX")

describe 'Matches correct and misplaced guesses', ->

  beforeEach ->
    codeBreaker = new CodeBreaker 'MARA'

  it 'should find one guess in correct position', ->
    expect(codeBreaker.checkCombination 'vvma').toBe("X*")

  it 'should find two correct guesses and one misplaced', ->
    expect(codeBreaker.checkCombination 'vama').toBe("XX*")

  it 'should find two correct guesses and two misplaced', ->
    expect(codeBreaker.checkCombination 'rama').toBe("XX**")

  it 'should find three correct guesses and one misplaced', ->
    expect(codeBreaker.checkCombination 'marr').toBe("XXX*")

  it 'should find one correct guess and three misplaced', ->
    expect(codeBreaker.checkCombination 'aamr').toBe("X***")

