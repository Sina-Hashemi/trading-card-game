package com.example.controller;

import com.example.model.*;
import com.example.enums.*;

public class ShopMenuController {

    public static Result back() {
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Entered main menu!");
    }
}
