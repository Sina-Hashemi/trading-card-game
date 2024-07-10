package com.example.interactor;

import com.example.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SettingMenuInteractor implements Initializable {
    public static String theme = "Light";
    public static MediaPlayer player;
    public static AudioClip audioClip;

    @FXML
    private Button changeThemeButton;
    @FXML
    private Button returnBackButton;
    @FXML
    private Slider musicSlider ;
    @FXML
    private CheckBox muteMusicCheckBox ;
    @FXML
    private Slider soundEffectSlider ;
    @FXML
    private CheckBox muteSoudnEffectCheckBox ;

    @FXML
    protected void onChangeThemButtonClick() {
        if(theme.equals("Dark")) theme = "Light";
        if(theme.equals("Light")) theme = "Dark";

        setTheme();
    }

    @FXML
    protected void onReturnBackButtonClick() throws IOException {
        Main.setRoot("MainMenu");
    }

    @FXML
    protected void onChangeMusicVolumeSlider(){
    }

    @FXML
    protected void onChangeSoundEffectVolumeSlider(){
    }

    @FXML
    protected void onMuteMusicVolumeCheckBox(){
        player.setMute(muteMusicCheckBox.isSelected());
    }

    @FXML
    protected void onMuteSoundEffectVolumeCheckBox(){
        if(audioClip != null) {
            if(muteMusicCheckBox.isSelected()) audioClip.setVolume(0);
            else audioClip.setVolume(50);
        }
    }

    public static void setTheme() {
        // Main.scene.getStylesheets().add(theme + "/theme.css");
        Main.scene.getStylesheets().add((new Main()).getClass().getResource(theme + "/theme.css").toExternalForm());

        if(player != null) player.stop();
        Media music = new Media((new Main()).getClass().getResource(theme + "/music.mp3").toExternalForm());
        player = new MediaPlayer(music);
        player.play();
    }

    public static void playSoundEffect(String address) {
        audioClip = new AudioClip((new Main()).getClass().getResource(theme + "/" + address).toExternalForm());
        audioClip.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        musicSlider.setMinorTickCount(0);
        musicSlider.setMajorTickUnit(100);
        musicSlider.setValue(50);
        musicSlider.setShowTickMarks(true);
        musicSlider.setShowTickLabels(true);
        musicSlider.setMajorTickUnit(25);
        musicSlider.setBlockIncrement(10);

        musicSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue <? extends Number > observable, Number oldValue, Number newValue) {
                player.setVolume((double) newValue);
            }
        });

        soundEffectSlider.setMinorTickCount(0);
        soundEffectSlider.setMajorTickUnit(100);
        soundEffectSlider.setValue(50);
        soundEffectSlider.setShowTickMarks(true);
        soundEffectSlider.setShowTickLabels(true);
        soundEffectSlider.setMajorTickUnit(25);
        soundEffectSlider.setBlockIncrement(10);

        soundEffectSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue <? extends Number > observable, Number oldValue, Number newValue) {
                audioClip.setVolume((double) newValue);
            }
        });
    }
}