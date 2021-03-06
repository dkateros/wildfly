/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.as.cli.handlers;

import org.jboss.as.util.security.GetClassLoaderAction;
import org.jboss.as.util.security.ReadEnvironmentPropertyAction;
import org.jboss.as.util.security.ReadPropertyAction;

import static java.lang.System.getProperty;
import static java.lang.System.getSecurityManager;
import static java.lang.System.getenv;
import static java.security.AccessController.doPrivileged;

/**
 * Package privileged actions
 *
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 * @author Scott.Stark@jboss.org
 * @author Alexey Loubyansky
 */
class SecurityActions {

    static String getSystemProperty(String name) {
        return getSecurityManager() == null ? getProperty(name) : doPrivileged(new ReadPropertyAction(name));
    }

    static String getEnvironmentVariable(String name) {
        return getSecurityManager() == null ? getenv(name) : doPrivileged(new ReadEnvironmentPropertyAction(name));
    }

    static ClassLoader getClassLoader(Class<?> cls) {
        return getSecurityManager() == null ? cls.getClassLoader() : doPrivileged(new GetClassLoaderAction(cls));
    }
}
