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
package net.saga.aeroconf.app.data.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import net.saga.aeroconf.app.data.provider.contract.ConfContract;
import net.saga.aeroconf.app.data.provider.operations.BulkInsertOp;
import net.saga.aeroconf.app.data.provider.operations.DeleteOp;
import net.saga.aeroconf.app.data.provider.operations.FetchOp;
import net.saga.aeroconf.app.data.provider.operations.InsertOp;
import net.saga.aeroconf.app.data.provider.operations.Operation;
import net.saga.aeroconf.app.data.provider.operations.QueryOp;
import net.saga.aeroconf.app.data.provider.operations.UpdateOp;
import net.saga.aeroconf.app.data.vo.Presentation;
import net.saga.aeroconf.app.data.vo.Room;
import net.saga.aeroconf.app.data.vo.Schedule;
import net.saga.aeroconf.app.data.vo.Speaker;

public class AeroConfContentProvider extends AbstractAeroConfProvider implements ConfContract {

    private ContentResolver resolver;

    public AeroConfContentProvider() {

    }


    @Override
    public boolean onCreate() {

        roomStore = registerAndOpenStore(Room.class);
        speakerStore = registerAndOpenStore(Speaker.class);
        presentationStore = registerAndOpenStore(Presentation.class);
        scheduleStore = registerAndOpenStore(Schedule.class);
        resolver = getContext().getContentResolver();
        return true;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int match = MATCHER.match(uri);
        Operation<Integer> op = null;

        switch (match) {
            case RoomContract.ROOM:
                op = new DeleteOp(resolver, RoomContract.URI);
                break;
            case SpeakerContract.SPEAKER:
                op = new DeleteOp(resolver, SpeakerContract.URI);
                break;
            case PresentationContract.PRESENTATION:
                op = new DeleteOp(resolver, PresentationContract.URI);
                break;
            case ScheduleContract.SCHEDULE:
                op = new DeleteOp(resolver, ScheduleContract.URI);
                break;
        }

        Integer res = execute(uri, null, selection, selectionArgs, op);
        if (res == null) {
            res = 0;
        }

        return res;

    }

    @Override
    public String getType(Uri uri) {

        int match = MATCHER.match(uri);

        switch (match) {
            case RoomContract.ROOM:
            case SpeakerContract.SPEAKER:
            case PresentationContract.PRESENTATION:
            case ScheduleContract.SCHEDULE:
                break;
            default: {
                throw new IllegalArgumentException(String.format("%s not supported", uri.toString()));
            }
        }

        return uri.toString();
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        int match = MATCHER.match(uri);
        Operation<Uri> operation;
        switch (match) {
            case RoomContract.ROOM:
                operation = new InsertOp<>(resolver, RoomContract.URI, Room.class);
                break;
            case SpeakerContract.SPEAKER:
                operation = new InsertOp<>(resolver, SpeakerContract.URI, Speaker.class);
                break;
            case PresentationContract.PRESENTATION:
                operation = new InsertOp<>(resolver, PresentationContract.URI, Presentation.class);
                break;
            case ScheduleContract.SCHEDULE:
                operation = new InsertOp<>(resolver, ScheduleContract.URI, Schedule.class);
                break;
            default: {
                throw new IllegalArgumentException(String.format("%s not supported", uri.toString()));
            }
        }

        return execute(uri, new ContentValues[]{values}, null, null, operation);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        int match = MATCHER.match(uri);

        switch (match) {
            case RoomContract.ROOM:
            case SpeakerContract.SPEAKER:
            case PresentationContract.PRESENTATION:
            case ScheduleContract.SCHEDULE:
                return execute(uri, null, selection, selectionArgs, new QueryOp());
            case RoomContract.ROOM_ID:
            case SpeakerContract.SPEAKER_ID:
            case PresentationContract.PRESENTATION_ID:
            case ScheduleContract.SCHEDULE_ID:
                return execute(uri, null, selection, selectionArgs, new FetchOp());
            default:
                throw new IllegalArgumentException(String.format("%s not supported", uri.toString()));
        }

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Operation<Integer> op;
        int match = MATCHER.match(uri);
        ContentValues[] vals;

        if (values == null) {
            vals = new ContentValues[]{null};
        } else {
            vals = new ContentValues[]{values};
        }

        switch (match) {
            case RoomContract.ROOM:
                op = new UpdateOp<>(resolver, RoomContract.URI, Room.class);
                break;
            case SpeakerContract.SPEAKER:
                op = new UpdateOp<>(resolver, SpeakerContract.URI, Speaker.class);
                break;
            case PresentationContract.PRESENTATION:
                op = new UpdateOp<>(resolver, PresentationContract.URI, Presentation.class);
                break;
            case ScheduleContract.SCHEDULE:
                op = new UpdateOp<>(resolver, ScheduleContract.URI, Schedule.class);
                break;
            default:
                throw new IllegalArgumentException(String.format("%s not supported", uri.toString()));
        }

        Integer res = execute(uri, vals, selection, selectionArgs, op);
        if (res == null) {
            res = 0;
        }
        return res;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        int match = MATCHER.match(uri);
        Operation<Integer> operation;
        switch (match) {
            case RoomContract.ROOM:
                operation = new BulkInsertOp<>(resolver, RoomContract.URI, Room.class);
                break;
            case SpeakerContract.SPEAKER:
                operation = new BulkInsertOp<>(resolver, SpeakerContract.URI, Speaker.class);
                break;
            case PresentationContract.PRESENTATION:
                operation = new BulkInsertOp<>(resolver, PresentationContract.URI, Presentation.class);
                break;
            case ScheduleContract.SCHEDULE:
                operation = new BulkInsertOp<>(resolver, ScheduleContract.URI, Schedule.class);
                break;
            default: {
                throw new IllegalArgumentException(String.format("%s not supported", uri.toString()));
            }
        }

        Integer res = execute(uri, values, null, null, operation);

        if (res == null) {
            return 0;
        } else {
            return res;
        }
    }
}
