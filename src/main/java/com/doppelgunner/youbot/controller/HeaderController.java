package com.doppelgunner.youbot.controller;

import com.doppelgunner.youbot.Util;
import com.doppelgunner.youbot.YouBot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.net.URI;

/**
 * Created by robertoguazon on 15/07/2017.
 */
public class HeaderController extends Controller {

    @FXML private ImageView dpImageView;
    @FXML private Label usernameLabel;
    @FXML private Button myChannelButton;
    @FXML private Button logButton;
    @FXML private Tooltip loginTooltip;
    @FXML private Button demoButton;

    private ImageView login,logout;

    @FXML
    protected void initialize() {
        super.initialize();

        Circle clip = new Circle(32,32,32);
        dpImageView.setClip(clip);

        myChannelButton.setText("Show videos from my channel");
        if (YouBot.isLoggedIn()) {
            setDP(YouBot.getImage());
            myChannelButton.setVisible(true);
        } else {
            setDP(YouBot.getDefaultImage());
            myChannelButton.setVisible(false);
        }

        login = new ImageView("images/login.png");
        logout = new ImageView("images/logout.png");
        logButton.setGraphic(login);

        ImageView demoView = new ImageView();
        demoView.setImage(new Image("/images/youtube.png"));
        demoButton.setGraphic(demoView);
    }

    public Button getLogButton() {
        return logButton;
    }

    public void setDP(Image image) {
        dpImageView.setImage(image);
    }

    public void setUsername(String username) {
        usernameLabel.setText(username);
    }

    @FXML
    public void goDemo() {
        Util.goLink("https://youtu.be/GNQiAWNQ8HA");
    }

    @FXML
    public void showMyVideos()
    {

    }

    @FXML
    public void log() {
        logButton.setDisable(true);
        System.out.println("log button is pressed");
        if (!YouBot.isLoggedIn()) {
            Util.runBackground(
                    () -> YouBot.login(),
                    () -> {
                        if (YouBot.isLoggedIn()) {
                            logButton.setGraphic(logout);
                            loginTooltip.setText("Logout");
                            setDP(YouBot.getImage());
                            setUsername(YouBot.getUsername());
                            myChannelButton.setVisible(true);
                        }
                    }, true);

        } else {
            Util.runBackground(
                    () -> YouBot.logout(),
                    () -> {
                        logButton.setGraphic(login);
                        loginTooltip.setText("Login");
                        setDP(YouBot.getDefaultImage());
                        setUsername("guest");
                        logButton.setDisable(false);
                        myChannelButton.setVisible(false);
                    }
            , false);
        }
    }
}
