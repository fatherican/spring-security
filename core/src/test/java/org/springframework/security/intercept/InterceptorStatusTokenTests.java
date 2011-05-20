/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
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

package org.springframework.security.intercept;

import junit.framework.TestCase;

import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextImpl;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.util.SimpleMethodInvocation;

import org.aopalliance.intercept.MethodInvocation;


/**
 * Tests {@link InterceptorStatusToken}.
 *
 * @author Ben Alex
 * @version $Id$
 */
public class InterceptorStatusTokenTests extends TestCase {
    //~ Constructors ===================================================================================================

    public InterceptorStatusTokenTests() {
        super();
    }

    public InterceptorStatusTokenTests(String arg0) {
        super(arg0);
    }

    //~ Methods ========================================================================================================

    public void testNoArgConstructorDoesntExist() {
        Class clazz = InterceptorStatusToken.class;

        try {
            clazz.getDeclaredConstructor((Class[]) null);
            fail("Should have thrown NoSuchMethodException");
        } catch (NoSuchMethodException expected) {
            assertTrue(true);
        }
    }

    public void testOperation() {
        ConfigAttributeDefinition attr = new ConfigAttributeDefinition("FOO");
        MethodInvocation mi = new SimpleMethodInvocation();

        SecurityContext ctx = new SecurityContextImpl();

        InterceptorStatusToken token = new InterceptorStatusToken(ctx, true, attr, mi);

        assertTrue(token.isContextHolderRefreshRequired());
        assertEquals(attr, token.getAttr());
        assertEquals(mi, token.getSecureObject());
        assertSame(ctx, token.getSecurityContext());
    }
}
