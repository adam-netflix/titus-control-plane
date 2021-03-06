/*
 * Copyright 2019 Netflix, Inc.
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

package com.netflix.titus.common.model.sanitizer;

/**
 * Validation errors indicate various reasons why a Job may be malformed.
 */
public class ValidationError {
    private final String field;
    private final String description;
    private final Type type;

    /**
     * The error type defined here is an indication of the seriousness of the error.  In general "HARD" errors should be
     * considered fatal, in that continued use of the validated object is not recommended.  SOFT errors indicate that
     * subsequent use of the validated object is possible.
     */
    public enum Type {
        HARD,
        SOFT
    }

    public ValidationError(String field, String description) {
        this(field, description, Type.HARD);
    }

    public ValidationError(String field, String description, Type type) {
        this.field = field;
        this.description = description;
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    public String getMessage() {
        return String.format("field: '%s', description: '%s', type: '%s'", field, description, type);
    }

    public boolean isHard() {
        return getType().equals(Type.HARD);
    }

    public boolean isSoft() {
        return getType().equals(Type.SOFT);
    }

    @Override
    public String toString() {
        return String.format("Validation failed: '%s'", getMessage());
    }
}
