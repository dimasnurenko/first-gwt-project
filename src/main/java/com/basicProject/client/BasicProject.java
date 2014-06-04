/*
 * Copyright 2014 Codenvy, S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.basicProject.client;

import com.basicProject.client.dialogWindow.DialogWindowPresenter;
import com.basicProject.client.dialogWindow.DialogWindowView;
import com.basicProject.client.dialogWindow.DialogWindowViewImpl;
import com.basicProject.client.mainWindow.MainWindowPresenter;
import com.basicProject.client.mainWindow.MainWindowView;
import com.basicProject.client.mainWindow.MainWindowViewImpl;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

/**
 * @author Dmitry Shnurenko
 */
public class BasicProject implements EntryPoint {

    @Override
    public void onModuleLoad() {
        Styles styles = GWT.create(Styles.class);
        styles.style().ensureInjected();

        Localization localization = GWT.create(Localization.class);


        DialogWindowView dialogWindowView = new DialogWindowViewImpl();
        DialogWindowPresenter dialogWindowPresenter = new DialogWindowPresenter(dialogWindowView);

        MainWindowView mainWindow = new MainWindowViewImpl(localization);
        MainWindowPresenter presenter = new MainWindowPresenter(mainWindow, dialogWindowPresenter);

        SimpleLayoutPanel panel = new SimpleLayoutPanel();
        panel.add(mainWindow);
        RootLayoutPanel.get().add(panel);
    }
}
