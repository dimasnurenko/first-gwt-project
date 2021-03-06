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
package com.basicProject.client.showRegisterUsers;

import com.basicProject.client.entity.User;
import com.google.inject.Inject;

import java.util.List;

/**
 * @author Dmitry Shnurenko
 */
public class ShowRegisterUsersPresenter implements ShowRegisterUsersView.ActionDelegate {

    private final ShowRegisterUsersView showRegisterUsersView;

    @Inject
    public ShowRegisterUsersPresenter(ShowRegisterUsersView showRegisterUsersView) {
        this.showRegisterUsersView = showRegisterUsersView;
        this.showRegisterUsersView.setDelegate(this);
    }

    @Override
    public void showUsersFromDataBase(List<User> list) {
        showRegisterUsersView.showRegisterUsers(list);
        showRegisterUsersView.showWindow();
    }

    @Override
    public void onCancelButtonClicked() {
        showRegisterUsersView.hideWindow();
    }
}
