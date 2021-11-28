/*
 * Follow https://github.com/KnowGroup
 */
package org.know.group.practice.reactive.model;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

/**
 * @author KnowGroup
 */
@Gson.TypeAdapters
@Value.Immutable
public interface Account {

    String accountNumber();

    String accountType();
}
