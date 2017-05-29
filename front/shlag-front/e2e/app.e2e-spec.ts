import { ShlagFrontPage } from './app.po';

describe('shlag-front App', function() {
  let page: ShlagFrontPage;

  beforeEach(() => {
    page = new ShlagFrontPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
