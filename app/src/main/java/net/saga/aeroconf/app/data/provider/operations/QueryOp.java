/* Copyright 2014 Hoyt Summers Pittman III
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package net.saga.aeroconf.app.data.provider.operations;


import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import net.saga.aeroconf.app.data.provider.contract.SingleColumnJsonArrayList;

import org.jboss.aerogear.android.impl.datamanager.SQLStore;

import java.util.ArrayList;
import java.util.Collections;

public class QueryOp implements Operation<Cursor> {

    @Override
    public SingleColumnJsonArrayList exec(SQLStore store, Uri uri, ContentValues[] values, String selection, String[] selectionArgs) {
        ArrayList collection = new ArrayList(store.readAll());
        Collections.sort(collection);
        return new SingleColumnJsonArrayList(collection);
    }
}