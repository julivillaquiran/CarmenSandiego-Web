package org.jv.wicket

import appmodel.CreateVillainAppModel
import java.util.Arrays
import java.util.List
import model.GameSystem
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.Button
import org.apache.wicket.markup.html.form.DropDownChoice
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.RadioChoice
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.model.PropertyModel
import org.uqbar.wicket.xtend.WicketExtensionFactoryMethods
import org.uqbar.wicket.xtend.XLink
import org.uqbar.wicket.xtend.XListView
import testingData.TestingData
import villain.Gender
import villain.Hobbie
import villain.Villain


class Files extends WebPage {
	extension WicketExtensionFactoryMethods = new WicketExtensionFactoryMethods
	GameSystem gameSystem = new TestingData().createGameSystem();
	CreateVillainAppModel createVillainAppModel
	Villain villain = new Villain()
	WorldMapPage worldMapPage

//	new(WorldMapPage worldMapPage) {
//		this.createVillainAppModel = new CreateVillainAppModel(gameSystem, villain, true)
////		this.createVillainAppModel = files.createVillainAppModel
//		this.worldMapPage = worldMapPage
//		constructor()
//    }
    
    new(){
    	this.createVillainAppModel = new CreateVillainAppModel(gameSystem, villain, true)
    	constructor()
    }
    
    new(GameSystem gameSystem){
    	this.createVillainAppModel = new CreateVillainAppModel(gameSystem, villain, true)
    	constructor()
    }
    
    
    def constructor(){
    	val Form<GameSystem> aVillainForm = new Form<GameSystem>("aVillainForm", new CompoundPropertyModel(createVillainAppModel.gameSystem
		))
		this.agregarGrillaResultados(aVillainForm)
		this.addChild(aVillainForm)
		
		val Form<Villain> formVillano = new Form<Villain>("aVillain", new CompoundPropertyModel(createVillainAppModel.villain))
		this.agregarInfoVillano(formVillano)
		this.addChild(formVillano)
		formVillano.outputMarkupId =  true
		
		
		this.agregarBotonesInterPage()
    }
    
    def agregarSeñas(Form<Villain> parent){
    	//Adding list of signs
		val listSigns = new XListView("signs", new PropertyModel(this,"createVillainAppModel.villain.signs"))
		listSigns.populateItem = [ item |
			new Label("signName", item.defaultModel)
			val labelCharacteristic = new Label("signName", item.defaultModel)
			item.addChild(labelCharacteristic)
			labelCharacteristic.outputMarkupId = true
			item.addChild(new XLink<Object>("Eliminar") => [
		       	onClick = [| 
		       		this.createVillainAppModel.takenSign = item.modelObject
		       		this.createVillainAppModel.takeSign
					
		       	]
		  	])
		]
		parent.addChild(listSigns)
		listSigns.outputMarkupId = true
		
		val newSign = new TextField<String>("newSign", new PropertyModel(this,"createVillainAppModel.newSign"))
    	parent.addChild(newSign)
    	newSign.outputMarkupId = true
    	
    	parent.addChild(new Button("addSign") {
			override onSubmit() {
            		createVillainAppModel.addSign
            		createVillainAppModel.newSign = ""
			}
		})
    }
    
    def agregarHobbies(Form<Villain> parent){
    	//Adding list of hobbies
		val listHobbies = new XListView("hobbies", new PropertyModel(this,"createVillainAppModel.villain.hobbies"))
		listHobbies.populateItem = [ item |
			new Label("hobbieName", item.defaultModel)
			val labelHobbie = new Label("hobbieName", item.defaultModel)
			item.addChild(labelHobbie)
			labelHobbie.outputMarkupId = true
			item.addChild(new XLink<Object>("Eliminar") => [
		       	onClick = [| 
		       		this.createVillainAppModel.takenHobbie = item.modelObject
					this.createVillainAppModel.takeHobbie
		       	]
		  	])
		]
		parent.addChild(listHobbies)
		listHobbies.outputMarkupId = true
		
		var List<Hobbie> hobbies = Hobbie.values
		val DropDownChoice<Hobbie> hobbiesOption = new DropDownChoice<Hobbie>("newHobbie", 
			new PropertyModel(this, "createVillainAppModel.newHobbie"), hobbies
		)
		parent.addChild(hobbiesOption)
		hobbiesOption.outputMarkupId = true
    	
    	parent.addChild(new Button("addHobbie") {
			override onSubmit() {
            		createVillainAppModel.addHobbie
			}
		})
    }
    
    def agregarInfoVillano(Form<Villain> parent) {			
		val nameTextField = new TextField<String>("villainName", new PropertyModel(this,"createVillainAppModel.villain.name"))
    	parent.addChild(nameTextField)
    	nameTextField.outputMarkupId = true
    	
    	agregarSeñas(parent)
    	agregarHobbies(parent)	

		//Adding radio group -- Couldn't retrive current selection from the selected villain
		var List<Gender> TYPES  =  Arrays.asList(Gender.Masculino, Gender.Femenino)
		 
		val RadioChoice<Gender> gender = new RadioChoice("gender", new PropertyModel(this, "createVillainAppModel.villain.sex"), TYPES )
		parent.addChild(gender)
		gender.outputMarkupId = true  
		
		parent.addChild(new Button("newCountry") {
			override onSubmit() {
            		createVillainAppModel.addVillain
			}
		})  		
	}
    
    
    def agregarGrillaResultados(Form<GameSystem> parent) {
		val listView = new XListView("files")
		listView.populateItem = [ item |
			item.model = item.modelObject.asCompoundModel
			item.addChild(new Label("name"))
			item.addChild(new XLink<Object>("editar") => [
        	onClick = [| 
				editarVillano(item.modelObject)
        	]
        ])
			item.addChild(new XLink<Object>("eliminar") => [
			onClick = [|
				eliminarVillano(item.modelObject)
				]
			])
		]
		parent.addChild(listView)
		listView.outputMarkupId = true
		
		parent.addChild(new Button("newCountry") {
			override onSubmit() {
            		nuevoVillano()
			}
		})
	}
	
	def nuevoVillano() {
		createVillainAppModel.villain =  new Villain
	}
	
	def eliminarVillano(Villain villain) {
		this.createVillainAppModel.gameSystem.removeVillains(villain)
	}
	
	def editarVillano(Villain villano) {
		this.createVillainAppModel.villain = villano
	}
    
    def agregarBotonesInterPage() {
		this.addChild(new XLink<Object>("worldmap") => [
         onClick = [| setResponsePage(new WorldMapPage(this.createVillainAppModel.gameSystem)) ]
        ])
        
        this.addChild(new XLink<Object>("files") => [
         onClick = [|  ]
        ])
	}
}