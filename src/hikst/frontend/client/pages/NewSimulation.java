package hikst.frontend.client.pages;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.plaf.RootPaneUI;

import hikst.frontend.client.DatabaseService;
import hikst.frontend.client.DatabaseServiceAsync;
import hikst.frontend.client.SplineGraf;
import hikst.frontend.client.callback.TreeCallback;
import hikst.frontend.shared.HikstObject;
import hikst.frontend.shared.SimObject;
import hikst.frontend.shared.SimObjectTree;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.javac.JsniCollector.Interval;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.FlowPanel;

public class NewSimulation extends Composite implements HasText {

	ViewObjects panel;
	MainPage panelBack;
	private static NewSimulationUiBinder uiBinder = GWT
			.create(NewSimulationUiBinder.class);
	@UiField Button back;
	@UiField Button addObject;
	@UiField DateBox fromDate;
	@UiField DateBox toDate;
	@UiField TextBox intervall;
	@UiField Button buttonShowSpline;
	@UiField FlowPanel eastPanel;
	@UiField FlowPanel centerPanel;
	public @UiField Tree tree;
	private TreeCallback treeCallback;
	
	DatabaseServiceAsync databaseService = GWT.create(DatabaseService.class);
	
	interface NewSimulationUiBinder extends UiBinder<Widget, NewSimulation> {
	}

	public NewSimulation() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	private void updateTree(int id)
	{
		
		databaseService.loadObject(id, treeCallback);
	}
	
	public NewSimulation(Composite parent, HikstObject simObject){
		this();
		fromDate.setValue(((NewSimulation) parent).fromDate.getValue());
		toDate.setValue(((NewSimulation) parent).toDate.getValue());
		intervall.setValue(((NewSimulation) parent).intervall.getValue());
		
		treeCallback = new TreeCallback(this);
		
		updateTree(simObject.getID());
		
	}
	
//	public NewSimulation(int id)
//	{
//		this();
//		treeCallback = new TreeCallback(this);
//		updateTree(id);
//	}

	@UiHandler("addObject")
	void onAddObjectClick(ClickEvent event) {
		//RootLayoutPanel.get().add(new NewObject());
		panel = new ViewObjects(this);
		RootLayoutPanel.get().add(panel);
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub

	}
	@UiHandler("back")
	void onBackClick(ClickEvent event) {
		RootLayoutPanel.get().add(new MainPage());
		panelBack = new MainPage();
		RootLayoutPanel.get().add(panelBack);
	}


	@UiHandler("buttonShowSpline")
	void onButtonShowSplineClick(ClickEvent event) {
		centerPanel.add(SplineGraf.createChart());
		System.out.println("Should show spline!!!");
	}
	

}