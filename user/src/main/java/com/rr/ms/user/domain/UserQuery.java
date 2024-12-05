package com.rr.ms.user.domain;

import org.inferred.freebuilder.FreeBuilder;

import java.util.Optional;
import java.util.Set;

@FreeBuilder
public interface UserQuery {
    Optional<Set<Long>> ids();
    Optional<String> names();

    class Builder extends UserQuery_Builder {

    }
}
