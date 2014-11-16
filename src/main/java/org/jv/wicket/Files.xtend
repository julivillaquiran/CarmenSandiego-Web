package org.jv.wicket

import org.uqbar.wicket.xtend.WicketExtensionFactoryMethods
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import org.uqbar.wicket.xtend.XLink
import model.GameSystem
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.CompoundPropertyModel
import org.uqbar.wicket.xtend.XListView
import org.uqbar.wicket.xtend.XButton
import villain.Villain
import org.apache.wicket.model.PropertyModel
import testingData.TestingData
import java.util.List
import java.util.Arrays
import org.apache.wicket.markup.html.form.RadioChoice
import villain.Gender

class Files extends WebPage {
	extension WicketExtensionFactoryMethods = new WicketExtensionFactoryMethods
	GameSystem gameSystem = new GameSystem()
	Villain villain = new Villain()

	new() {
		this.gameSystem = new GameSystem()
		this.gameSystem = new TestingData().createGameSystem();
		
		
		val Form<GameSystem> aVillainForm = new Form<GameSystem>("aVillainForm", new CompoundPropertyModel(gameSystem))
		this.agregarGrillaResultados(aVillainForm)
		this.addChild(aVillainForm)
		
		val Form<Villain> formVillano = new Form<Villain>("aVillain", new CompoundPropertyModel(villain))
		this.agregarInfoVillano(formVillano)
		this.addChild(formVillano)
		formVillano.outputMarkupId =  true
		
		
		this.agregarBotonesInterPage()
        
    }
    
    def agregarInfoVillano(Form<Villain> parent) {			
		val labelName = new Label("villainName", new PropertyModel(this,"villain.name"))
    	parent.addChild(labelName)
    	labelName.outputMarkupId = true
    		
		//Adding list of signs
		val listSigns = new XListView("signs", new PropertyModel(this,"villain.signs"))
		listSigns.populateItem = [ item |
			new Label("signName", item.defaultModel)
			val labelCharacteristic = new Label("signName", item.defaultModel)
			item.addChild(labelCharacteristic)
			labelCharacteristic.outputMarkupId = true
			item.addChild(new XLink<Object>("Eliminar") => [
		       	onClick = [| 
					
		       	]
		  	])
		]
		parent.addChild(listSigns)
		
		//Adding list of hobbies
		val listHobbies = new XListView("hobbies", new PropertyModel(this,"villain.hobbies"))
		listHobbies.populateItem = [ item |
			new Label("hobbieName", item.defaultModel)
			val labelCharacteristic = new Label("hobbieName", item.defaultModel)
			item.addChild(labelCharacteristic)
			labelCharacteristic.outputMarkupId = true
			item.addChild(new XLink<Object>("Eliminar") => [
		       	onClick = [| 
					
		       	]
		  	])
		]
		parent.addChild(listHobbies)
		
		//Adding radio group -- Couldn't retrive current selection from the selected villain
		
		var List<Gender> TYPES  =  Arrays.asList(Gender.Masculino, Gender.Femenino)
		 
		val RadioChoice<Gender> gender = new RadioChoice("gender", new PropertyModel(this, "villain.sex"), TYPES )
		parent.addChild(gender)
		gender.outputMarkupId = true    		
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
			item.addChild(new XButton("eliminar")
				.onClick = [| ])
		]
		parent.addChild(listView)
	}
	
	def editarVillano(Villain villano) {
		this.villain = villano
	}
    
    def agregarBotonesInterPage() {
		this.addChild(new XLink<Object>("worldmap") => [
         onClick = [| setResponsePage(worldMap) ]
        ])
        
        this.addChild(new XLink<Object>("files") => [
         onClick = [|  ]
        ])
	}
}