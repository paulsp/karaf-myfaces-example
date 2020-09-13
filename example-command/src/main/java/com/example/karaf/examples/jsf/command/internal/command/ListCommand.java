/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.example.karaf.examples.jsf.command.internal.command;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.apache.karaf.shell.support.table.ShellTable;

import com.example.karaf.examples.jsf.api.Booking;
import com.example.karaf.examples.jsf.api.BookingService;

@Service
@Command(scope = "booking", name = "list", description = "List the current bookings")
public class ListCommand implements Action {

    @Reference
    private BookingService bookingService;

    @Override
    public Object execute() throws Exception {
        ShellTable table = new ShellTable();
        table.column("ID");
        table.column("Flight");
        table.column("Customer");
        for (Booking booking : bookingService.list()) {
            table.addRow().addContent(booking.getId(), booking.getFlight(), booking.getCustomer());
        }
        table.print(System.out);
        return null;
    }

}