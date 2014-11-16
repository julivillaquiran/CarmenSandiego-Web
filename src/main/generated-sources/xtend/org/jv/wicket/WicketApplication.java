package org.jv.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.jv.wicket.worldMap;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see org.jv.wicket.Start#main(String[])
 */
@SuppressWarnings("all")
public class WicketApplication extends WebApplication {
  public Class<? extends Page> getHomePage() {
    return worldMap.class;
  }
  
  public void init() {
    super.init();
  }
}
