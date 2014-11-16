package org.jv.wicket

import org.apache.wicket.protocol.http.WebApplication

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see org.jv.wicket.Start#main(String[])
 */
class WicketApplication extends WebApplication {
	
	override getHomePage() {
		worldMap
	}
	
	override init() {
		super.init()
		// add your configuration here
	}
	
}