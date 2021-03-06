/*
 * Copyright 2012-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.artifact_cache;

import com.facebook.buck.io.BorrowablePath;
import com.facebook.buck.io.LazyPath;
import com.facebook.buck.rules.RuleKey;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class NoopArtifactCache implements ArtifactCache {

  @Override
  public CacheResult fetch(RuleKey ruleKey, LazyPath output) {
    // Do nothing.
    return CacheResult.miss();
  }

  @Override
  public ListenableFuture<Void> store(
      ImmutableSet<RuleKey> ruleKeys,
      ImmutableMap<String, String> metadata,
      BorrowablePath output) {
    return Futures.immediateFuture(null);
  }

  /** @return {@code false}: storing artifacts is never supported by this class. */
  @Override
  public boolean isStoreSupported() {
    return false;
  }

  @Override
  public void close() {
    // Nothing to complete - do nothing.
  }
}
