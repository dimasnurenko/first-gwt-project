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
package com.basicProject.client.registrationWindow;

import com.basicProject.client.ClientDecoratedResources;
import com.basicProject.client.Localization;
import com.basicProject.client.entity.User;
import com.basicProject.client.eventbus.event.BackButtonEvent;
import com.basicProject.client.eventbus.event.RegistrationEvent;
import com.basicProject.client.eventbus.event.ShowRegisterUsersEvent;
import com.basicProject.client.eventbus.event.ShowTextEvent;
import com.basicProject.client.navigator.MainNavigator;
import com.basicProject.client.showRegisterUsers.ShowRegisterUsersView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwtmockito.GwtMockitoTestRunner;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(GwtMockitoTestRunner.class)
public class RegistrationWindowPresenterTest {

    @Mock
    private RegistrationWindowView   registrationWindowView;
    @Mock
    private RegistrationEvent        registrationEvent;
    @Mock
    private BackButtonEvent          backButtonEvent;
    @Mock
    private ShowRegisterUsersEvent   showRegisterUsersEvent;
    @Mock
    private ShowTextEvent            showTextEvent;
    @Mock
    private ShowRegisterUsersView    registerUsersView;
    @Mock
    private MainNavigator            mainNavigator;
    @Mock
    private EventBus                 eventBus;
    @Mock
    private ClientDecoratedResources clientDecoratedResources;
    @Mock
    private Localization             localization;

    @InjectMocks
    private RegistrationWindowPresenter presenter;

    @Test
    public void userShouldBeAddedToDataBase() throws Exception {
        final String LOGIN = "login";
        final String EMAIL = "email@com.ua";
        final String PASSWORD = "password";

        when(registrationWindowView.getLogin()).thenReturn(LOGIN);
        when(registrationWindowView.getEmail()).thenReturn(EMAIL);
        when(registrationWindowView.getPassword()).thenReturn(PASSWORD);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                List<User> list = (List<User>)args[0];

                assertEquals(list.size(), 0);

                return null;
            }
        }).when(registerUsersView).showRegisterUsers(anyList());

        presenter.showAllUsersFromDataBase(showRegisterUsersEvent);
        presenter.saveEmployeeToDataBase(registrationEvent);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                List<User> list = (List<User>)args[0];
                User testUser = list.get(0);

                assertEquals(list.size(), 1);
                assertEquals(testUser.getLogin(), LOGIN);
                assertEquals(testUser.getEmail(), EMAIL);
                assertEquals(testUser.getPassword(), PASSWORD);

                return null;
            }
        }).when(registerUsersView).showRegisterUsers(anyList());

        presenter.showAllUsersFromDataBase(showRegisterUsersEvent);

        verify(registrationWindowView).getLogin();
        verify(registrationWindowView).getEmail();
        verify(registrationWindowView).getPassword();

        verify(registrationWindowView).setLogin(anyString());
        verify(registrationWindowView).setEmail(anyString());
        verify(registrationWindowView).setPassword(anyString());
    }

    @Test
    public void mainWindowShouldBeAppearedAfterRegisterWindow() throws Exception {
        presenter.setMainNavigator(mainNavigator);
        presenter.backToMainPage(backButtonEvent);

        verify(mainNavigator).showMainWindow();
    }

    @Test
    public void registeredUsersFromDataBaseShouldBeShown() throws Exception {
        presenter.showAllUsersFromDataBase(showRegisterUsersEvent);

        verify(registerUsersView).showRegisterUsers(anyList());
    }

    @Test
    public void textFromExternalTextResourceShouldBeShown() throws Exception {
        final String TEXT = "getExternalText";

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String testtext = (String)args[0];

                assertEquals(testtext, TEXT);

                return null;
            }
        }).when(registrationWindowView).setText(anyString());

        presenter.onTextExternalResourceChange(showTextEvent);

        verify(registrationWindowView).setText(anyString());
    }

    @Test
    public void errorLoginMessageShouldBeShown() throws Exception {
        final String ERROR_LOGIN = "25";

        when(localization.errorLogin()).thenReturn(ERROR_LOGIN);
        when(registrationWindowView.getLogin()).thenReturn(ERROR_LOGIN);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object args[] = invocation.getArguments();
                String errorString = (String)args[0];

                assertEquals(errorString, ERROR_LOGIN);

                return null;
            }
        }).when(registrationWindowView).setErrorLogin(anyString());

        presenter.saveEmployeeToDataBase(registrationEvent);

        verify(registrationWindowView).getLogin();
        verify(localization).errorLogin();
        verify(registrationWindowView).setErrorLogin(anyString());
    }

    @Test
    public void errorEmailMessageShouldBeShown() throws Exception {
        final String LOGIN = "login";
        final String ERROR_EMAIL = "email";

        when(localization.errorEmail()).thenReturn(ERROR_EMAIL);
        when(registrationWindowView.getLogin()).thenReturn(LOGIN);
        when(registrationWindowView.getEmail()).thenReturn(ERROR_EMAIL);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object args[] = invocation.getArguments();
                String errorString = (String)args[0];

                assertEquals(errorString, ERROR_EMAIL);

                return null;
            }
        }).when(registrationWindowView).setErrorEmail(anyString());

        presenter.saveEmployeeToDataBase(registrationEvent);

        verify(registrationWindowView).getLogin();
        verify(registrationWindowView).getEmail();
        verify(registrationWindowView).setErrorEmail(anyString());
        verify(localization).errorEmail();
    }

    @Test
    public void errorPasswordMessageShouldBeShown() throws Exception {
        final String LOGIN = "login";
        final String EMAIL = "email@com.ua";
        final String ERROR_PASSWORD = "+*";

        when(localization.errorPassword()).thenReturn(ERROR_PASSWORD);
        when(registrationWindowView.getLogin()).thenReturn(LOGIN);
        when(registrationWindowView.getEmail()).thenReturn(EMAIL);
        when(registrationWindowView.getPassword()).thenReturn(ERROR_PASSWORD);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object args[] = invocation.getArguments();
                String errorString = (String)args[0];

                assertEquals(errorString, ERROR_PASSWORD);

                return null;
            }
        }).when(registrationWindowView).setErrorPassword(anyString());

        presenter.saveEmployeeToDataBase(registrationEvent);

        verify(localization).errorPassword();
        verify(registrationWindowView).getLogin();
        verify(registrationWindowView).getEmail();
        verify(registrationWindowView).getPassword();
        verify(registrationWindowView).setErrorPassword(anyString());
    }
}
