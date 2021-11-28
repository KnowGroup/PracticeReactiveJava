/*
 * Follow https://github.com/KnowGroup
 */

package org.know.group.practice.reactive.model;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.time.Instant;

/**
 * @author KnowGroup
 */
@Gson.TypeAdapters
@Value.Immutable
public interface Transaction {
    Instant executionTime();

    Double amount();
}
