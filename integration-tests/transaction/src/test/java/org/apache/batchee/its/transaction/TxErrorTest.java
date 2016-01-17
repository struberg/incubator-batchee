/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.batchee.its.transaction;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;

import org.apache.batchee.util.Batches;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TxErrorTest {


    @Test
    public void testRolledBackDuringWork() {
        final JobOperator jobOperator = BatchRuntime.getJobOperator();
        BatchStatus batchStatus = Batches.waitFor(jobOperator, jobOperator.start("txtest1", null));
        Assert.assertEquals(batchStatus, BatchStatus.COMPLETED);
        Assert.assertEquals(TxErrorWriter1.written.intValue(), 5);
    }
}
