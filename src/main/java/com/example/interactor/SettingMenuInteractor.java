package com.example.interactor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;

public class SettingMenuInteractor {

    @FXML
    private Button changeSoundButton;
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
    protected void onChangeSoundButtonClick() {
    }

    @FXML
    protected void onChangeThemButtonClick() {
    }

    @FXML
    protected void onReturnBackButtonClick() {
    }

    @FXML
    protected void onChangeMusicVolumeSlider(){
    }
    @FXML
    protected void onChangeSoundEffectVolumeSlider(){
    }

    @FXML
    protected void onMuteMusicVolumeCheckBox(){
    }
    @FXML
    protected void onMuteSoundEffectVolumeCheckBox(){
    }
}