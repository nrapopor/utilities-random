/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2017 ( Jan 9, 2017 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * <DL>
 * <DT>Description:</DT>
 * <DD>TODO add description</DD>
 * <DT>Date:</DT>
 * <DD>Jan 9, 2017</DD>
 * </DL>
 *
 * @author nrapopor - Nick Rapoport
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoadConfigTest implements IConstants {
    /**
     * <DL>
     * <DT>GOOD_KEY</DT>
     * <DD>TODO add GOOD_KEY description</DD>
     * </DL>
     */
    private static final String GOOD_KEY = "last-names-syllables-key"; //$NON-NLS-1$

    private static final String GOOD_INT_KEY = "syllable-count-default"; //$NON-NLS-1$

    private static final String BAD_INT_KEY = "dummy-int"; //$NON-NLS-1$

    @SuppressWarnings("unused")
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoadConfigTest.class);

    private static final String BAD_KEY = "no-such-key"; //$NON-NLS-1$

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>set Up Before Class</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Tear Down After Class</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Set Up before each test</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Tear Down after each test</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.parasoft.sa.ctp.LoadConfig#getInt(java.lang.String, int)}.
     */
    @Test
    public final void testGetInt() {
        int intVal = LoadConfig.getInt(GOOD_INT_KEY, 9000);
        assertNotEquals(9000, intVal);
        intVal = LoadConfig.getInt(BAD_KEY, 9999);
        assertEquals(9999, intVal);
        intVal = LoadConfig.getInt(BAD_INT_KEY, 8888);
        assertEquals(8888, intVal);
    }

    /**
     * Test method for {@link com.parasoft.sa.ctp.LoadConfig#getString(java.lang.String)}.
     */
    @Test
    public final void testGetStringString() {
        String strVal = LoadConfig.getString(GOOD_KEY);
        assertEquals(LAST_NAME_SYLLABLES, strVal);
        strVal = LoadConfig.getString(BAD_KEY);
        assertEquals("!" + BAD_KEY + "!", strVal); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Test method for {@link com.parasoft.sa.ctp.LoadConfig#getString(java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testGetStringStringString() {
        final String bad = LoadConfig.getString(BAD_KEY, "default"); //$NON-NLS-1$
        assertEquals("default", bad); //$NON-NLS-1$
    }

    /** {@inheritDoc} */
    public void changeFile(final String fileName) throws NameGeneratorException {
    }

}
