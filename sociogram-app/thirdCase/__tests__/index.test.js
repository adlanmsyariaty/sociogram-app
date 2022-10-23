const { breakdown } = require('../thirdCase')

describe('hitung jumlah kata', () => {
  it('should show the number of words of the sentence (100)', () => {
    const result1 = breakdown(12345678);
    const result2 = breakdown(87654321);
    const result3 = breakdown(4573634275);
    expect(result1).toEqual([10000000, 2000000, 300000, 40000, 5000, 600, 70, 8]);
    expect(result2).toEqual([80000000, 7000000, 600000, 50000, 4000, 300, 20, 1]);
    expect(result3).toEqual([4000000000, 500000000, 70000000, 3000000, 600000, 30000, 4000, 200, 70, 5]);
  });
});
