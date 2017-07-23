package com.doppelgunner.youbot.controller;

import javafx.fxml.FXML;
import javafx.stage.*;

public abstract class Controller {
	
	protected Stage stage;

	@FXML
	protected void initialize() {
		System.out.println("INIT: " + getClass().getSimpleName());
	}

	public void dispose() {
	}
}