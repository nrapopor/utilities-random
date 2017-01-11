/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2017 ( Jan 10, 2017 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * <DL>
 * <DT>Description:</DT>
 * <DD>Dummy test class for cleaning out coverage noise</DD>
 * <DT>Date:</DT>
 * <DD>Jan 10, 2017</DD>
 * </DL>
 *
 * @author nrapopor - Nick Rapoport
 *
 */
public class SyllableTypeTest {
    @SuppressWarnings("unused")
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SyllableTypeTest.class);

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Dummy test added for completeness to eliminate missed byte code in coverage reports</DD>
     * <DT>Date:</DT>
     * <DD>Jan 10, 2017</DD>
     * </DL>
     */
    @Test
    public final void test() {
        assertNotNull(SyllableType.values());
        assertEquals(SyllableType.valueOf("MIDDLE"), SyllableType.MIDDLE); //$NON-NLS-1$
    }

}
