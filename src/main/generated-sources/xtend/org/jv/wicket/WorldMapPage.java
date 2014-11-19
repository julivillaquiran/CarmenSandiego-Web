package org.jv.wicket;

import appmodel.CreateCountryAppModel;
import country.Country;
import country.InterestPlace;
import java.util.List;
import model.GameSystem;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.jv.wicket.Files;
import org.uqbar.wicket.xtend.WicketExtensionFactoryMethods;
import org.uqbar.wicket.xtend.XLink;
import org.uqbar.wicket.xtend.XListView;
import testingData.TestingData;

@SuppressWarnings("all")
public class WorldMapPage extends WebPage {
  @Extension
  private WicketExtensionFactoryMethods _wicketExtensionFactoryMethods = new WicketExtensionFactoryMethods();
  
  private GameSystem gameSystem = new TestingData().createGameSystem();
  
  private CreateCountryAppModel createCountryAppModel;
  
  private Country pais = new Country();
  
  private Files filesPage;
  
  public WorldMapPage() {
    CreateCountryAppModel _createCountryAppModel = new CreateCountryAppModel(this.gameSystem, this.pais);
    this.createCountryAppModel = _createCountryAppModel;
    this.constructor();
  }
  
  public WorldMapPage(final GameSystem gameSystem) {
    CreateCountryAppModel _createCountryAppModel = new CreateCountryAppModel(gameSystem, this.pais);
    this.createCountryAppModel = _createCountryAppModel;
    this.constructor();
  }
  
  public MarkupContainer constructor() {
    MarkupContainer _xblockexpression = null;
    {
      GameSystem _gameSystem = this.createCountryAppModel.getGameSystem();
      CompoundPropertyModel<GameSystem> _compoundPropertyModel = new CompoundPropertyModel<GameSystem>(_gameSystem);
      final Form<GameSystem> paisForm = new Form<GameSystem>("aCountryForm", _compoundPropertyModel);
      this.agregarGrillaResultados(paisForm);
      this._wicketExtensionFactoryMethods.addChild(this, paisForm);
      Country _country = this.createCountryAppModel.getCountry();
      CompoundPropertyModel<Country> _compoundPropertyModel_1 = new CompoundPropertyModel<Country>(_country);
      final Form<Country> formPais = new Form<Country>("aCountry", _compoundPropertyModel_1);
      this.agregarInfoPais(formPais);
      this._wicketExtensionFactoryMethods.addChild(this, formPais);
      formPais.setOutputMarkupId(true);
      _xblockexpression = this.agregarBotonesInterPage();
    }
    return _xblockexpression;
  }
  
  public MarkupContainer agregarBotonesInterPage() {
    MarkupContainer _xblockexpression = null;
    {
      XLink<Object> _xLink = new XLink<Object>("worldmap");
      final Procedure1<XLink<Object>> _function = new Procedure1<XLink<Object>>() {
        public void apply(final XLink<Object> it) {
          final Procedure0 _function = new Procedure0() {
            public void apply() {
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
              GameSystem _gameSystem = WorldMapPage.this.createCountryAppModel.getGameSystem();
              Files _files = new Files(_gameSystem);
              it.setResponsePage(_files);
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
  
  public MarkupContainer agregarInfoPais(final Form<Country> parent) {
    MarkupContainer _xblockexpression = null;
    {
      PropertyModel<String> _propertyModel = new PropertyModel<String>(this, "createCountryAppModel.country.name");
      final TextField<String> nameTextField = new TextField<String>("countryName", _propertyModel);
      this._wicketExtensionFactoryMethods.addChild(parent, nameTextField);
      nameTextField.setOutputMarkupId(true);
      this.agregarConecciones(parent);
      this.agregarLugaresdeInteres(parent);
      this.agregarCaracteristicas(parent);
      _xblockexpression = this._wicketExtensionFactoryMethods.addChild(parent, new Button("newCountry") {
        public void onSubmit() {
          WorldMapPage.this.createCountryAppModel.addCountry();
        }
      });
    }
    return _xblockexpression;
  }
  
  public MarkupContainer agregarCaracteristicas(final Form<Country> parent) {
    MarkupContainer _xblockexpression = null;
    {
      PropertyModel _propertyModel = new PropertyModel(this, "createCountryAppModel.country.characteristics");
      final XListView<String> listCharacteristics = new XListView<String>("characteristics", _propertyModel);
      final Procedure1<ListItem<String>> _function = new Procedure1<ListItem<String>>() {
        public void apply(final ListItem<String> item) {
          IModel<?> _defaultModel = item.getDefaultModel();
          new Label("characteristicName", _defaultModel);
          IModel<?> _defaultModel_1 = item.getDefaultModel();
          final Label labelCharacteristic = new Label("characteristicName", _defaultModel_1);
          WorldMapPage.this._wicketExtensionFactoryMethods.addChild(item, labelCharacteristic);
          labelCharacteristic.setOutputMarkupId(true);
          XLink<Object> _xLink = new XLink<Object>("Eliminar");
          final Procedure1<XLink<Object>> _function = new Procedure1<XLink<Object>>() {
            public void apply(final XLink<Object> it) {
              final Procedure0 _function = new Procedure0() {
                public void apply() {
                  String _modelObject = item.getModelObject();
                  WorldMapPage.this.createCountryAppModel.setTakenCharacteristic(_modelObject);
                  WorldMapPage.this.createCountryAppModel.takeCharacteristic();
                }
              };
              it.setOnClick(_function);
            }
          };
          XLink<Object> _doubleArrow = ObjectExtensions.<XLink<Object>>operator_doubleArrow(_xLink, _function);
          WorldMapPage.this._wicketExtensionFactoryMethods.addChild(item, _doubleArrow);
        }
      };
      listCharacteristics.setPopulateItem(_function);
      this._wicketExtensionFactoryMethods.addChild(parent, listCharacteristics);
      listCharacteristics.setOutputMarkupId(true);
      PropertyModel<String> _propertyModel_1 = new PropertyModel<String>(this, "createCountryAppModel.newCharacteristic");
      final TextField<String> newSign = new TextField<String>("newSign", _propertyModel_1);
      this._wicketExtensionFactoryMethods.addChild(parent, newSign);
      newSign.setOutputMarkupId(true);
      _xblockexpression = this._wicketExtensionFactoryMethods.addChild(parent, new Button("addSign") {
        public void onSubmit() {
          WorldMapPage.this.createCountryAppModel.addCharacteristic();
          WorldMapPage.this.createCountryAppModel.setNewCharacteristic("");
        }
      });
    }
    return _xblockexpression;
  }
  
  public MarkupContainer agregarConecciones(final Form<Country> parent) {
    MarkupContainer _xblockexpression = null;
    {
      PropertyModel _propertyModel = new PropertyModel(this, "createCountryAppModel.country.connections");
      final XListView<Country> listConnections = new XListView<Country>("connections", _propertyModel);
      final Procedure1<ListItem<Country>> _function = new Procedure1<ListItem<Country>>() {
        public void apply(final ListItem<Country> item) {
          Country _modelObject = item.getModelObject();
          CompoundPropertyModel<Country> _asCompoundModel = WorldMapPage.this._wicketExtensionFactoryMethods.<Country>asCompoundModel(_modelObject);
          item.setModel(_asCompoundModel);
          final Label labelSign = new Label("name");
          WorldMapPage.this._wicketExtensionFactoryMethods.addChild(item, labelSign);
          labelSign.setOutputMarkupId(true);
          XLink<Object> _xLink = new XLink<Object>("Eliminar");
          final Procedure1<XLink<Object>> _function = new Procedure1<XLink<Object>>() {
            public void apply(final XLink<Object> it) {
              final Procedure0 _function = new Procedure0() {
                public void apply() {
                  Country _modelObject = item.getModelObject();
                  WorldMapPage.this.createCountryAppModel.setTakenConnection(_modelObject);
                  WorldMapPage.this.createCountryAppModel.takeConnection();
                }
              };
              it.setOnClick(_function);
            }
          };
          XLink<Object> _doubleArrow = ObjectExtensions.<XLink<Object>>operator_doubleArrow(_xLink, _function);
          WorldMapPage.this._wicketExtensionFactoryMethods.addChild(item, _doubleArrow);
        }
      };
      listConnections.setPopulateItem(_function);
      this._wicketExtensionFactoryMethods.addChild(parent, listConnections);
      GameSystem _gameSystem = this.createCountryAppModel.getGameSystem();
      List<Country> conections = _gameSystem.getWorldMap();
      PropertyModel<Country> _propertyModel_1 = new PropertyModel<Country>(this, "createCountryAppModel.newConnection");
      final DropDownChoice<Country> conectionsOptions = new DropDownChoice<Country>("connectionsOptions", _propertyModel_1, conections);
      this._wicketExtensionFactoryMethods.addChild(parent, conectionsOptions);
      conectionsOptions.setOutputMarkupId(true);
      _xblockexpression = this._wicketExtensionFactoryMethods.addChild(parent, new Button("addConnections") {
        public void onSubmit() {
          WorldMapPage.this.createCountryAppModel.addConnection();
          Country _country = new Country();
          WorldMapPage.this.createCountryAppModel.setNewConnection(_country);
        }
      });
    }
    return _xblockexpression;
  }
  
  public MarkupContainer agregarLugaresdeInteres(final Form<Country> parent) {
    MarkupContainer _xblockexpression = null;
    {
      PropertyModel _propertyModel = new PropertyModel(this, "createCountryAppModel.country.places");
      final XListView<InterestPlace> listPlaces = new XListView<InterestPlace>("places", _propertyModel);
      final Procedure1<ListItem<InterestPlace>> _function = new Procedure1<ListItem<InterestPlace>>() {
        public void apply(final ListItem<InterestPlace> item) {
          InterestPlace _modelObject = item.getModelObject();
          CompoundPropertyModel<InterestPlace> _asCompoundModel = WorldMapPage.this._wicketExtensionFactoryMethods.<InterestPlace>asCompoundModel(_modelObject);
          item.setModel(_asCompoundModel);
          final Label labelSign = new Label("name");
          WorldMapPage.this._wicketExtensionFactoryMethods.addChild(item, labelSign);
          labelSign.setOutputMarkupId(true);
          XLink<Object> _xLink = new XLink<Object>("Eliminar");
          final Procedure1<XLink<Object>> _function = new Procedure1<XLink<Object>>() {
            public void apply(final XLink<Object> it) {
              final Procedure0 _function = new Procedure0() {
                public void apply() {
                  InterestPlace _modelObject = item.getModelObject();
                  WorldMapPage.this.createCountryAppModel.setTakenPlace(_modelObject);
                  WorldMapPage.this.createCountryAppModel.takePlace();
                }
              };
              it.setOnClick(_function);
            }
          };
          XLink<Object> _doubleArrow = ObjectExtensions.<XLink<Object>>operator_doubleArrow(_xLink, _function);
          WorldMapPage.this._wicketExtensionFactoryMethods.addChild(item, _doubleArrow);
        }
      };
      listPlaces.setPopulateItem(_function);
      this._wicketExtensionFactoryMethods.addChild(parent, listPlaces);
      GameSystem _gameSystem = this.createCountryAppModel.getGameSystem();
      List<InterestPlace> places = _gameSystem.getInterestPlaces();
      PropertyModel<InterestPlace> _propertyModel_1 = new PropertyModel<InterestPlace>(this, "createCountryAppModel.newPlace");
      final DropDownChoice<InterestPlace> interesPlace = new DropDownChoice<InterestPlace>("interesPlace", _propertyModel_1, places);
      this._wicketExtensionFactoryMethods.addChild(parent, interesPlace);
      interesPlace.setOutputMarkupId(true);
      _xblockexpression = this._wicketExtensionFactoryMethods.addChild(parent, new Button("addPlaces") {
        public void onSubmit() {
          WorldMapPage.this.createCountryAppModel.addPlace();
          InterestPlace _interestPlace = new InterestPlace();
          WorldMapPage.this.createCountryAppModel.setNewPlace(_interestPlace);
        }
      });
    }
    return _xblockexpression;
  }
  
  public MarkupContainer agregarGrillaResultados(final Form<GameSystem> parent) {
    MarkupContainer _xblockexpression = null;
    {
      final XListView<Country> listView = new XListView<Country>("worldMap");
      final Procedure1<ListItem<Country>> _function = new Procedure1<ListItem<Country>>() {
        public void apply(final ListItem<Country> item) {
          Country _modelObject = item.getModelObject();
          CompoundPropertyModel<Country> _asCompoundModel = WorldMapPage.this._wicketExtensionFactoryMethods.<Country>asCompoundModel(_modelObject);
          item.setModel(_asCompoundModel);
          Label _label = new Label("name");
          WorldMapPage.this._wicketExtensionFactoryMethods.addChild(item, _label);
          XLink<Object> _xLink = new XLink<Object>("editar");
          final Procedure1<XLink<Object>> _function = new Procedure1<XLink<Object>>() {
            public void apply(final XLink<Object> it) {
              final Procedure0 _function = new Procedure0() {
                public void apply() {
                  Country _modelObject = item.getModelObject();
                  WorldMapPage.this.editarPais(_modelObject);
                }
              };
              it.setOnClick(_function);
            }
          };
          XLink<Object> _doubleArrow = ObjectExtensions.<XLink<Object>>operator_doubleArrow(_xLink, _function);
          WorldMapPage.this._wicketExtensionFactoryMethods.addChild(item, _doubleArrow);
          XLink<Object> _xLink_1 = new XLink<Object>("eliminar");
          final Procedure1<XLink<Object>> _function_1 = new Procedure1<XLink<Object>>() {
            public void apply(final XLink<Object> it) {
              final Procedure0 _function = new Procedure0() {
                public void apply() {
                  Country _modelObject = item.getModelObject();
                  WorldMapPage.this.eliminarPais(_modelObject);
                }
              };
              it.setOnClick(_function);
            }
          };
          XLink<Object> _doubleArrow_1 = ObjectExtensions.<XLink<Object>>operator_doubleArrow(_xLink_1, _function_1);
          WorldMapPage.this._wicketExtensionFactoryMethods.addChild(item, _doubleArrow_1);
        }
      };
      listView.setPopulateItem(_function);
      this._wicketExtensionFactoryMethods.addChild(parent, listView);
      listView.setOutputMarkupId(true);
      _xblockexpression = this._wicketExtensionFactoryMethods.addChild(parent, new Button("newCountry") {
        public void onSubmit() {
          WorldMapPage.this.nuevoPais();
        }
      });
    }
    return _xblockexpression;
  }
  
  public void nuevoPais() {
    Country _country = new Country();
    this.createCountryAppModel.setCountry(_country);
  }
  
  public void eliminarPais(final Country country) {
    this.createCountryAppModel.setCountry(country);
    GameSystem _gameSystem = this.createCountryAppModel.getGameSystem();
    _gameSystem.removeCountries(country);
    Country _country = new Country();
    this.createCountryAppModel.setCountry(_country);
  }
  
  public void editarPais(final Country pais) {
    this.createCountryAppModel.setCountry(pais);
  }
}
