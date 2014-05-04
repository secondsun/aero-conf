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

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import net.saga.aeroconf.app.data.provider.contract.ConfContract;
import net.saga.aeroconf.app.util.GsonUtils;

import org.jboss.aerogear.android.impl.datamanager.SQLStore;

public class InsertOp<T> implements Operation<Uri> {

    private final ContentResolver resolver;
    private final Uri notifyUri;
    private final Class<T> klass;

    public InsertOp(ContentResolver resolver, Uri notifyUri, Class<T> klass) {
        this.resolver = resolver;
        this.notifyUri = notifyUri;
        this.klass = klass;
    }

    @Override
    public Uri exec(SQLStore store, Uri uri, ContentValues[] values, String selection, String[] selectionArgs) {
        T calendar = GsonUtils.GSON.fromJson(values[0].getAsString(ConfContract.DATA), klass);
        store.save(calendar);

        if (values[0].getAsBoolean(ConfContract.NOTIFY) != null && values[0].getAsBoolean(ConfContract.NOTIFY)) {
            resolver.notifyChange(notifyUri, null, false);
        }
        return uri;
    }
}