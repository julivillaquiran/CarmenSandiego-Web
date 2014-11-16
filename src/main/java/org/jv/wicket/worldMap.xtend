package org.jv.wicket

import org.uqbar.wicket.xtend.WicketExtensionFactoryMethods
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import org.uqbar.wicket.xtend.XLink

import model.GameSystem
import testingData.TestingData
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.model.PropertyModel
import villain.Villain
import java.util.List
import org.uqbar.wicket.xtend.XListView
import country.Country
import org.apache.wicket.markup.html.form.Form
import org.uqbar.wicket.xtend.XButton
import org.apache.wicket.markup.html.list.ListItem

class worldMap extends WebPage {
	extension WicketExtensionFactoryMethods = new WicketExtensionFactoryMethods
	
	GameSystem gameSystem = new GameSystem()
	Country pais = new Country()
	String cadena; 
//	var Pais dato

	


	new() {
		
		this.gameSystem = new GameSystem()
		this.gameSystem = new TestingData().createGameSystem();
		cadena = gameSystem.worldMap.get(0).name
		
		val Form<GameSystem> paisForm = new Form<GameSystem>("aCountryForm", new CompoundPropertyModel(gameSystem))
		this.agregarGrillaResultados(paisForm)
		this.addChild(paisForm)
		
		val Form<Country> formPais = new Form<Country>("aCountry", new CompoundPropertyModel(pais))
		this.agregarInfoPais(formPais, pais)
		this.addChild(formPais)
		formPais.outputMarkupId =  true
		
	
    	

		
		
		this.agregarBotonesInterPage()

    }
	

    
//    def agregarLabel(String content){
//    	val label = new Label("test", new CompoundPropertyModel(content))
//    	this.addChild(label)
//    	label.outputMarkupId = true
//    }
	
	def agregarBotonesInterPage() {
		this.addChild(new XLink<Object>("worldmap") => [
         onClick = [|  ]
        ])
        
        this.addChild(new XLink<Object>("files") => [
         onClick = [| setResponsePage(Files) ]
        ])
	}
	

	def agregarInfoPais(Form<Country> parent, Country pais) {			
//		Adding country name
		val labelName = new Label("countryName", new PropertyModel(this,"pais.name"))
    	parent.addChild(labelName)
    	labelName.outputMarkupId = true
    		
    	//	Adding list of connections
    	val listConnections = new XListView("connections", new PropertyModel(this,"pais.connections"))
		listConnections.populateItem = [ item |
			item.model = item.modelObject.asCompoundModel
			val labelSign = new Label("name")
			item.addChild(labelSign)
			labelSign.outputMarkupId = true
			item.addChild(new XLink<Object>("Eliminar") => [
		       	onClick = [| 
					
		       	]
		  	])
		]
		parent.addChild(listConnections)
		
		//	Adding list of connections
    	val listPlaces = new XListView("places", new PropertyModel(this,"pais.places"))
		listPlaces.populateItem = [ item |
			item.model = item.modelObject.asCompoundModel
			val labelSign = new Label("name")
			item.addChild(labelSign)
			labelSign.outputMarkupId = true
			item.addChild(new XLink<Object>("Eliminar") => [
		       	onClick = [| 
					
		       	]
		  	])
		]
		parent.addChild(listPlaces)
		
		//Adding list of characteristics
		val listCharacteristics = new XListView("characteristics", new PropertyModel(this,"pais.characteristics"))
		listCharacteristics.populateItem = [ item |
			new Label("characteristicName", item.defaultModel)
			val labelCharacteristic = new Label("characteristicName", item.defaultModel)
			item.addChild(labelCharacteristic)
			labelCharacteristic.outputMarkupId = true
			item.addChild(new XLink<Object>("Eliminar") => [
		       	onClick = [| 
					
		       	]
		  	])
		]
		parent.addChild(listCharacteristics)
    		
	}
    
    
    def agregarGrillaResultados(Form<GameSystem> parent) {
		val listView = new XListView("worldMap")
		listView.populateItem = [ item |
			item.model = item.modelObject.asCompoundModel
			item.addChild(new Label("name"))
			item.addChild(new XLink<Object>("editar") => [
        	onClick = [| 
				editarPais(item.modelObject)
        	]
        ])
			item.addChild(new XButton("eliminar")
				.onClick = [| ])
		]
		parent.addChild(listView)
	}
	
	def editarPais(Country pais) {
		this.cadena = pais.name
		this.pais = pais
	}
	

	
}