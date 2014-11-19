package org.jv.wicket

import org.uqbar.wicket.xtend.WicketExtensionFactoryMethods
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import org.uqbar.wicket.xtend.XLink

import model.GameSystem
import testingData.TestingData
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.model.PropertyModel
import org.uqbar.wicket.xtend.XListView
import country.Country
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.Button
import org.apache.wicket.markup.html.form.TextField
import appmodel.CreateCountryAppModel
import country.InterestPlace
import org.apache.wicket.markup.html.form.DropDownChoice
import java.util.List

class WorldMapPage extends WebPage {
	extension WicketExtensionFactoryMethods = new WicketExtensionFactoryMethods
	
	GameSystem gameSystem = new TestingData().createGameSystem()
	CreateCountryAppModel createCountryAppModel
	Country pais = new Country()
	Files filesPage

	new(){
		this.createCountryAppModel = new CreateCountryAppModel(gameSystem, pais)
		constructor()
	}
	
	new(GameSystem gameSystem) {

		this.createCountryAppModel = new CreateCountryAppModel(gameSystem, pais)
		constructor()
    }
    
//    new(Files filesPage, WorldMapPage worldMapPage){
//    	this.filesPage = filesPage
//    	this.createCountryAppModel = worldMapPage.createCountryAppModel
//    	constructor()
//    }
    
    
    def constructor(){
    	val Form<GameSystem> paisForm = new Form<GameSystem>("aCountryForm", 
			new CompoundPropertyModel(createCountryAppModel.gameSystem)
		)
		this.agregarGrillaResultados(paisForm)
		this.addChild(paisForm)
		
		val Form<Country> formPais = new Form<Country>("aCountry", new CompoundPropertyModel(createCountryAppModel.country))
		this.agregarInfoPais(formPais)
		this.addChild(formPais)
		formPais.outputMarkupId =  true		
		
		this.agregarBotonesInterPage()
    }
	
	def agregarBotonesInterPage() {
		this.addChild(new XLink<Object>("worldmap") => [
         onClick = [|  ]
        ])
        
        this.addChild(new XLink<Object>("files") => [ 
         onClick = [| setResponsePage(new Files(this.createCountryAppModel.gameSystem)) ]
        ])
	}
	
	
	
	def agregarInfoPais(Form<Country> parent) {			
//		Adding country name
		val nameTextField = new TextField<String>("countryName", new PropertyModel(this,"createCountryAppModel.country.name"))
    	parent.addChild(nameTextField)
    	nameTextField.outputMarkupId = true
    	
    	agregarConecciones(parent)
    	agregarLugaresdeInteres(parent)
    	agregarCaracteristicas(parent)
    	
    	parent.addChild(new Button("newCountry") {
			override onSubmit() {
            		createCountryAppModel.addCountry
			}
		})
	}
	
	
	
	def agregarCaracteristicas(Form<Country> parent){
		//Adding list of characteristics
		val listCharacteristics = new XListView("characteristics", 
			new PropertyModel(this,"createCountryAppModel.country.characteristics")
		)
		listCharacteristics.populateItem = [ item |
			new Label("characteristicName", item.defaultModel)
			val labelCharacteristic = new Label("characteristicName", item.defaultModel)
			item.addChild(labelCharacteristic)
			labelCharacteristic.outputMarkupId = true
			item.addChild(new XLink<Object>("Eliminar") => [
		       	onClick = [| 
		       		this.createCountryAppModel.takenCharacteristic = item.modelObject
		       		this.createCountryAppModel.takeCharacteristic
		       	]
		  	])
		]
		parent.addChild(listCharacteristics)
		listCharacteristics.outputMarkupId = true
		
		val newSign = new TextField<String>("newSign", new PropertyModel(this,"createCountryAppModel.newCharacteristic"))
    	parent.addChild(newSign)
    	newSign.outputMarkupId = true
    	
    	parent.addChild(new Button("addSign") {
			override onSubmit() {
            		createCountryAppModel.addCharacteristic
            		createCountryAppModel.newCharacteristic = ""
			}
		})
	}
	
	def agregarConecciones(Form<Country> parent) {
		//	Adding list of connections
    	val listConnections = new XListView("connections", 
    		new PropertyModel(this,"createCountryAppModel.country.connections")
    	)
		listConnections.populateItem = [ item |
			item.model = item.modelObject.asCompoundModel
			val labelSign = new Label("name")
			item.addChild(labelSign)
			labelSign.outputMarkupId = true
			item.addChild(new XLink<Object>("Eliminar") => [
		       	onClick = [| 
		       		this.createCountryAppModel.takenConnection = item.modelObject
		       		this.createCountryAppModel.takeConnection
					
		       	]
		  	])
		]
		parent.addChild(listConnections)
		
		var List<Country> conections =  createCountryAppModel.gameSystem.worldMap
		val DropDownChoice<Country> conectionsOptions = new DropDownChoice<Country>("connectionsOptions", 
			new PropertyModel(this, "createCountryAppModel.newConnection"), conections
		)
		parent.addChild(conectionsOptions)
		conectionsOptions.outputMarkupId = true
		
		parent.addChild(new Button("addConnections") {
			override onSubmit() {
            		createCountryAppModel.addConnection
            		createCountryAppModel.newConnection = new Country
			}
		})
	}

	def agregarLugaresdeInteres(Form<Country> parent){
		//	Adding list of connections
    	val listPlaces = new XListView("places", new PropertyModel(this,"createCountryAppModel.country.places"))
		listPlaces.populateItem = [ item |
			item.model = item.modelObject.asCompoundModel
			val labelSign = new Label("name")
			item.addChild(labelSign)
			labelSign.outputMarkupId = true
			item.addChild(new XLink<Object>("Eliminar") => [
		       	onClick = [| 
		       		this.createCountryAppModel.takenPlace = item.modelObject
		       		this.createCountryAppModel.takePlace
					
		       	]
		  	])
		]
		parent.addChild(listPlaces)
		
		var List<InterestPlace> places =  createCountryAppModel.gameSystem.interestPlaces
		val DropDownChoice<InterestPlace> interesPlace = new DropDownChoice<InterestPlace>("interesPlace", 
			new PropertyModel(this, "createCountryAppModel.newPlace"), places
		)
		parent.addChild(interesPlace)
		interesPlace.outputMarkupId = true
		
		parent.addChild(new Button("addPlaces") {
			override onSubmit() {
            		createCountryAppModel.addPlace
            		createCountryAppModel.newPlace = new InterestPlace
			}
		})
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
			
			item.addChild(new XLink<Object>("eliminar") => [
			onClick = [|
				eliminarPais(item.modelObject)
				]
			])
		]
		parent.addChild(listView)
		listView.outputMarkupId = true
		
		parent.addChild(new Button("newCountry") {
			override onSubmit() {
            		nuevoPais()
			}
		})

	}
	
	def nuevoPais() {
		createCountryAppModel.country = new Country
	}
	
	def eliminarPais(Country country) {
		this.createCountryAppModel.country = country
		this.createCountryAppModel.gameSystem.removeCountries(country)
		this.createCountryAppModel.country = new Country
	}
	
	
	def editarPais(Country pais) {
		this.createCountryAppModel.country = pais
	}
	

	
}