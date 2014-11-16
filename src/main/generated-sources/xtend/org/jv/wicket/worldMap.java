package org.jv.wicket;

import country.Country;
import java.util.List;
import model.GameSystem;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
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
import org.uqbar.wicket.xtend.XButton;
import org.uqbar.wicket.xtend.XLink;
import org.uqbar.wicket.xtend.XListView;
import testingData.TestingData;

@SuppressWarnings("all")
public class worldMap extends WebPage {
  @Extension
  private WicketExtensionFactoryMethods _wicketExtensionFactoryMethods = new WicketExtensionFactoryMethods();
  
  private GameSystem gameSystem = new GameSystem();
  
  private Country pais = new Country();
  
  private String cadena;
  
  public worldMap() {
    GameSystem _gameSystem = new GameSystem();
    this.gameSystem = _gameSystem;
    TestingData _testingData = new TestingData();
    GameSystem _createGameSystem = _testingData.createGameSystem();
    this.gameSystem = _createGameSystem;
    List<Country> _worldMap = this.gameSystem.getWorldMap();
    Country _get = _worldMap.get(0);
    String _name = _get.getName();
    this.cadena = _name;
    CompoundPropertyModel<GameSystem> _compoundPropertyModel = new CompoundPropertyModel<GameSystem>(this.gameSystem);
    final Form<GameSystem> paisForm = new Form<GameSystem>("aCountryForm", _compoundPropertyModel);
    this.agregarGrillaResultados(paisForm);
    this._wicketExtensionFactoryMethods.addChild(this, paisForm);
    CompoundPropertyModel<Country> _compoundPropertyModel_1 = new CompoundPropertyModel<Country>(this.pais);
    final Form<Country> formPais = new Form<Country>("aCountry", _compoundPropertyModel_1);
    this.agregarInfoPais(formPais, this.pais);
    this._wicketExtensionFactoryMethods.addChild(this, formPais);
    formPais.setOutputMarkupId(true);
    this.agregarBotonesInterPage();
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
              it.<Files>setResponsePage(Files.class);
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
  
  public MarkupContainer agregarInfoPais(final Form<Country> parent, final Country pais) {
    MarkupContainer _xblockexpression = null;
    {
      PropertyModel<Object> _propertyModel = new PropertyModel<Object>(this, "pais.name");
      final Label labelName = new Label("countryName", _propertyModel);
      this._wicketExtensionFactoryMethods.addChild(parent, labelName);
      labelName.setOutputMarkupId(true);
      PropertyModel _propertyModel_1 = new PropertyModel(this, "pais.connections");
      final XListView<Object> listConnections = new XListView<Object>("connections", _propertyModel_1);
      final Procedure1<ListItem<Object>> _function = new Procedure1<ListItem<Object>>() {
        public void apply(final ListItem<Object> item) {
          Object _modelObject = item.getModelObject();
          CompoundPropertyModel<Object> _asCompoundModel = worldMap.this._wicketExtensionFactoryMethods.<Object>asCompoundModel(_modelObject);
          item.setModel(_asCompoundModel);
          final Label labelSign = new Label("name");
          worldMap.this._wicketExtensionFactoryMethods.addChild(item, labelSign);
          labelSign.setOutputMarkupId(true);
          XLink<Object> _xLink = new XLink<Object>("Eliminar");
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
          worldMap.this._wicketExtensionFactoryMethods.addChild(item, _doubleArrow);
        }
      };
      listConnections.setPopulateItem(_function);
      this._wicketExtensionFactoryMethods.addChild(parent, listConnections);
      PropertyModel _propertyModel_2 = new PropertyModel(this, "pais.places");
      final XListView<Object> listPlaces = new XListView<Object>("places", _propertyModel_2);
      final Procedure1<ListItem<Object>> _function_1 = new Procedure1<ListItem<Object>>() {
        public void apply(final ListItem<Object> item) {
          Object _modelObject = item.getModelObject();
          CompoundPropertyModel<Object> _asCompoundModel = worldMap.this._wicketExtensionFactoryMethods.<Object>asCompoundModel(_modelObject);
          item.setModel(_asCompoundModel);
          final Label labelSign = new Label("name");
          worldMap.this._wicketExtensionFactoryMethods.addChild(item, labelSign);
          labelSign.setOutputMarkupId(true);
          XLink<Object> _xLink = new XLink<Object>("Eliminar");
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
          worldMap.this._wicketExtensionFactoryMethods.addChild(item, _doubleArrow);
        }
      };
      listPlaces.setPopulateItem(_function_1);
      this._wicketExtensionFactoryMethods.addChild(parent, listPlaces);
      PropertyModel _propertyModel_3 = new PropertyModel(this, "pais.characteristics");
      final XListView<Object> listCharacteristics = new XListView<Object>("characteristics", _propertyModel_3);
      final Procedure1<ListItem<Object>> _function_2 = new Procedure1<ListItem<Object>>() {
        public void apply(final ListItem<Object> item) {
          IModel<?> _defaultModel = item.getDefaultModel();
          new Label("characteristicName", _defaultModel);
          IModel<?> _defaultModel_1 = item.getDefaultModel();
          final Label labelCharacteristic = new Label("characteristicName", _defaultModel_1);
          worldMap.this._wicketExtensionFactoryMethods.addChild(item, labelCharacteristic);
          labelCharacteristic.setOutputMarkupId(true);
          XLink<Object> _xLink = new XLink<Object>("Eliminar");
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
          worldMap.this._wicketExtensionFactoryMethods.addChild(item, _doubleArrow);
        }
      };
      listCharacteristics.setPopulateItem(_function_2);
      _xblockexpression = this._wicketExtensionFactoryMethods.addChild(parent, listCharacteristics);
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
          CompoundPropertyModel<Country> _asCompoundModel = worldMap.this._wicketExtensionFactoryMethods.<Country>asCompoundModel(_modelObject);
          item.setModel(_asCompoundModel);
          Label _label = new Label("name");
          worldMap.this._wicketExtensionFactoryMethods.addChild(item, _label);
          XLink<Object> _xLink = new XLink<Object>("editar");
          final Procedure1<XLink<Object>> _function = new Procedure1<XLink<Object>>() {
            public void apply(final XLink<Object> it) {
              final Procedure0 _function = new Procedure0() {
                public void apply() {
                  Country _modelObject = item.getModelObject();
                  worldMap.this.editarPais(_modelObject);
                }
              };
              it.setOnClick(_function);
            }
          };
          XLink<Object> _doubleArrow = ObjectExtensions.<XLink<Object>>operator_doubleArrow(_xLink, _function);
          worldMap.this._wicketExtensionFactoryMethods.addChild(item, _doubleArrow);
          XButton _xButton = new XButton("eliminar");
          final Procedure0 _function_1 = new Procedure0() {
            public void apply() {
            }
          };
          XButton _setOnClick = _xButton.setOnClick(_function_1);
          worldMap.this._wicketExtensionFactoryMethods.addChild(item, _setOnClick);
        }
      };
      listView.setPopulateItem(_function);
      _xblockexpression = this._wicketExtensionFactoryMethods.addChild(parent, listView);
    }
    return _xblockexpression;
  }
  
  public Country editarPais(final Country pais) {
    Country _xblockexpression = null;
    {
      String _name = pais.getName();
      this.cadena = _name;
      _xblockexpression = this.pais = pais;
    }
    return _xblockexpression;
  }
}
