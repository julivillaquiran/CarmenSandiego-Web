package org.jv.wicket

import org.apache.wicket.protocol.http.WebApplication
import model.GameSystem
import testingData.TestingData
import appmodel.CreateCountryAppModel
import country.Country

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see org.jv.wicket.Start#main(String[])
 */
class WicketApplication extends WebApplication {
	GameSystem gameSystem = new TestingData().createGameSystem()
//	Country pais = new Country()
//	CreateCountryAppModel createCountryAppModel = new CreateCountryAppModel(gameSystem, pais)
	override getHomePage() {
		WorldMapPage
	}
	
	override init() {
		super.init()
		// add your configuration here
	}
	
}