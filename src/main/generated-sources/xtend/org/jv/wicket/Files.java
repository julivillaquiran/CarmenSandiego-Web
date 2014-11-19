package org.jv.wicket;

import appmodel.CreateVillainAppModel;
import java.util.Arrays;
import java.util.List;
import model.GameSystem;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.jv.wicket.WorldMapPage;
import org.uqbar.wicket.xtend.WicketExtensionFactoryMethods;
import org.uqbar.wicket.xtend.XLink;
import org.uqbar.wicket.xtend.XListView;
import testingData.TestingData;
import villain.Gender;
import villain.Hobbie;
import villain.Villain;

@SuppressWarnings("all")
public class Files extends WebPage {
  @Extension
  private WicketExtensionFactoryMethods _wicketExtensionFactoryMethods = new WicketExtensionFactoryMethods();
  
  private GameSystem gameSystem = new TestingData().createGameSystem();
  
  private CreateVillainAppModel createVillainAppModel;
  
  private Villain villain = new Villain();
  
  private WorldMapPage worldMapPage;
  
  public Files() {
    CreateVillainAppModel _createVillainAppModel = new CreateVillainAppModel(this.gameSystem, this.villain, true);
    this.createVillainAppModel = _createVillainAppModel;
    this.constructor();
  }
  
  public Files(final GameSystem gameSystem) {
    CreateVillainAppModel _createVillainAppModel = new CreateVillainAppModel(gameSystem, this.villain, true);
    this.createVillainAppModel = _createVillainAppModel;
    this.constructor();
  }
  
  public MarkupContainer constructor() {
    MarkupContainer _xblockexpression = null;
    {
      GameSystem _gameSystem = this.createVillainAppModel.getGameSystem();
      CompoundPropertyModel<GameSystem> _compoundPropertyModel = new CompoundPropertyModel<GameSystem>(_gameSystem);
      final Form<GameSystem> aVillainForm = new Form<GameSystem>("aVillainForm", _compoundPropertyModel);
      this.agregarGrillaResultados(aVillainForm);
      this._wicketExtensionFactoryMethods.addChild(this, aVillainForm);
      Villain _villain = this.createVillainAppModel.getVillain();
      CompoundPropertyModel<Villain> _compoundPropertyModel_1 = new CompoundPropertyModel<Villain>(_villain);
      final Form<Villain> formVillano = new Form<Villain>("aVillain", _compoundPropertyModel_1);
      this.agregarInfoVillano(formVillano);
      this._wicketExtensionFactoryMethods.addChild(this, formVillano);
      formVillano.setOutputMarkupId(true);
      _xblockexpression = this.agregarBotonesInterPage();
    }
    return _xblockexpression;
  }
  
  public MarkupContainer agregarSeñas(final Form<Villain> parent) {
    MarkupContainer _xblockexpression = null;
    {
      PropertyModel _propertyModel = new PropertyModel(this, "createVillainAppModel.villain.signs");
      final XListView<String> listSigns = new XListView<String>("signs", _propertyModel);
      final Procedure1<ListItem<String>> _function = new Procedure1<ListItem<String>>() {
        public void apply(final ListItem<String> item) {
          IModel<?> _defaultModel = item.getDefaultModel();
          new Label("signName", _defaultModel);
          IModel<?> _defaultModel_1 = item.getDefaultModel();
          final Label labelCharacteristic = new Label("signName", _defaultModel_1);
          Files.this._wicketExtensionFactoryMethods.addChild(item, labelCharacteristic);
          labelCharacteristic.setOutputMarkupId(true);
          XLink<Object> _xLink = new XLink<Object>("Eliminar");
          final Procedure1<XLink<Object>> _function = new Procedure1<XLink<Object>>() {
            public void apply(final XLink<Object> it) {
              final Procedure0 _function = new Procedure0() {
                public void apply() {
                  String _modelObject = item.getModelObject();
                  Files.this.createVillainAppModel.setTakenSign(_modelObject);
                  Files.this.createVillainAppModel.takeSign();
                }
              };
              it.setOnClick(_function);
            }
          };
          XLink<Object> _doubleArrow = ObjectExtensions.<XLink<Object>>operator_doubleArrow(_xLink, _function);
          Files.this._wicketExtensionFactoryMethods.addChild(item, _doubleArrow);
        }
      };
      listSigns.setPopulateItem(_function);
      this._wicketExtensionFactoryMethods.addChild(parent, listSigns);
      listSigns.setOutputMarkupId(true);
      PropertyModel<String> _propertyModel_1 = new PropertyModel<String>(this, "createVillainAppModel.newSign");
      final TextField<String> newSign = new TextField<String>("newSign", _propertyModel_1);
      this._wicketExtensionFactoryMethods.addChild(parent, newSign);
      newSign.setOutputMarkupId(true);
      _xblockexpression = this._wicketExtensionFactoryMethods.addChild(parent, new Button("addSign") {
        public void onSubmit() {
          Files.this.createVillainAppModel.addSign();
          Files.this.createVillainAppModel.setNewSign("");
        }
      });
    }
    return _xblockexpression;
  }
  
  public MarkupContainer agregarHobbies(final Form<Villain> parent) {
    MarkupContainer _xblockexpression = null;
    {
      PropertyModel _propertyModel = new PropertyModel(this, "createVillainAppModel.villain.hobbies");
      final XListView<Hobbie> listHobbies = new XListView<Hobbie>("hobbies", _propertyModel);
      final Procedure1<ListItem<Hobbie>> _function = new Procedure1<ListItem<Hobbie>>() {
        public void apply(final ListItem<Hobbie> item) {
          IModel<?> _defaultModel = item.getDefaultModel();
          new Label("hobbieName", _defaultModel);
          IModel<?> _defaultModel_1 = item.getDefaultModel();
          final Label labelHobbie = new Label("hobbieName", _defaultModel_1);
          Files.this._wicketExtensionFactoryMethods.addChild(item, labelHobbie);
          labelHobbie.setOutputMarkupId(true);
          XLink<Object> _xLink = new XLink<Object>("Eliminar");
          final Procedure1<XLink<Object>> _function = new Procedure1<XLink<Object>>() {
            public void apply(final XLink<Object> it) {
              final Procedure0 _function = new Procedure0() {
                public void apply() {
                  Hobbie _modelObject = item.getModelObject();
                  Files.this.createVillainAppModel.setTakenHobbie(_modelObject);
                  Files.this.createVillainAppModel.takeHobbie();
                }
              };
              it.setOnClick(_function);
            }
          };
          XLink<Object> _doubleArrow = ObjectExtensions.<XLink<Object>>operator_doubleArrow(_xLink, _function);
          Files.this._wicketExtensionFactoryMethods.addChild(item, _doubleArrow);
        }
      };
      listHobbies.setPopulateItem(_function);
      this._wicketExtensionFactoryMethods.addChild(parent, listHobbies);
      listHobbies.setOutputMarkupId(true);
      List<Hobbie> hobbies = (List<Hobbie>)Conversions.doWrapArray(Hobbie.values());
      PropertyModel<Hobbie> _propertyModel_1 = new PropertyModel<Hobbie>(this, "createVillainAppModel.newHobbie");
      final DropDownChoice<Hobbie> hobbiesOption = new DropDownChoice<Hobbie>("newHobbie", _propertyModel_1, hobbies);
      this._wicketExtensionFactoryMethods.addChild(parent, hobbiesOption);
      hobbiesOption.setOutputMarkupId(true);
      _xblockexpression = this._wicketExtensionFactoryMethods.addChild(parent, new Button("addHobbie") {
        public void onSubmit() {
          Files.this.createVillainAppModel.addHobbie();
        }
      });
    }
    return _xblockexpression;
  }
  
  public MarkupContainer agregarInfoVillano(final Form<Villain> parent) {
    MarkupContainer _xblockexpression = null;
    {
      PropertyModel<String> _propertyModel = new PropertyModel<String>(this, "createVillainAppModel.villain.name");
      final TextField<String> nameTextField = new TextField<String>("villainName", _propertyModel);
      this._wicketExtensionFactoryMethods.addChild(parent, nameTextField);
      nameTextField.setOutputMarkupId(true);
      this.agregarSeñas(parent);
      this.agregarHobbies(parent);
      List<Gender> TYPES = Arrays.<Gender>asList(Gender.Masculino, Gender.Femenino);
      PropertyModel<Gender> _propertyModel_1 = new PropertyModel<Gender>(this, "createVillainAppModel.villain.sex");
      final RadioChoice<Gender> gender = new RadioChoice<Gender>("gender", _propertyModel_1, TYPES);
      this._wicketExtensionFactoryMethods.addChild(parent, gender);
      gender.setOutputMarkupId(true);
      _xblockexpression = this._wicketExtensionFactoryMethods.addChild(parent, new Button("newCountry") {
        public void onSubmit() {
          Files.this.createVillainAppModel.addVillain();
        }
      });
    }
    return _xblockexpression;
  }
  
  public MarkupContainer agregarGrillaResultados(final Form<GameSystem> parent) {
    MarkupContainer _xblockexpression = null;
    {
      final XListView<Villain> listView = new XListView<Villain>("files");
      final Procedure1<ListItem<Villain>> _function = new Procedure1<ListItem<Villain>>() {
        public void apply(final ListItem<Villain> item) {
          Villain _modelObject = item.getModelObject();
          CompoundPropertyModel<Villain> _asCompoundModel = Files.this._wicketExtensionFactoryMethods.<Villain>asCompoundModel(_modelObject);
          item.setModel(_asCompoundModel);
          Label _label = new Label("name");
          Files.this._wicketExtensionFactoryMethods.addChild(item, _label);
          XLink<Object> _xLink = new XLink<Object>("editar");
          final Procedure1<XLink<Object>> _function = new Procedure1<XLink<Object>>() {
            public void apply(final XLink<Object> it) {
              final Procedure0 _function = new Procedure0() {
                public void apply() {
                  Villain _modelObject = item.getModelObject();
                  Files.this.editarVillano(_modelObject);
                }
              };
              it.setOnClick(_function);
            }
          };
          XLink<Object> _doubleArrow = ObjectExtensions.<XLink<Object>>operator_doubleArrow(_xLink, _function);
          Files.this._wicketExtensionFactoryMethods.addChild(item, _doubleArrow);
          XLink<Object> _xLink_1 = new XLink<Object>("eliminar");
          final Procedure1<XLink<Object>> _function_1 = new Procedure1<XLink<Object>>() {
            public void apply(final XLink<Object> it) {
              final Procedure0 _function = new Procedure0() {
                public void apply() {
                  Villain _modelObject = item.getModelObject();
                  Files.this.eliminarVillano(_modelObject);
                }
              };
              it.setOnClick(_function);
            }
          };
          XLink<Object> _doubleArrow_1 = ObjectExtensions.<XLink<Object>>operator_doubleArrow(_xLink_1, _function_1);
          Files.this._wicketExtensionFactoryMethods.addChild(item, _doubleArrow_1);
        }
      };
      listView.setPopulateItem(_function);
      this._wicketExtensionFactoryMethods.addChild(parent, listView);
      listView.setOutputMarkupId(true);
      _xblockexpression = this._wicketExtensionFactoryMethods.addChild(parent, new Button("newCountry") {
        public void onSubmit() {
          Files.this.nuevoVillano();
        }
      });
    }
    return _xblockexpression;
  }
  
  public void nuevoVillano() {
    Villain _villain = new Villain();
    this.createVillainAppModel.setVillain(_villain);
  }
  
  public boolean eliminarVillano(final Villain villain) {
    GameSystem _gameSystem = this.createVillainAppModel.getGameSystem();
    return _gameSystem.removeVillains(villain);
  }
  
  public void editarVillano(final Villain villano) {
    this.createVillainAppModel.setVillain(villano);
  }
  
  public MarkupContainer agregarBotonesInterPage() {
    MarkupContainer _xblockexpression = null;
    {
      XLink<Object> _xLink = new XLink<Object>("worldmap");
      final Procedure1<XLink<Object>> _function = new Procedure1<XLink<Object>>() {
        public void apply(final XLink<Object> it) {
          final Procedure0 _function = new Procedure0() {
            public void apply() {
              GameSystem _gameSystem = Files.this.createVillainAppModel.getGameSystem();
              WorldMapPage _worldMapPage = new WorldMapPage(_gameSystem);
              it.setResponsePage(_worldMapPage);
            }
          };
          it.setOnClick(_function);
        }
      };
      XLink<Object> _doubleArrow = ObjectExtensions.<XLink<Object>>operator_doubleArrow(_xLink, _function);
      this._wicketExtensionFactoryMethods.addChild(this, _doubleArrow);
      XLink<Object> _xLink_1 = new XLink<Object>("files");
      final Procedure1<XLink<Object>> _function_1 = new Procedure1<XLink<Object>>() {
        public void apply(final XLink<Object> it) {
          final Procedure0 _function = new Procedure0() {
            public void apply() {
            }
          };
          it.setOnClick(_function);
        }
      };
      XLink<Object> _doubleArrow_1 = ObjectExtensions.<XLink<Object>>operator_doubleArrow(_xLink_1, _function_1);
      _xblockexpression = this._wicketExtensionFactoryMethods.addChild(this, _doubleArrow_1);
    }
    return _xblockexpression;
  }
}
