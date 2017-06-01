import { ShlagFrontPage } from './app.po';

describe('shlag-front App', () => {
  let page: ShlagFrontPage;

  beforeEach(() => {
    page = new ShlagFrontPage();
  });

  it('should display welcome message', done => {
    page.navigateTo();
    page.getParagraphText()
      .then(msg => expect(msg).toEqual('Welcome to app!!'))
      .then(done, done.fail);
  });
});
