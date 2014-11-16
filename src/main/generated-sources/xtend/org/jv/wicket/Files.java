package org.jv.wicket;

import java.util.Arrays;
import java.util.List;
import model.GameSystem;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.jv.wicket.worldMap;
import org.uqbar.wicket.xtend.WicketExtensionFactoryMethods;
import org.uqbar.wicket.xtend.XButton;
import org.uqbar.wicket.xtend.XLink;
import org.uqbar.wicket.xtend.XListView;
import testingData.TestingData;
import villain.Gender;
import villain.Villain;

@SuppressWarnings("all")
public class Files extends WebPage {
  @Extension
  private WicketExtensionFactoryMethods _wicketExtensionFactoryMethods = new WicketExtensionFactoryMethods();
  
  private GameSystem gameSystem = new GameSystem();
  
  private Villain villain = new Villain();
  
  public Files() {
    GameSystem _gameSystem = new GameSystem();
    this.gameSystem = _gameSystem;
    TestingData _testingData = new TestingData();
    GameSystem _createGameSystem = _testingData.createGameSystem();
    this.gameSystem = _createGameSystem;
    CompoundPropertyModel<GameSystem> _compoundPropertyModel = new CompoundPropertyModel<GameSystem>(this.gameSystem);
    final Form<GameSystem> aVillainForm = new Form<GameSystem>("aVillainForm", _compoundPropertyModel);
    this.agregarGrillaResultados(aVillainForm);
    this._wicketExtensionFactoryMethods.addChild(this, aVillainForm);
    CompoundPropertyModel<Villain> _compoundPropertyModel_1 = new CompoundPropertyModel<Villain>(this.villain);
    final Form<Villain> formVillano = new Form<Villain>("aVillain", _compoundPropertyModel_1);
    this.agregarInfoVillano(formVillano);
    this._wicketExtensionFactoryMethods.addChild(this, formVillano);
    formVillano.setOutputMarkupId(true);
    this.agregarBotonesInterPage();
  }
  
  public Component agregarInfoVillano(final Form<Villain> parent) {
    Component _xblockexpression = null;
    {
      PropertyModel<Object> _propertyModel = new PropertyModel<Object>(this, "villain.name");
      final Label labelName = new Label("villainName", _propertyModel);
      this._wicketExtensionFactoryMethods.addChild(parent, labelName);
      labelName.setOutputMarkupId(true);
      PropertyModel _propertyModel_1 = new PropertyModel(this, "villain.signs");
      final XListView<Object> listSigns = new XListView<Object>("signs", _propertyModel_1);
      final Procedure1<ListItem<Object>> _function = new Procedure1<ListItem<Object>>() {
        public void apply(final ListItem<Object> item) {
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
      PropertyModel _propertyModel_2 = new PropertyModel(this, "villain.hobbies");
      final XListView<Object> listHobbies = new XListView<Object>("hobbies", _propertyModel_2);
      final Procedure1<ListItem<Object>> _function_1 = new Procedure1<ListItem<Object>>() {
        public void apply(final ListItem<Object> item) {
          IModel<?> _defaultModel = item.getDefaultModel();
          new Label("hobbieName", _defaultModel);
          IModel<?> _defaultModel_1 = item.getDefaultModel();
          final Label labelCharacteristic = new Label("hobbieName", _defaultModel_1);
          Files.this._wicketExtensionFactoryMethods.addChild(item, labelCharacteristic);
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
          Files.this._wicketExtensionFactoryMethods.addChild(item, _doubleArrow);
        }
      };
      listHobbies.setPopulateItem(_function_1);
      this._wicketExtensionFactoryMethods.addChild(parent, listHobbies);
      List<Gender> TYPES = Arrays.<Gender>asList(Gender.Masculino, Gender.Femenino);
      PropertyModel<Gender> _propertyModel_3 = new PropertyModel<Gender>(this, "villain.sex");
      final RadioChoice<Gender> gender = new RadioChoice<Gender>("gender", _propertyModel_3, TYPES);
      this._wicketExtensionFactoryMethods.addChild(parent, gender);
      _xblockexpression = gender.setOutputMarkupId(true);
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
          XButton _xButton = new XButton("eliminar");
          final Procedure0 _function_1 = new Procedure0() {
            public void apply() {
            }
          };
          XButton _setOnClick = _xButton.setOnClick(_function_1);
          Files.this._wicketExtensionFactoryMethods.addChild(item, _setOnClick);
        }
      };
      listView.setPopulateItem(_function);
      _xblockexpression = this._wicketExtensionFactoryMethods.addChild(parent, listView);
    }
    return _xblockexpression;
  }
  
  public Villain editarVillano(final Villain villano) {
    return this.villain = villano;
  }
  
  public MarkupContainer agregarBotonesInterPage() {
    MarkupContainer _xblockexpression = null;
    {
      XLink<Object> _xLink = new XLink<Object>("worldmap");
      final Procedure1<XLink<Object>> _function = new Procedure1<XLink<Object>>() {
        public void apply(final XLink<Object> it) {
          final Procedure0 _function = new Procedure0() {
            public void apply() {
              it.<worldMap>setResponsePage(worldMap.class);
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
