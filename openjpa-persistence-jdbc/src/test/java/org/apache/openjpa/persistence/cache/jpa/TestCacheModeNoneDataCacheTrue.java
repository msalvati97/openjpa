/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.openjpa.persistence.cache.jpa;

import java.util.HashMap;
import java.util.Map;

import org.apache.openjpa.persistence.OpenJPAEntityManagerFactorySPI;

/*
 * Caching is off when shared cache mode is set to NONE no matter
 * dataCache is set to true or not.
 * */
public class TestCacheModeNoneDataCacheTrue extends TestCacheModeNone{
    @Override
    public OpenJPAEntityManagerFactorySPI getEntityManagerFactory() {
        Map<String,Object> props = new HashMap<>();
        props.put("openjpa.DataCache", "true");
        props.put("openjpa.RemoteCommitProvider", "sjvm");
        if (emf == null) {
            emf = createEntityManagerFactory("cache-mode-none",props);
            assertNotNull(emf);
            assertEquals("true", emf.getConfiguration().getDataCache());
            cache = emf.getCache();
            assertNotNull(cache);
        }
        return emf;
    }

}
