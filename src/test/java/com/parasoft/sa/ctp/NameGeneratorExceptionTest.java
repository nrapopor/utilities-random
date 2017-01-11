/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2017 ( Jan 7, 2017 ) Nick Rapoport all rights reserved.
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
 * <DD>Test NameGeneratorException class</DD>
 * <DT>Date:</DT>
 * <DD>Jan 7, 2017</DD>
 * </DL>
 *
 * @author nrapopor - Nick Rapoport
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NameGeneratorExceptionTest extends NameGeneratorException implements IConstants {
    /**
     * <DL>
     * <DT>serialVersionUID</DT>
     * <DD>default value of 1</DD>
     * </DL>
     */
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NameGeneratorExceptionTest.class);

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
     * Test method for {@link com.parasoft.sa.ctp.NameGeneratorException#NameGeneratorException()}.
     */
    @Test
    public void testNameGeneratorException() {
        final NameGeneratorException ex = new NameGeneratorException();
        assertNotNull(ex);
    }

    /**
     * Test method for {@link com.parasoft.sa.ctp.NameGeneratorException#NameGeneratorException(java.lang.String)}.
     */
    @Test
    public void testNameGeneratorExceptionString() {
        final NameGeneratorException ex = new NameGeneratorException("Message"); //$NON-NLS-1$
        assertNotNull(ex);
        assertEquals("Message", ex.getMessage()); //$NON-NLS-1$
    }

    /**
     * Test method for
     * {@link com.parasoft.sa.ctp.NameGeneratorException#NameGeneratorException(java.lang.String, java.lang.Throwable)}.
     */
    @Test
    public void testNameGeneratorExceptionStringThrowable() {
        final Exception cause = new Exception();
        final NameGeneratorException ex = new NameGeneratorException("Message", cause); //$NON-NLS-1$
        assertNotNull(ex);
        assertEquals("Message", ex.getMessage()); //$NON-NLS-1$
        assertEquals(cause, ex.getCause());

    }

    /**
     * Test method for
     * {@link com.parasoft.sa.ctp.NameGeneratorException#NameGeneratorException(java.lang.String, java.lang.Throwable, boolean, boolean)}.
     */
    @Test
    public void testNameGeneratorExceptionStringThrowableBooleanBoolean() {
        final Exception cause = new Exception();
        final NameGeneratorException ex = new NameGeneratorException("Message", cause, true, true); //$NON-NLS-1$
        assertNotNull(ex);
        assertEquals("Message", ex.getMessage()); //$NON-NLS-1$
        assertEquals(cause, ex.getCause());
    }

    /**
     * Test method for {@link com.parasoft.sa.ctp.NameGeneratorException#NameGeneratorException(java.lang.Throwable)}.
     */
    @Test
    public void testNameGeneratorExceptionThrowable() {
        final Exception cause = new Exception();
        final NameGeneratorException ex = new NameGeneratorException(cause);
        assertNotNull(ex);
        assertEquals(cause, ex.getCause());
    }

    /** {@inheritDoc} */
    public void changeFile(final String fileName) throws NameGeneratorException {
    }

}
