/*
 * Copyright 2018 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.titus.runtime.endpoint.metadata;

import com.netflix.titus.api.jobmanager.model.CallMetadata;
import com.netflix.titus.api.jobmanager.model.Caller;
import com.netflix.titus.api.jobmanager.model.CallerType;

import java.util.Collections;
import java.util.Optional;
import javax.inject.Singleton;

@Singleton
public class AnonymousCallMetadataResolver implements CallMetadataResolver {

    private static final AnonymousCallMetadataResolver INSTANCE = new AnonymousCallMetadataResolver();

    private static final String ANONYMOUS_ID = "anonymous";

    private static final CallMetadata ANONYMOUS = CallMetadata.newBuilder()
            .withCallerId(ANONYMOUS_ID)
            .withCallPath(Collections.singletonList(ANONYMOUS_ID))
            .withCallReason("")
            .withCallers(Collections.singletonList(Caller.newBuilder()
                    .withId(ANONYMOUS_ID)
                    .withCallerType(CallerType.Unknown)
                    .build()
            ))
            .build();

    private static final Optional<CallMetadata> ANONYMOUS_OPTIONAL = Optional.of(ANONYMOUS);

    @Override
    public Optional<CallMetadata> resolve() {
        return ANONYMOUS_OPTIONAL;
    }

    public static AnonymousCallMetadataResolver getInstance() {
        return INSTANCE;
    }
}