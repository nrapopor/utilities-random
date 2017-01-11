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
 * <DD>test NameGenerator class</DD>
 * <DT>Date:</DT>
 * <DD>Jan 7, 2017</DD>
 * </DL>
 *
 * @author nrapopor - Nick Rapoport
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NameGeneratorTest extends AbstractTestBase {
    protected static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NameGeneratorTest.class);

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>setup before class</DD>
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
     * <DD>tear down after class</DD>
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
     * <DD>set up for the test</DD>
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
     * <DD>tear down after each test</DD>
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
     * Test method for {@link com.parasoft.sa.ctp.NameGenerator#changeFile(String)}.
     *
     */
    @Test
    public void testChangeFileError() {
        final String testFile = "/NameGeneratorSyllable-NoMidConsonants.txt"; //$NON-NLS-1$
        System.setProperty(IConstants.SYLLABLE_FILE, testFile);
        final String expected = "File name cannot be null or empty"; //$NON-NLS-1$
        INameGenerator test = null;
        boolean thrown = false;

        try {
            test = new NameGenerator();
            assertNotNull(test);
            test.changeFile(null);
        } catch (final NameGeneratorException ex) {
            log.info("caught expected {} : {} ", ex.getClass().getSimpleName() //$NON-NLS-1$
                , ex.getMessage());
            assertEquals("Expected bad file message", expected.trim(), ex.getMessage().trim()); //$NON-NLS-1$
            thrown = true;
        }
        assertTrue("exception was expected", thrown); //$NON-NLS-1$
        thrown = false;
        try {
            test.changeFile(""); //$NON-NLS-1$
        } catch (final NameGeneratorException ex) {
            log.info("caught expected {} : {} ", ex.getClass().getSimpleName() //$NON-NLS-1$
                , ex.getMessage());
            assertEquals("Expected bad file message", expected.trim(), ex.getMessage().trim()); //$NON-NLS-1$
            thrown = true;
        }
        assertTrue("exception was expected", thrown); //$NON-NLS-1$
    }

    /**
     * Test method for {@link com.parasoft.sa.ctp.NameGenerator#compose(int)}.
     *
     */
    @Test
    public void testComposeErrors() {
        prepareFile("/NameGeneratorSyllable-NoMidConsonants.txt"); //$NON-NLS-1$
        testCompose("compose(int syls) can't have less than 1 syllable", 0); //$NON-NLS-1$
        testCompose(String.format(IConstants.MISSING_MIDDLE_ERROR_FMT, prepareFile("/NameGeneratorSyllable-NoMid.txt")), //$NON-NLS-1$
            3);
        testCompose(String.format(IConstants.MISSING_MIDDLE_ERROR_FMT, prepareFile("/NameGeneratorSyllable-NoMid.txt")), //$NON-NLS-1$
            2, false);
        testCompose(
            String.format(IConstants.MISSING_PREFIX_ERROR_FMT, prepareFile("/NameGeneratorSyllable-NoPrefixes.txt")), //$NON-NLS-1$
            3);
        testCompose(
            String.format(IConstants.MISSING_SUFFIX_ERROR_FMT, prepareFile("/NameGeneratorSyllable-NoSuffixes.txt")), //$NON-NLS-1$
            3);
    }

    /**
     * Test method for {@link com.parasoft.sa.ctp.NameGenerator#compose(int)}.
     *
     */
    @Test
    public void testComposeNoMiddleConsonants() {
        final String testFile = "/NameGeneratorSyllable-NoMidConsonants.txt"; //$NON-NLS-1$
        System.setProperty(IConstants.SYLLABLE_FILE, testFile);
        final String expected = String.format(IConstants.EXPECTED_ERROR_FMT, "middle", CONSONANT); //$NON-NLS-1$
        String result = null;
        try {
            final INameGenerator test = new NameGenerator();
            result = test.compose(3);
        } catch (final NameGeneratorException ex) {
            log.info("caught expected {} Error : {} ", ex.getClass().getSimpleName() //$NON-NLS-1$
                , ex.getMessage());
            assertEquals("Expected no middle constants message", expected.trim(), ex.getMessage().trim()); //$NON-NLS-1$
        }
        assertNull("exception was expected, result should be null", result); //$NON-NLS-1$
    }

    /**
     * Test method for {@link com.parasoft.sa.ctp.NameGenerator#compose(int)}.
     *
     */
    @Test
    public void testComposeNoMiddleVowel() {
        final String testFile = "/NameGeneratorSyllable-NoMidVowels.txt"; //$NON-NLS-1$
        System.setProperty(IConstants.SYLLABLE_FILE, testFile);
        final String expected = String.format(IConstants.EXPECTED_ERROR_FMT, "middle", "vowel"); //$NON-NLS-1$ //$NON-NLS-2$
        String result = null;
        try {
            final INameGenerator test = new NameGenerator();
            result = test.compose(3);
        } catch (final NameGeneratorException ex) {
            log.info("caught expected {} : {} ", ex.getClass().getSimpleName() //$NON-NLS-1$
                , ex.getMessage());
            assertEquals("Expected no middle constants message", expected.trim(), ex.getMessage().trim()); //$NON-NLS-1$
        }
        assertNull("exception was expected, result should be null", result); //$NON-NLS-1$
    }

    /**
     * Test method for {@link com.parasoft.sa.ctp.NameGenerator#compose(int)}.
     *
     */
    @Test
    public void testConstructor() {
        final String testFile = "/NameGeneratorSyllable-NoMidConsonants.txt"; //$NON-NLS-1$
        System.setProperty(IConstants.SYLLABLE_FILE, testFile);
        //final String expected = String.format(ISyllableListHolder.EXPECTED_ERROR_FMT, "middle", CONSONANT);
        INameGenerator test = null;

        try {
            test = new NameGenerator();
            assertNotNull(test);
            test = new NameGenerator(testFile);
            assertNotNull(test);
            test.changeFile(testFile);
            assertNotNull(test);
        } catch (final NameGeneratorException ex) {
            log.error("caught expected {} Error : {} ", ex.getClass().getSimpleName() //$NON-NLS-1$
                , ex.getMessage());
            //fail("Unexpected exception");
        }
        assertNotNull("exception was not expected, NameGenerator will not be null", test); //$NON-NLS-1$
    }

}
