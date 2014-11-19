package org.jv.wicket;

import model.GameSystem;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.jv.wicket.WorldMapPage;
import testingData.TestingData;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see org.jv.wicket.Start#main(String[])
 */
@SuppressWarnings("all")
public class WicketApplication extends WebApplication {
  private GameSystem gameSystem = new TestingData().createGameSystem();
  
  public Class<? extends Page> getHomePage() {
    return WorldMapPage.class;
  }
  
  public void init() {
    super.init();
  }
}
