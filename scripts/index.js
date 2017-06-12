var LineByLineReader = require('line-by-line'),
    lr = new LineByLineReader('QA_Video_Games.json');

lr.on('error', function (err) {
    // 'err' contains error object
});

lr.on('line', function (line) {
    const obj = JSON.parse(line);

    console.log(obj);
    // 'line' contains the current line without the trailing newline character.
});

lr.on('end', function () {
    // All lines are read, file is closed now.
    console.log('The end');
});