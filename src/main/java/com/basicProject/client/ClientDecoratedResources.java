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

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

/**
 * @author Dmitry Shnurenko
 */
public interface ClientDecoratedResources extends ClientBundle {

    public interface CssStyles extends CssResource {

        String button();

        String cellTable();

        String buttonPanel();

        String infoTextBoxOnMainPage();

        String errorTextBoxRegisterForm();

        String simplePanel();

        String buttonPanelRegistrationPage();

        String mainPanel();

        String registrationPanel();

    }

    @Source("styles.css")
    CssStyles style();

    @Source("image.jpg")
    ImageResource image();

    @Source("gwttxt.txt")
    TextResource text();

    @Source("externaltext.txt")
    ExternalTextResource getExternalText();

}
