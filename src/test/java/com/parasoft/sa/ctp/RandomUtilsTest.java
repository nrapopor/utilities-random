/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2016 ( Dec 23, 2016 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.parasoft.api.ExtensionToolContext;

/**
 * <DL>
 * <DT>Description:</DT>
 * <DD>test RandomUtils class</DD>
 * <DT>Date:</DT>
 * <DD>Dec 23, 2016</DD>
 * </DL>
 *
 * @author nrapopor - Nick Rapoport
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RandomUtilsTest extends AbstractTestBase {
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RandomUtilsTest.class);

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Set Up Before Class</DD>
     * <DT>Date:</DT>
     * <DD>Dec 23, 2016</DD>
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
     * <DD>Dec 23, 2016</DD>
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
     * <DD>Dec 23, 2016</DD>
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
     * <DD>Dec 23, 2016</DD>
     * </DL>
     *
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.parasoft.sa.ctp.RandomUtils#getIstance()}.
     */
    @Test
    public void testGetInstance() {
        final IConstants utils = RandomUtils.getIstance();
        assertNotNull(utils);
        final IConstants utils2 = RandomUtils.getIstance();
        assertSame(utils, utils2);
    }

    /**
     * Test method for
     * {@link com.parasoft.sa.ctp.RandomUtils#getRandomAlphanumeric(java.lang.Object, com.parasoft.api.ExtensionToolContext)}.
     */
    @Test
    public void testGetRandomAlphanumeric() {
        final int testLength = 15;
        String result = RandomUtils.getRandomAlphanumeric("" + testLength, (ExtensionToolContext) null); //$NON-NLS-1$
        assertNotNull("No random value returned", result); //$NON-NLS-1$
        assertEquals("Length does not match expected", testLength, result.length()); //$NON-NLS-1$

        result = RandomUtils.getRandomAlphanumeric("", (ExtensionToolContext) null); //$NON-NLS-1$
        assertNotNull("No random value returned", result); //$NON-NLS-1$
        assertEquals("Length does not match expected", IConstants.DEFAULT_LENGTH, result.length()); //$NON-NLS-1$
    }

    /**
     * Test method for {@link com.parasoft.sa.ctp.RandomUtils#getRandomName()}.
     */
    @Test
    public void testGetRandomName() {
        ((org.apache.logging.log4j.core.Logger) LogManager.getLogger(Utils.class.getName())).setLevel(Level.TRACE);
        String result = RandomUtils.getRandomName();
        assertNotNull("No random value returned", result); //$NON-NLS-1$

        log.info("Name 1: {}", result); //$NON-NLS-1$
        System.setProperty(IConstants.SYLLABLE_COUNT, "4"); //$NON-NLS-1$
        RandomUtils.resetNames();
        final String result2 = RandomUtils.getRandomName();
        assertNotNull("No random value returned", result2); //$NON-NLS-1$
        log.info("Name 2: {}", result2); //$NON-NLS-1$
        RandomUtils.setLastNames(null);
        assertNotNull(RandomUtils.getLastNames());
        prepareFile("/NameGeneratorSyllable-NoMid.txt"); //$NON-NLS-1$
        final INameGenerator test;
        try {
            test = new NameGenerator();
            RandomUtils.setFirstNames(test);
        } catch (final NameGeneratorException ex) {
            log.error("caught {} Error : ", ex.getClass().getSimpleName() //$NON-NLS-1$
                , ex);
            fail("unexpected exception"); //$NON-NLS-1$
        }
        result = RandomUtils.getRandomName();
        assertEquals("empty string not value", "", result); //$NON-NLS-1$ //$NON-NLS-2$
        System.setProperty(IConstants.SYLLABLE_COUNT, "NOT A NUMBER"); //$NON-NLS-1$
        RandomUtils.resetNames();
        assertEquals(RandomUtils.getSyllableCount(), IConstants.DEFAULT_SYLLABLE_COUNT);
        System.setProperty(IConstants.FIRST_NAME_SYLLABLES, "NO SUCH FILE"); //$NON-NLS-1$
        RandomUtils.resetNames();
        //assertEquals("Length does not match expected", testLength, result.length());
    }

    /**
     * Test method for {@link com.parasoft.sa.ctp.RandomUtils#getRandomName(Object, ExtensionToolContext)}.
     */
    @Test
    public void testGetRandomNameObjectExtensionToolContext() {
        final List<String> argList = Arrays.asList(new String[] { "3,5", "", "z,5", "5,z", ",", "5,", ",5", null }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
        for (final String arg : argList) {
            String result = RandomUtils.getRandomName(arg == null ? "null" : arg, (ExtensionToolContext) null); //$NON-NLS-1$
            log.info("Name 1{{}}: {}", arg, result); //$NON-NLS-1$
            assertNotNull("No random value returned", result); //$NON-NLS-1$
            ((org.apache.logging.log4j.core.Logger) LogManager.getLogger(RandomUtils.class.getName()))
                .setLevel(Level.INFO);
            result =
                RandomUtils.getRandomName(arg == null ? null : Stream.of(arg.split(",\\s*")).toArray(String[]::new)); //$NON-NLS-1$
            ((org.apache.logging.log4j.core.Logger) LogManager.getLogger(RandomUtils.class.getName()))
                .setLevel(Level.TRACE);
            log.info("Name 2{{}}: {}", arg, result); //$NON-NLS-1$
            assertNotNull("No random value returned", result); //$NON-NLS-1$
        }
        //((org.apache.logging.log4j.core.Logger) LogManager.getLogger(Utils.class.getName())).setLevel(Level.INFO);
        final int[] counts = new int[] { 5 };
        final String result = RandomUtils.getRandomName(counts);
        log.info("Name 2{{}}: {}", Arrays.toString(counts), result); //$NON-NLS-1$
        assertNotNull("No random value returned", result); //$NON-NLS-1$
        //((org.apache.logging.log4j.core.Logger) LogManager.getLogger(Utils.class.getName())).setLevel(Level.TRACE);

    }

    /**
     * Test method for
     * {@link com.parasoft.sa.ctp.RandomUtils#getRandomNumber(java.lang.Object, com.parasoft.api.ExtensionToolContext)}.
     */
    @Test
    public void testGetRandomNumber() {
        final int testLength = 15;
        String result = RandomUtils.getRandomNumber("" + testLength, (ExtensionToolContext) null); //$NON-NLS-1$
        assertNotNull("No random value returned", result); //$NON-NLS-1$
        assertEquals("Length does not match expected", testLength, result.length()); //$NON-NLS-1$

        result = RandomUtils.getRandomNumber("", (ExtensionToolContext) null); //$NON-NLS-1$
        assertNotNull("No random value returned", result); //$NON-NLS-1$
        assertEquals("Length does not match expected", IConstants.DEFAULT_LENGTH, result.length()); //$NON-NLS-1$
    }

    /**
     * Test method for
     * {@link com.parasoft.sa.ctp.RandomUtils#getRandomString(java.lang.Object, com.parasoft.api.ExtensionToolContext)}.
     */
    @Test
    public void testGetRandomString() {
        final int testLength = 15;
        String result = RandomUtils.getRandomString("" + testLength, (ExtensionToolContext) null); //$NON-NLS-1$
        assertNotNull("No random value returned", result); //$NON-NLS-1$
        assertEquals("Length does not match expected", testLength, result.length()); //$NON-NLS-1$

        result = RandomUtils.getRandomString("", (ExtensionToolContext) null); //$NON-NLS-1$
        assertNotNull("No random value returned", result); //$NON-NLS-1$
        assertEquals("Length does not match expected", IConstants.DEFAULT_LENGTH, result.length()); //$NON-NLS-1$
    }
}
