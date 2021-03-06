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
import com.basicProject.client.mvp.View;
import com.google.inject.ImplementedBy;

import java.util.List;

/**
 * @author Dmitry Shnurenko
 */
@ImplementedBy(ShowRegisterUsersViewImpl.class)
public interface ShowRegisterUsersView extends View<ShowRegisterUsersView.ActionDelegate> {

    public interface ActionDelegate {

        void onCancelButtonClicked();

        void showUsersFromDataBase(List<User> list);

    }

    void hideWindow();

    void showWindow();

    void showRegisterUsers(List<User> list);
}
