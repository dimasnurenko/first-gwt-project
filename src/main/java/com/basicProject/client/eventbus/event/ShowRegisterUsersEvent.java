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
package com.basicProject.client.eventbus.event;

import com.basicProject.client.entity.User;
import com.google.gwt.event.shared.GwtEvent;

import java.util.List;

/**
 * @author Dmitry Shnurenko
 */
public class ShowRegisterUsersEvent extends GwtEvent<ShowRegisterUsersEventHandler> {
    public static Type<ShowRegisterUsersEventHandler> TYPE = new Type<>();

    private List<User> users;

    public ShowRegisterUsersEvent() {

    }

    public List<User> getUsers(){
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public Type<ShowRegisterUsersEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ShowRegisterUsersEventHandler handler) {
        handler.showAllUsersFromDataBase(this);
    }
}
