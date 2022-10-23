const { flattenArray } = require('../secondCase')

describe('hitung jumlah kata', () => {
  it('should show the number of words of the sentence (100)', () => {
    const result1 = flattenArray([1, 2, [3, 4, [5, 5, 7]], 5, [6, 7], 8]);
    const result2 = flattenArray([[1, 2], [3, 4, [5, 5, 7]], 5, [6, 7], 8]);
    const result3 = flattenArray([1, [2, [3, 4, [5, 5, 7]]], 5, [6, 7], 8]);
    const result4 = flattenArray([1, 2, [3, 4, [5, 5, 7]], [5, [[6, 7], 8]]]);
    expect(result1).toEqual([1, 2, 3, 4, 5, 5, 7, 5, 6, 7, 8]);
    expect(result2).toEqual([1, 2, 3, 4, 5, 5, 7, 5, 6, 7, 8]);
    expect(result3).toEqual([1, 2, 3, 4, 5, 5, 7, 5, 6, 7, 8]);
    expect(result4).toEqual([1, 2, 3, 4, 5, 5, 7, 5, 6, 7, 8]);
  });
});
